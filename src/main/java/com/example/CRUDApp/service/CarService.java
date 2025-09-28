package com.example.CRUDApp.service;

import com.example.CRUDApp.model.Car;
import com.example.CRUDApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public void saveCarEntry(Car car) {
        carRepository.save(car);
    }

    public List<Car> getCarAllEntries() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

//    public Optional<Car> updateCarEntry(Long id, Car newCarData) {
//        return carRepository.findById(id).map(oldCarEntry -> {
//                oldCarEntry.setBrand(newCarData.getBrand());
//                oldCarEntry.setModel(newCarData.getModel());
//                return carRepository.save(oldCarEntry);
//        });
//    }

    public Optional<Car> updateCarEntry(Long id, Car newCarData) {
        return carRepository.findById(id).map(oldCarEntry -> {

            // Basic fields
            oldCarEntry.setBrand(newCarData.getBrand());
            oldCarEntry.setModel(newCarData.getModel());
            oldCarEntry.setColor(newCarData.getColor());
            oldCarEntry.setYearOfManufacture(newCarData.getYearOfManufacture());
            oldCarEntry.setPrice(newCarData.getPrice());
            oldCarEntry.setFuelType(newCarData.getFuelType());
            oldCarEntry.setTransmission(newCarData.getTransmission());
            oldCarEntry.setMileage(newCarData.getMileage());
            oldCarEntry.setAvailable(newCarData.isAvailable());
            oldCarEntry.setRegistrationDate(newCarData.getRegistrationDate());

            // For collection fields
            oldCarEntry.setFeatures(newCarData.getFeatures());

            // For embedded object
            if (newCarData.getDimensions() != null) {
                oldCarEntry.setDimensions(newCarData.getDimensions());
            }

            return carRepository.save(oldCarEntry);
        });
    }


    public void deleteCarEntry(Long id) {
        carRepository.deleteById(id);
    }

}
