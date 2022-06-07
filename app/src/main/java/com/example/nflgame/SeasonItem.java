package com.example.nflgame;

public class SeasonItem {
    private int mImageResource;
    private String mSeason;
    private String mIncorrect;
    private String mCorrect;

    public SeasonItem(int imageResource, String season, String correct, String incorrect) {
        mImageResource = imageResource;
        mSeason = season;
        mIncorrect = incorrect;
        mCorrect = correct;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmSeason() {
        return mSeason;
    }

    public String getmIncorrect() {
        return mIncorrect;
    }

    public String getmCorrect() {
        return mCorrect;
    }
}