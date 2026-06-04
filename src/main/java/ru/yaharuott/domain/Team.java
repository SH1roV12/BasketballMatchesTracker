package ru.yaharuott.domain;

import java.util.ArrayList;

public class Team {
    private String name;
    private int totalGames;
    private double effectiveFieldGoalPercentage;
    private final ArrayList<Match> matches = new ArrayList<>();
    public Team( String name, int totalGames){
        this.name = name;
        this.totalGames = 0;

    }

    public String getName(){
        return name;
    }
    public void addMatch(Match match){
        matches.add(match);
        totalGames++;
    }

    public int getTotalGames(){
        return totalGames;
    }

    public double getEffectiveFieldGoalPercentage() {
        return effectiveFieldGoalPercentage;
    }

    public void setEffectiveFieldGoalPercentage(double efg){
        this.effectiveFieldGoalPercentage = efg;
    }

    public ArrayList<Match> getMatches(){
        return matches;
    }
}
