package com.example.CRUDApp.repository;

import com.example.CRUDApp.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRespository extends JpaRepository<Owner, Long> {
}
