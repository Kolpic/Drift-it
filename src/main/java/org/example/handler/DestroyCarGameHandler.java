package org.example.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.service.CarGameService;
import org.example.utils.HandleRequestUtil;

import java.io.IOException;
import java.io.OutputStream;

/**
 * HTTP handler for managing car destruction in the car game.
 * This class handles incoming HTTP requests for destroying cars in the game,
 * processes these requests, and sends appropriate responses back to the client.
 */
public class DestroyCarGameHandler implements HttpHandler {

    private final CarGameService carGameService = CarGameService.getInstance();

    /**
     * Handles HTTP requests for destroying cars in the car game.
     * It calls the CarGameService to perform the car destruction and sends back
     * the response about the destroyed car to the client.
     *
     * @param exchange The HTTP exchange object representing the request and response.
     * @throws IOException If an I/O error occurs during the handling of the request.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String response = carGameService.destroyCar();
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
