package ru.yaharuott.repository;

import ru.yaharuott.domain.Match;
import ru.yaharuott.domain.Team;

import java.util.concurrent.ConcurrentHashMap;

public class TeamRepository {
    private final ConcurrentHashMap<String, Team> teams = new ConcurrentHashMap<>();
    public TeamRepository(){}


    public void save(Team team){
        teams.put(team.getName(),team);
    }


    public Team getTeamByName(String name){
        return teams.get(name);
    }

    public ConcurrentHashMap<String,Team> getTeams(){
        return teams;
    }

}

