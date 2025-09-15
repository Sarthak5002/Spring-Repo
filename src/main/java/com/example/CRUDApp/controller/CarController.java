package com.example.CRUDApp.controller;

import com.example.CRUDApp.model.Car;
import com.example.CRUDApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/getAllCars")
    public ResponseEntity<List<Car>> getAllCars() {

        try {
            List<Car> carList = new ArrayList<>(carRepository.findAll());

            if(carList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(carList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCarById/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> carData = carRepository.findById(id);

        if(carData.isPresent()) {
            return new ResponseEntity<>(carData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addCar")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car carObj = carRepository.save(car);

        return new ResponseEntity<>(carObj, HttpStatus.OK);
    }

    @PostMapping("/updateCarById/{id}")
    public ResponseEntity<Car> updateCarById(@PathVariable Long id, @RequestBody Car newCarData) {

        Optional<Car> oldCarData = carRepository.findById(id);

        if(oldCarData.isPresent()) {
            Car updatedCarData = oldCarData.get();
            updatedCarData.setBrand(newCarData.getBrand());
            updatedCarData.setModel(newCarData.getModel());

            Car carObj = carRepository.save(updatedCarData);
            return new ResponseEntity<>(carObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        Optional<Car> carData = carRepository.findById(id);

        if(carData.isPresent()) {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
