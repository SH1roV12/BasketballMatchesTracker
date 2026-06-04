package ru.yaharuott.start;

import com.sun.net.httpserver.HttpServer;
import ru.yaharuott.contoller.GetMatches;
import ru.yaharuott.contoller.GetTeam;
import ru.yaharuott.contoller.GetTeams;
import ru.yaharuott.contoller.NewMatch;
import ru.yaharuott.repository.MatchRepository;
import ru.yaharuott.repository.TeamRepository;
import ru.yaharuott.service.MatchService;
import ru.yaharuott.service.TeamService;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Start {
    public void startServer () throws Exception {
        TeamRepository teamRepository = new TeamRepository();
        MatchRepository matchRepository = new MatchRepository();
        TeamService teamService = new TeamService(teamRepository);
        MatchService matchService = new MatchService(matchRepository,teamService);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.createContext("/api/v1/matches", new NewMatch(matchService));
        server.createContext("/api/v1/matches", new GetMatches(matchService));
        server.createContext("/api/v1/teams", new GetTeams(teamService));
        server.createContext("/api/v1/team", new GetTeam(teamService));

        System.out.println("Сервер запущен на http://localhost:8080/matches");
        server.start();
    }
}
