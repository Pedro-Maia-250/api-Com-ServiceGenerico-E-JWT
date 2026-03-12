package com.lunarvoid.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lunarvoid.entities.Circulo;
import com.lunarvoid.services.CirculoService;

@RestController
@RequestMapping(value = "/circulos")
public class CirculoResources {
    
    @Autowired
    private CirculoService service;

    @GetMapping
    public ResponseEntity<List<Circulo>> fidAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Circulo> fidAll(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findbyId(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Circulo> update(@PathVariable Long id, @RequestBody Circulo obj){
        return ResponseEntity.ok().body(service.update(obj, id));
    }

    @PostMapping
    public ResponseEntity<Circulo> insert(@RequestBody Circulo obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping("/{id}/perimetro")
    public ResponseEntity<Double> perimetro(@PathVariable Long id){
        return ResponseEntity.ok().body(service.perimetro(id));
    }

    @GetMapping("/{id}/area")
    public ResponseEntity<Double> area(@PathVariable Long id){
        return ResponseEntity.ok().body(service.area(id));
    }
}
