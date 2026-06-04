package ru.yaharuott.service;
import ru.yaharuott.domain.Match;
import ru.yaharuott.domain.Team;
import ru.yaharuott.repository.TeamRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TeamService  {
    private final TeamRepository repository;
    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }
    public void addTeam(String name,Match match){
        Team candidate = repository.getTeamByName(name);
        if (candidate != null){
            candidate.addMatch(match);
            candidate.setEffectiveFieldGoalPercentage(calculateEffectiveFieldGoalPercentage(candidate.getMatches(),candidate.getName()));
            repository.save(candidate);
            return;
        }
        Team team = new Team(name,1);
        team.addMatch(match);
        team.setEffectiveFieldGoalPercentage(calculateEffectiveFieldGoalPercentage(team.getMatches(), team.getName()));
        repository.save(team);
    }

    public double calculateEffectiveFieldGoalPercentage(ArrayList<Match> matches, String name){
        return matches.stream().mapToDouble(
                match ->{
                    boolean isHome = match.getHomeTeam().equals(name);

                        double goals = isHome ? match.getHomeGoalsMade() : match.getAwayGoalsMade();
                        double threes = isHome ? match.getHomeThreePointersMade() : match.getAwayThreePointersMade();
                        double all = match.getFieldGoalsAttempted();

                        return  (goals + 0.5 * threes) / all;

                }).average().orElse(0.0);
    }

    public Team getTeam(String name){
        return repository.getTeamByName(name);
    }

    public ConcurrentHashMap<String,Team> getTeams(){
        return repository.getTeams();
    }
}
