package com.example.crud.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.entities.Cliente;



@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    public ArrayList<Cliente> findByClub_Id(Long idClub); 
    

}
