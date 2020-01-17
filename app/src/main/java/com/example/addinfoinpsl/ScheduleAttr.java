package com.example.addinfoinpsl;

public class ScheduleAttr {
    String teamOne;
    String teamTwo;
    String id;
    String time;
    String date;
    String status;

    public ScheduleAttr() {
    }

    public ScheduleAttr(String teamOne, String teamTwo, String id, String time, String date, String status) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.id = id;
        this.time = time;
        this.date = date;
        this.status = status;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
