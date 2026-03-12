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

import com.lunarvoid.entities.Retangulo;
import com.lunarvoid.services.RetanguloService;


@RestController
@RequestMapping(value = "/retangulos")
public class RetanguloResources {
    
    @Autowired
    private RetanguloService service;

    @GetMapping
    public ResponseEntity<List<Retangulo>> fidAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Retangulo> fidAll(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findbyId(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Retangulo> update(@PathVariable Long id, @RequestBody Retangulo obj){
        return ResponseEntity.ok().body(service.update(obj, id));
    }

    @PostMapping
    public ResponseEntity<Retangulo> insert(@RequestBody Retangulo obj){
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
