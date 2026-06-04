package ru.yaharuott;

import com.sun.net.httpserver.HttpServer;
import ru.yaharuott.contoller.*;
import ru.yaharuott.repository.MatchRepository;
import ru.yaharuott.repository.TeamRepository;
import ru.yaharuott.service.MatchService;
import ru.yaharuott.service.TeamService;
import ru.yaharuott.start.Start;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws Exception{
        TeamRepository teamRepository = new TeamRepository();
        MatchRepository matchRepository = new MatchRepository();
        TeamService teamService = new TeamService(teamRepository);
        MatchService matchService = new MatchService(matchRepository,teamService);

        HttpServer server = HttpServer.create(new InetSocketAddress(9965),0);

        server.createContext("/api/v1/ping", new Pong());
        server.createContext("/api/v1/matches/new", new NewMatch(matchService));
        server.createContext("/api/v1/matches", new GetMatches(matchService));
        server.createContext("/api/v1/teams", new GetTeams(teamService));
        server.createContext("/api/v1/team", new GetTeam(teamService));
        server.setExecutor(Executors.newFixedThreadPool(10));
        System.out.println("Сервер запущен на http://localhost:9965/matches");
        server.start();
    }
}
