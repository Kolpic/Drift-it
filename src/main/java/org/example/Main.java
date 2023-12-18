package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.handler.DestroyCarGameHandler;
import org.example.handler.DriftGameHandler;
import org.example.handler.RepairGameHandler;
import org.example.handler.StartGameHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Main class to set up and start the HTTP server for the car game.
 * This class configures the server, sets up the context paths for various game actions,
 * and starts the server to listen for incoming requests.
 *
 * In the whole application there is only one car, and we can play with it.
 * There is only one, because i don't see the point of using more,
 * because i am not using a database(using only data structures) and every
 * time we close the application everything is deleted, and we don't have the information anymore.
 *
 * Example for starting the game endpoint: http://localhost:8080/start/driftingCoefficient=100&speed=15&tiresLevel=2
 * Example for starting the drift endpoint: http://localhost:8080/drift-it/winter
 * Example for repairing the car endpoint: http://localhost:8080/repair/tiresLevel=5&
 * Example for destroying the car endpoint: http://localhost:8080/destroy
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Create an HTTP server listening on localhost at port 8080.
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        // Set up context paths and their corresponding handlers.
        server.createContext("/start", new StartGameHandler());
        server.createContext("/drift-it", new DriftGameHandler());
        server.createContext("/repair", new RepairGameHandler());
        server.createContext("/destroy", new DestroyCarGameHandler());

        // Start the server.
        server.start();
        System.out.println("Server started!");
    }
}