package com.ibm.marvel.controller;

import com.ibm.marvel.dtos.CriadorDTO;
import com.ibm.marvel.model.Criador;
import com.ibm.marvel.services.CriadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/criador")
public class CriadorController {

    @Autowired
    private CriadorService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Criador> find (@PathVariable Integer id)  {
        Criador obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Criador obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value ="/{id}", method =RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Criador obj){
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete (@PathVariable Integer id)  {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CriadorDTO>> findAll()  {
        List<Criador> list = service.findAll();
        List<CriadorDTO> dto = list.stream().map(CriadorDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

}
