package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.entities.Club;



@Repository
public interface IClubRepository extends JpaRepository<Club, Long> {
    
}
