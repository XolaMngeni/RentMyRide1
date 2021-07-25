/* CarRepository.java
   Implementation of ICarRepository
   Author: Rick Allen Herbert (219014337)
   Date: 24 July 2021
*/
package za.ac.cput.repository.car.impl;

import za.ac.cput.entity.Car;
import za.ac.cput.repository.car.ICarRepository;

import java.util.HashSet;
import java.util.Set;

public class CarRepository implements ICarRepository {

    private static CarRepository repository = null;
    private Set<Car> carDB = null;

    private CarRepository()
    {
        carDB = new HashSet<Car>();
    }

    public static CarRepository getRepository()
    {
        if (repository == null)
        {
            repository = new CarRepository();
        }
        return repository;
    }

    @Override
    public Car create(Car car) {
        boolean success = carDB.add(car);
        if (!success)
            return null;
        return car;
    }

    @Override
    public Car read(String numberPlate) {
        for (Car c : carDB)
            if (c.getNumberPlate().equals(numberPlate))
            {
                return c;
            }
        return null;
    }

    @Override
    public Car update(Car car) {
        Car preCar = read(car.getNumberPlate());
        if (preCar != null)
        {
            carDB.remove(preCar);
            carDB.add(car);
            return car;
        }
        return null;
    }

    @Override
    public boolean delete(String numberPlate) {
        Car carToDelete = read(numberPlate);
        if (carToDelete == null)
            return false;
        carDB.remove(numberPlate);
        return true;
    }

    @Override
    public Set<Car> getAll() {
        return carDB;
    }
}
