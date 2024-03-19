package com.example.crud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.service.AutomovilService;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/ChipsActivosPorClub")
public class Controller {
    @Autowired
    private AutomovilService autoService;

    @GetMapping("/{clubId}")
    public Stream<Long> getChipsPorClub(@PathVariable("clubId") Long clubId) {
        return autoService.getChipsActivosPorClub(clubId);
    }



    
    
}
