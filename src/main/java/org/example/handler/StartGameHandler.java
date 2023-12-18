package org.example.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.service.CarGameService;
import org.example.utils.HandleRequestUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * HTTP handler for starting the car game.
 * This class handles incoming HTTP requests to initialize the car game
 * by setting up car properties based on client parameters.
 */
public class StartGameHandler implements HttpHandler {

    private final CarGameService carGameService = CarGameService.getInstance();

    /**
     * Handles HTTP requests to start the car game.
     * Extracts request parameters, sets up the car, and sends a response back to the client.
     *
     * @param exchange The HTTP exchange object representing the request and response.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            Map<String, String> requestedClientParams = HandleRequestUtil.handleRequestParameters(exchange);
            String response = carGameService.setUpCar(requestedClientParams);
            exchange.sendResponseHeaders(200, response.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, 0);
        }
    }
}
