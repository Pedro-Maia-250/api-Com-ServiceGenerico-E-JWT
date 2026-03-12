package com.lunarvoid.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunarvoid.entities.Forma;
import com.lunarvoid.services.*;

@RestController
@RequestMapping(value = "/formas")
public class FormaResources {

    @Autowired
    private CirculoService cService;
    @Autowired
    private QuadradoService qService;
    @Autowired
    private RetanguloService rService;
    
    @GetMapping
    public ResponseEntity<List<Forma>> returnAll(){
        List<Forma> formas = new ArrayList<>();

        formas.addAll(cService.findAll());
        formas.addAll(qService.findAll());
        formas.addAll(rService.findAll());

        return ResponseEntity.ok().body(formas);
    }
}
