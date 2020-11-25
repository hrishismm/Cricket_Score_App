package com.example.cricketlivescore;

public class Model {
    String id,team1,team2,status,date,type;



    //Constructor
    public Model(String id,String team1, String team2,String type,String status,String date) {
        this.id = id;

        this.team1 = team1;
        this.team2 = team2;
        this.status = status;
        this.date = date;
        this.type = type;

    }



    //getters
    public String getID() {
        return id;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
