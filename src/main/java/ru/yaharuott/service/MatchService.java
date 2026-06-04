package ru.yaharuott.service;

import ru.yaharuott.domain.Match;
import ru.yaharuott.pkg.exeptions.WrongScoreException;
import ru.yaharuott.repository.MatchRepository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchService  {
    private final MatchRepository repository;
    private final TeamService teamService;
    public MatchService(MatchRepository repository, TeamService teamService) {
        this.repository = repository;
        this.teamService = teamService;
    }

    public void addMatch(String homeTeam, String awayTeam, int homeScore, int awayScore, int homeGoalsMade, int awayGoalsMade, int awayThreePointersMade, int homeThreePointersMade, int fieldGoalsAttempted){
        if (homeScore == awayScore || homeScore < 0 || awayScore < 0 ){
            throw new WrongScoreException();
        }
        String id = UUID.randomUUID().toString();
        Match match = new Match(id,homeTeam,awayTeam,homeScore,awayScore,homeGoalsMade,awayGoalsMade,awayThreePointersMade,homeThreePointersMade,fieldGoalsAttempted);
        repository.save(match);
        teamService.addTeam(homeTeam, match);
        teamService.addTeam(awayTeam, match);
    }


    public ConcurrentHashMap<String, Match> getMatches(){
        return repository.getMatches();
    }
}
