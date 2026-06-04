package ru.yaharuott.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.yaharuott.domain.Team;
import ru.yaharuott.dto.GetTeamRequest;
import ru.yaharuott.dto.MatchRequest;
import ru.yaharuott.dto.MatchResponse;
import ru.yaharuott.service.MatchService;
import ru.yaharuott.service.TeamService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetTeam implements HttpHandler {
    private final TeamService teamService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetTeam(TeamService teamService){
        this.teamService = teamService;

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())){
            InputStream is = exchange.getRequestBody();
            GetTeamRequest dto = objectMapper.readValue(is, GetTeamRequest.class);

            Team team = teamService.getTeam(dto.name);
            String json = objectMapper.writeValueAsString(team);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200,bytes.length);
            try (OutputStream os = exchange.getResponseBody()){
                os.write(bytes);
            }
        }else{
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
