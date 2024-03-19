package com.example.crud.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.entities.Automovil;



@Repository
public interface IAutomovilRepository extends JpaRepository<Automovil, Long> {
    //
    public ArrayList<Automovil> findByCliente_Club_Id(Long clubId);

    public ArrayList<Automovil> 
        findByCliente_Club_IdAndActivoAndCliente_Activo
            (Long clubId, Boolean autoState, Boolean clienteState);
}
