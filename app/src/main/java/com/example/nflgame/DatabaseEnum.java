package com.example.nflgame;

public enum DatabaseEnum {

    DB_NAME("allSeasonsDB"),
    ALL_SEASONS_TABLE("allSeasonsTable"),
    SAVEGAME_TABLE("saveGameTable");

    private String stringValue;

    private DatabaseEnum(String toString) {
        stringValue = toString;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
