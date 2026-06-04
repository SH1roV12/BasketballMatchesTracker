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

public class Pong implements HttpHandler {


    private final ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())){

            String response = "Server successfully working";
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
