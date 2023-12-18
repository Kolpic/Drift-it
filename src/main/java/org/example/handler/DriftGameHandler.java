package org.example.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.service.CarGameService;
import org.example.utils.HandleRequestUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * HTTP handler for managing drift actions in the car game.
 * This class handles incoming HTTP requests for performing drifts with cars,
 * and sends responses based on the drift results.
 */
public class DriftGameHandler implements HttpHandler {

    private final CarGameService carGameService = CarGameService.getInstance();

    /**
     * Handles HTTP requests for drifting actions in the car game.
     * It processes the request, performs the drift action through the CarGameService,
     * and sends the drift result back to the client.
     *
     * @param exchange The HTTP exchange object representing the request and response.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String requestedClientParams = HandleRequestUtil.handleRequestParametersForDrift(exchange);
            String response = carGameService.driftIt(requestedClientParams);
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
