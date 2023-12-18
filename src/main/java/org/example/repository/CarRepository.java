package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing a collection of Car objects.
 * Implements the Singleton pattern to ensure only one instance of the repository exists.
 */
@Getter
@Setter
public class CarRepository {

    private List<Car> cars;
    private static CarRepository carRepository;

    private CarRepository() {
        this.cars = new ArrayList<>();
    }

    /**
     * Provides access to the singleton instance of CarRepository.
     * If the instance doesn't exist, it is created.
     *
     * @return The singleton instance of CarRepository.
     */
    public static CarRepository getInstance() {
        if (carRepository == null) {
            carRepository = new CarRepository();
        }
        return carRepository;
    }

    /**
     * Creates and adds a default Car object to the repository.
     * The default car has predefined properties for drifting coefficient, speed, and tires level.
     */
    public void setDefaultCarProperties() {
        Car car = new Car();
        car.setDriftingCoefficient(50);
        car.setSpeed(15);
        car.setTiresLevel(5);
        cars.add(car);
    }

    /**
     * Destroys the first Car object in the repository and returns a string describing the action.
     * The description includes the stats of the destroyed car.
     *
     * @return A string description of the destroyed Car.
     */
    public String destroyCar() {
        StringBuilder stringBuilder = new StringBuilder();
        Car car = cars.get(0);
        stringBuilder.append("The car with stats: ")
                .append(car.getDriftingCoefficient()).append(" drifting coefficient ")
                        .append(car.getSpeed()).append(" speed ")
                        .append(car.getTiresLevel()).append(" tires level, ")
                        .append("is destroyed.");
        cars.remove(0);
        return stringBuilder.toString();
    }
}
