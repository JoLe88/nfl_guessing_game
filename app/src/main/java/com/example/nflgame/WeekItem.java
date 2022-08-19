package com.example.nflgame;

public class WeekItem {
    private int mImageResource;
    private int mWeek;
    private int mStars;

    public WeekItem(int imageResource, int week, int stars) {
        mImageResource = imageResource;
        mWeek = week;
        mStars = stars;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public int getmWeek() {
        return mWeek;
    }

    public int getmStars() {
        return mStars;
    }
}