package com.example.CRUDApp.controller;

import com.example.CRUDApp.model.Owner;
import com.example.CRUDApp.repository.OwnerRespository;
import com.example.CRUDApp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OwnerController {

    @Autowired
    private OwnerRespository ownerRespository;

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/getOwner")
    public ResponseEntity<List<Owner>> getAllEntries () {
        try {
            List<Owner> ownerList = ownerService.getAllOwnerEntries();

            if (ownerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(ownerList, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addOwnerInfo")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        ownerService.saveOwner(owner);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

}
