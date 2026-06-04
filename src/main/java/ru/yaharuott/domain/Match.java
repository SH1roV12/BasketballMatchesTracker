package ru.yaharuott.domain;

public class Match {
    private String id;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private int homeGoalsMade;
    private int awayGoalsMade;
    private int homeThreePointersMade;
    private int awayThreePointersMade;
    private int fieldGoalsAttempted;

    public Match(String id, String homeTeam, String awayTeam, int homeScore, int awayScore,int homeGoalsMade,int awayGoalsMade,int awayThreePointersMade,int homeThreePointersMade,int fieldGoalsAttempted){
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homeGoalsMade = homeGoalsMade;
        this.awayGoalsMade = awayGoalsMade;
        this.awayThreePointersMade = awayThreePointersMade;
        this.homeThreePointersMade = homeThreePointersMade;
        this.fieldGoalsAttempted = fieldGoalsAttempted;
    }

    public String getHomeTeam(){
        return homeTeam;
    }
    public String getAwayTeam(){return awayTeam;}
    public int getHomeScore(){
        return homeScore;
    }
    public int getAwayScore(){
        return awayScore;
    }
    public int getHomeGoalsMade(){
        return homeGoalsMade;
    }
    public int getAwayGoalsMade(){
        return awayGoalsMade;
    }

    public int getHomeThreePointersMade(){
        return homeThreePointersMade;
    }
    public int getAwayThreePointersMade(){
        return awayThreePointersMade;
    }

    public int getFieldGoalsAttempted(){
        return fieldGoalsAttempted;
    }

    public String getId(){
        return id;
    }

}
