package com.example.user.kansaidialect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.kansaidialect.R;
import com.example.user.kansaidialect.adapter.WordAdapter;
import com.example.user.kansaidialect.storages.EnglishWord;
import com.example.user.kansaidialect.storages.Setting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/09/08.
 */

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_page);

        ListView listView = (ListView) findViewById(R.id.lstRanking);

        String[] wordsArray = getResources().getStringArray(R.array.english_array);

        // 再生された単語マップを取得
        Map<String, ?> map = Setting.getAllPreferences(getApplicationContext());

        ArrayList<EnglishWord> list = new ArrayList<>();

        // 再生されていない項目を単語マップに追加
        for (int counter = 0; counter < wordsArray.length; counter++) {
            if (!map.containsKey(wordsArray[counter])) {
                map.put(wordsArray[counter], null);
            }
        }

        // 単語マップ並び替え用のリストを定義
        final List<Map.Entry<String, ?>> entryList = new ArrayList<Map.Entry<String, ?>>(map.entrySet());

        // 起動時に再生回数の降順に単語リストを並び替え
        Collections.sort(entryList, new Comparator<Map.Entry<String, ?>>() {
            public int compare(Map.Entry<String, ?> obj1, Map.Entry<String, ?> obj2) {

                Integer firstValue = 0;
                Integer secondValue = 0;

                try {
                    firstValue = (Integer) obj1.getValue();
                } catch (NullPointerException e) {
                    // 意図的にnullを入れてる場合はfirstValueを0のままにする
                }

                try {
                    secondValue = (Integer) obj2.getValue();
                } catch (NullPointerException e) {
                    // 意図的にnullを入れてる場合はfirstValueを0のままにする
                }

                int order = 0;
                if (firstValue == null) {
                    if (secondValue == null)
                        order = 0;  // null == null
                    else
                        order = 1;  // null > obj2
                } else {
                    if (secondValue == null)
                        order = -1;  // obj1 < null
                    else
                        order = secondValue.compareTo(firstValue);
                }
                return order;
            }
        });


        for (Map.Entry<String, ?> englishWord : entryList) {
            EnglishWord wordInf = new EnglishWord();
            wordInf.setWordName(englishWord.getKey());
            wordInf.setTimes(Setting.loadPlays(getApplicationContext(), englishWord.getKey()));
            list.add(wordInf);
        }

        WordAdapter wordAdapter = new WordAdapter(RankingActivity.this);

        wordAdapter.setWordList(list);
        listView.setAdapter(wordAdapter);

        Button returnButton = findViewById(R.id.btnBack);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
