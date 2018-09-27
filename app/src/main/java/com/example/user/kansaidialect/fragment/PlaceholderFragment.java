package com.example.user.kansaidialect.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.kansaidialect.R;
import com.example.user.kansaidialect.activity.RankingActivity;
import com.example.user.kansaidialect.storages.Setting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/08/11.
 */

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] wordsArray = getResources().getStringArray(R.array.english_array);

        // 再生された単語マップを取得
        Map<String, ?> map = Setting.getAllPreferences(getContext());

        // 再生されていない項目を単語マップに追加
        for (int counter = 0; counter < wordsArray.length; counter++) {
            if (!map.containsKey(wordsArray[counter])) {
                map.put(wordsArray[counter], null);
            }
        }

        // 単語マップ並び替え用のリストを定義
        final List<Map.Entry<String, ?>> entryList = new ArrayList<Map.Entry<String, ?>>(map.entrySet());

        // 起動時に再生回数の昇順に単語リストを並び替え
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

                int order;
                if (firstValue == null) {
                    if (secondValue == null)
                        order = 0;  // null == null
                    else
                        order = -1;  // null > o2
                } else {
                    if (secondValue == null)
                        order = 1;  // o1 < null
                    else
                        order = firstValue.compareTo(secondValue);
                }
                return order;
            }
        });

        View rootView = inflater.inflate(R.layout.word_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.english_txt);
        textView.setText(entryList.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getKey().toString());

        ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.play_btn);

        final String englishTxt = textView.getText().toString();
        final String keyTxt = englishTxt.replaceAll(" ", "");

        int mediaResource = container.getResources().getIdentifier(keyTxt, "raw", getContext().getPackageName());

        final MediaPlayer kansaiMp = MediaPlayer.create(getContext(), mediaResource);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kansaiMp.start();
                int plays = Setting.loadPlays(getContext(), englishTxt);
                Setting.savePlays(getContext(), englishTxt, plays + 1);
                Log.d("再生回数","「" + plays + "回」");
            }
        });

        Button rankingButton = rootView.findViewById(R.id.list_btn);

        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(), RankingActivity.class);
                getContext().startActivity(intent);
            }
        });

        return rootView;
    }
}
