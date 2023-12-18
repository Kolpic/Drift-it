package org.example.service;

import org.example.entity.Car;
import org.example.entity.Seasons;
import org.example.exception.TooLowTiresLevelException;
import org.example.repository.CarRepository;

import java.util.Map;

/**
 * Service class for managing car game functionalities.
 * This class handles operations like setting up cars, performing drifts, repairs, and destroying cars.
 */
public class CarGameService {

    private static CarGameService carGameService;
    private final CarRepository carRepository = CarRepository.getInstance();

    /**
     * Provides access to the singleton instance of CarGameService.
     * If the instance doesn't exist, it is created.
     *
     * @return The singleton instance of CarGameService.
     */
    public static CarGameService getInstance() {
        if (carGameService == null) {
            carGameService = new CarGameService();
        }
        return carGameService;
    }

    /**
     * Sets up a car with properties specified in the requested client parameters.
     * Calculates and returns the drift rating of the car.
     *
     * @param requestedClientParams Map containing car properties to be set.
     * @return A string indicating the drift rating of the car.
     */
    public String setUpCar(Map<String, String> requestedClientParams) {
        carRepository.setDefaultCarProperties();
        Car car = carRepository.getCars().get(0);

        updateCarWithUserInputDetails(requestedClientParams, car);

        double finalResult = 0;
        finalResult =  (car.getDriftingCoefficient() + car.getSpeed()
                + car.getTiresLevel());
        return String.format("Your car have %.2f drift rating", finalResult);
    }

    /**
     * Performs a drift action with the car based on the specified season.
     * Calculates drift points and returns a result string.
     *
     * @param season The season during which the drift is performed.
     * @return A string describing the result of the drift.
     */
    public String driftIt(String season) {
        Car car = carRepository.getCars().get(0);
        Seasons seasons = Seasons.valueOf(season.toUpperCase());

        double driftPoints = (car.getDriftingCoefficient() + car.getSpeed() +
                car.getTiresLevel()) * seasons.getValue();

        String resultDrift = performTheDrift(car, seasons);

        return resultDrift + "The car is with drift coefficient based on season(" +  season + ") "+ driftPoints;
    }

    /**
     * Repairs the car based on the specified client parameters.
     *
     * @param requestedClientParams Map containing details for car repair.
     * @return A string describing the updates made to the car.
     */
    public String repair(Map<String, String> requestedClientParams) {
        Car car = carRepository.getCars().get(0);
        String str = updateCarWithUserInputDetails(requestedClientParams, car);
        return str;
    }

    /**
     * Destroys the first car in the repository.
     *
     * @return A string describing the destroyed car.
     */
    public String destroyCar() {
        return carRepository.destroyCar();
    }

    /**
     * Performs the drift action for a car, altering its tire level based on the season.
     *
     * @param car The car to perform the drift.
     * @param seasons The season during which the drift is performed.
     * @return A string indicating the successful completion of the drift.
     * @throws TooLowTiresLevelException if the tire level is too low to perform a drift.
     */
    private String performTheDrift(Car car, Seasons seasons) {
        if (car.getTiresLevel() == 0) {
            throw new TooLowTiresLevelException("The car can't perform drift, because tire level is 0. ");
        }

        if (seasons.equals(Seasons.WINTER)) {
            car.setTiresLevel(car.getTiresLevel() - 1);
        } else if (seasons.equals(Seasons.SPRING)) {
            car.setTiresLevel(car.getTiresLevel() - 2);
        } else if (seasons.equals(Seasons.SUMMER)) {
            car.setTiresLevel(car.getTiresLevel() - 3);
        } else if (seasons.equals(Seasons.AUTUMN)) {
            car.setTiresLevel(car.getTiresLevel() - 2);
        }
        return "Car performed the drift successful. ";
    }

    /**
     * Updates the car properties based on user input details.
     *
     * @param requestedClientParams Map containing the updated properties.
     * @param car The car to be updated.
     * @return A string summarizing the updates made to the car.
     */
    private String updateCarWithUserInputDetails(Map<String, String> requestedClientParams, Car car) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String,String> entry : requestedClientParams.entrySet()) {
            if (entry.getKey().equals(car.getFieldDriftingCoefficientName())) {
                car.setDriftingCoefficient(Integer.parseInt(entry.getValue()));
                stringBuilder.append("Updated " + entry.getKey() + " " + entry.getValue() +
                        System.getProperty("line.separator"));
            } else if (entry.getKey().equals(car.getFieldSpeedName())) {
                car.setSpeed(Integer.parseInt(entry.getValue()));
                stringBuilder.append("Updated " + entry.getKey() + " " + entry.getValue() +
                        System.getProperty("line.separator"));
            } else if (entry.getKey().equals(car.getFieldTiresLevelName())) {
                stringBuilder.append("Updated " + entry.getKey() + " " + entry.getValue() +
                        System.getProperty("line.separator"));
                car.setTiresLevel(Integer.parseInt(entry.getValue()));
            }
        }
        return stringBuilder.toString();
    }
}
