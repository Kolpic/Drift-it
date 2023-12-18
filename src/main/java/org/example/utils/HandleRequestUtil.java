package org.example.utils;

import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for handling HTTP request parameters.
 */
public class HandleRequestUtil {

    /**
     * Extracts request parameters from an HTTP exchange and maps them into a HashMap.
     * The parameters are assumed to be in the format key=value&key=value.
     *
     * @param httpExchange The HTTP exchange containing the request.
     * @return A map containing the request parameters as key-value pairs.
     */
    public static Map<String, String> handleRequestParameters(HttpExchange httpExchange) {
        String[] pairs = httpExchange
                .getRequestURI()
                .toString()
                .split("//?")[2]
                .split("&");
        Map<String, String> mappedPairs = new HashMap<>();

        for (String pair : pairs) {
            String[] currentPair = pair.split("=");
            mappedPairs.put(currentPair[0], currentPair[1]);
        }
        return mappedPairs;
    }

    /**
     * Extracts the parameter string for a 'drift' request from an HTTP exchange.
     * This method is specific to requests related to drifting functionality.
     *
     * @param httpExchange The HTTP exchange containing the request.
     * @return The raw parameter string from the request.
     */
    public static String handleRequestParametersForDrift(HttpExchange httpExchange) {
        String pairs = httpExchange
                .getRequestURI()
                .toString()
                .split("//?")[2];
        return pairs;
    }
}
