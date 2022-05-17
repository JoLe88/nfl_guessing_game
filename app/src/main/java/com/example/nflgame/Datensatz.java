package com.example.nflgame;

public class Datensatz {

    private String gameId;
    private String season;
    private String game_type;
    private String week;
    private String date;
    private String written_date;
    private String weekday;
    private String away_team;
    private String away_score;
    private String home_team;
    private String home_score;
    private String result;
    private String total;
    private String away_win_loss_record;
    private String home_win_loss_record;
    private String roof;
    private String surface;
    private String temp;
    private String wind;
    private String written_weather;
    private String away_coach;
    private String home_coach;
    private String stadium;

    public Datensatz() {
    }

    public Datensatz(String gameId, String season, String game_type, String week, String date, String written_date, String weekday, String away_team, String away_score, String home_team, String home_score, String result, String total, String away_win_loss_record, String home_win_loss_record, String roof, String surface, String temp, String wind, String written_weather, String away_coach, String home_coach, String stadium) {
        this.gameId = gameId;
        this.season = season;
        this.game_type = game_type;
        this.week = week;
        this.date = date;
        this.written_date = written_date;
        this.weekday = weekday;
        this.away_team = away_team;
        this.away_score = away_score;
        this.home_team = home_team;
        this.home_score = home_score;
        this.result = result;
        this.away_win_loss_record = away_win_loss_record;
        this.home_win_loss_record = home_win_loss_record;
        this.total = total;
        this.roof = roof;
        this.surface = surface;
        this.temp = temp;
        this.wind = wind;
        this.written_weather = written_weather;
        this.away_coach = away_coach;
        this.home_coach = home_coach;
        this.stadium = stadium;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWritten_date() {
        return written_date;
    }

    public void setWritten_date(String written_date) {
        this.written_date = written_date;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getAway_score() {
        return away_score;
    }

    public void setAway_score(String away_score) {
        this.away_score = away_score;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getHome_score() {
        return home_score;
    }

    public void setHome_score(String home_score) {
        this.home_score = home_score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAway_win_loss_record() {
        return away_win_loss_record;
    }

    public void setAway_win_loss_record(String away_win_loss_record) {
        this.away_win_loss_record = away_win_loss_record;
    }

    public String getHome_win_loss_record() {
        return home_win_loss_record;
    }

    public void setHome_win_loss_record(String home_win_loss_record) {
        this.home_win_loss_record = home_win_loss_record;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWritten_weather() {
        return written_weather;
    }

    public void setWritten_weather(String written_weather) {
        this.written_weather = written_weather;
    }

    public String getAway_coach() {
        return away_coach;
    }

    public void setAway_coach(String away_coach) {
        this.away_coach = away_coach;
    }

    public String getHome_coach() {
        return home_coach;
    }

    public void setHome_coach(String home_coach) {
        this.home_coach = home_coach;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public void setDatensatzToNull() {
        this.gameId = null;
        this.season = null;
        this.game_type = null;
        this.week = null;
        this.date = null;
        this.written_date = null;
        this.weekday = null;
        this.away_team = null;
        this.away_score = null;
        this.home_team = null;
        this.home_score = null;
        this.result = null;
        this.total = null;
        this.away_win_loss_record = null;
        this.home_win_loss_record = null;
        this.roof = null;
        this.surface = null;
        this.temp = null;
        this.wind = null;
        this.written_weather = null;
        this.away_coach = null;
        this.home_coach = null;
        this.stadium = null;
    }

}
