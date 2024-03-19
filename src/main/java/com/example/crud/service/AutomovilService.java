package com.example.crud.service;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.entities.Automovil;
import com.example.crud.repository.IAutomovilRepository;


@Service
public class AutomovilService {
    @Autowired
    IAutomovilRepository automovilRepository;

    //prueba de obtener autos por club
    public ArrayList<Automovil> getAutomovilesPorClub(Long clubId){
        return automovilRepository.findByCliente_Club_Id(clubId);
    }

    //servicio que retorna solo los chips
    public Stream<Long> getChipsActivosPorClub (Long clubId){
        return automovilRepository.
            findByCliente_Club_IdAndActivoAndCliente_Activo(clubId, true, true).
                stream().
                    map(Automovil::getIdChip);
    }
}
