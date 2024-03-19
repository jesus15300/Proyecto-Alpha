package com.example.crud.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.crud.entities.Cliente;
import com.example.crud.repository.IClienteRepository;

public class ClienteService {

    @Autowired
    IClienteRepository clienteRepository;

    //Prueba de servicio obtener cliente por club
    public ArrayList<Cliente> getClientesPorClub(Long clubId){
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) clienteRepository.findByClub_Id(clubId);
        
        return clientes;
    }
}
