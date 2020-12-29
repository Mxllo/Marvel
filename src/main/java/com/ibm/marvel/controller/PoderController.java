package com.ibm.marvel.controller;

import com.ibm.marvel.dtos.PoderDTO;
import com.ibm.marvel.dtos.id.PoderIdDTO;
import com.ibm.marvel.dtos.insert.PoderNewDTO;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.services.PoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Poder")
public class PoderController {

    @Autowired
    private PoderService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<PoderIdDTO> find (@PathVariable Integer id)  {
        Poder obj = service.find(id);
        return ResponseEntity.ok().body(new PoderIdDTO(obj));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody PoderNewDTO obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value ="/{id}", method =RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody PoderNewDTO obj){
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete (@PathVariable Integer id)  {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PoderDTO>> findAll()  {
        List<Poder> list = service.findAll();
        List<PoderDTO> dto = list.stream().map(PoderDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

}
