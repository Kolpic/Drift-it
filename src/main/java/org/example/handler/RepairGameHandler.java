package org.example.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.service.CarGameService;
import org.example.utils.HandleRequestUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * HTTP handler for managing repair actions in the car game.
 * This class handles incoming HTTP requests for repairing cars in the game,
 * processes these requests, and sends appropriate responses back to the client.
 */
public class RepairGameHandler implements HttpHandler {

    private final CarGameService carGameService = CarGameService.getInstance();

    /**
     * Handles HTTP requests for repairing cars in the car game.
     * It extracts the repair parameters, performs the repair action using CarGameService,
     * and sends back the response about the repair to the client.
     *
     * @param exchange The HTTP exchange object representing the request and response.
     * @throws IOException If an I/O error occurs during the handling of the request.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            Map<String, String> requestClientParams = HandleRequestUtil.handleRequestParameters(exchange);
            String response = carGameService.repair(requestClientParams);
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
