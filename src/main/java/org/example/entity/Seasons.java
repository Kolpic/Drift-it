package org.example.entity;

/**
 * Enum representing the four seasons, each with an associated numerical value.
 * These values may represent a specific attribute or factor related to each season.
 */
public enum Seasons {
    SUMMER(0),
    AUTUMN(0.25),
    WINTER(0.5),
    SPRING(0.25);

    private final double value;

    /**
     * Constructor for the enum.
     *
     * @param value The numerical value associated with the season.
     */
    Seasons(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
