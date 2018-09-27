package com.example.user.kansaidialect.storages;

/**
 * Created by user on 2018/09/08.
 */

public class EnglishWord {
    long id;
    private String wordName;
    private int times;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
