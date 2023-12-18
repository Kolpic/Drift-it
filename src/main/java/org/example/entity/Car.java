package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.exception.WrongDataException;

/**
 * Represents a car with properties related to its performance and condition.
 * This class includes fields for drifting coefficient, speed, and tire level,
 * with specific constraints on the values these fields can accept.
 */
@Getter
@Setter
public class Car {

    private int driftingCoefficient;
    private int speed;
    private int tiresLevel;

    /**
     * Sets the drifting coefficient of the car.
     *
     * @param driftingCoefficient the drifting coefficient to be set.
     * @throws WrongDataException if the coefficient is not in the range 1 to 100.
     */
    public void setDriftingCoefficient(int driftingCoefficient) {
        if (driftingCoefficient >= 1 && driftingCoefficient <= 100) {
            this.driftingCoefficient = driftingCoefficient;
        } else {
            throw new WrongDataException("The drifting coefficient must be between 1 and 100");
        }
    }

    /**
     * Sets the speed of the car.
     *
     * @param speed the speed to be set.
     * @throws WrongDataException if the speed is not in the range 5 to 30.
     */
    public void setSpeed(int speed) {
        if (speed >= 5 && speed <= 30) {
            this.speed = speed;
        } else {
            throw new WrongDataException("The speed must be between 5 and 30");
        }
    }

    /**
     * Sets the tires level of the car.
     *
     * @param tiresLevel the level of tire wear to be set.
     * @throws WrongDataException if the tire level is not in the range 1 to 10.
     */
    public void setTiresLevel(int tiresLevel) {
        if (tiresLevel >= 1 && tiresLevel <= 10) {
            this.tiresLevel = tiresLevel;
        } else {
            throw new WrongDataException("The tires level must be between 1 and 10");
        }
    }

    public String getFieldDriftingCoefficientName() {
        return "driftingCoefficient";
    }

    public String getFieldSpeedName() {
        return "speed";
    }

    public String getFieldTiresLevelName() {
        return "tiresLevel";
    }
}
