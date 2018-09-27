package com.example.user.kansaidialect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.kansaidialect.R;
import com.example.user.kansaidialect.storages.EnglishWord;

import java.util.ArrayList;

/**
 * Created by user on 2018/09/08.
 */
public class WordAdapter extends BaseAdapter {


    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<EnglishWord> wordList;

    public WordAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setWordList(ArrayList<EnglishWord> foodList) {
        this.wordList = foodList;
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public Object getItem(int position) {
        return wordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return wordList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_kansaiben,parent,false);

        ((TextView)convertView.findViewById(R.id.name)).setText(wordList.get(position).getWordName());
        ((TextView)convertView.findViewById(R.id.time)).setText(String.valueOf(wordList.get(position).getTimes()));

        return convertView;
    }
}

