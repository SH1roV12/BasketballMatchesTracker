package ru.yaharuott.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.yaharuott.dto.MatchRequest;
import ru.yaharuott.dto.MatchResponse;
import ru.yaharuott.service.MatchService;
import ru.yaharuott.service.TeamService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class NewMatch implements HttpHandler {
    private final MatchService matchService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public NewMatch(MatchService matchService){
        this.matchService = matchService;

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())){
            InputStream is = exchange.getRequestBody();
            MatchRequest dto = objectMapper.readValue(is, MatchRequest.class);

            matchService.addMatch(dto.homeTeam, dto.awayTeam, dto.homeScore, dto.awayScore, dto.homeGoalsMade, dto.awayGoalsMade,dto.awayThreePointersMade,dto.homeThreePointersMade,dto.fieldGoalsAttempted);
            MatchResponse response = new MatchResponse("Match successfully created");
            String json = objectMapper.writeValueAsString(response);
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
