package com.example.CRUDApp.service;

import com.example.CRUDApp.model.Owner;
import com.example.CRUDApp.repository.OwnerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerService {

    @Autowired
    private OwnerRespository ownerRespository;

    public void saveOwnerDetails(Owner owner) {
        ownerRespository.save(owner);
    }

    public List<Owner> getAllOwnerEntries() {
        return ownerRespository.findAll();
    }

    public void saveOwner(Owner owner) {
          ownerRespository.save(owner);
    }
}
