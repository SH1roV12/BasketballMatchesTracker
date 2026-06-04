package ru.yaharuott.repository;

import ru.yaharuott.domain.Match;
import ru.yaharuott.domain.Team;

import java.util.concurrent.ConcurrentHashMap;

public class MatchRepository {
    private final ConcurrentHashMap<String, Match> matches = new ConcurrentHashMap<>();
    public MatchRepository(){}

    public void save(Match match){
        matches.put(match.getId(),match);
    }



    public ConcurrentHashMap<String, Match> getMatches(){
        return matches;
    }
}
