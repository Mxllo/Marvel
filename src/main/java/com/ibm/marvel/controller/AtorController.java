package com.ibm.marvel.controller;

import com.ibm.marvel.dtos.AtorDTO;
import com.ibm.marvel.dtos.id.AtorIdDTO;
import com.ibm.marvel.dtos.insert.AtorNewDTO;
import com.ibm.marvel.model.Ator;
import com.ibm.marvel.services.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Ator")
public class AtorController {

    @Autowired
    private AtorService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<AtorIdDTO> find (@PathVariable Integer id)  {
        Ator obj = service.find(id);
        return ResponseEntity.ok().body(new AtorIdDTO(obj));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody AtorNewDTO obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value ="/{id}", method =RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Ator obj){
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
    public ResponseEntity<List<AtorDTO>> findAll()  {
        List<Ator> list = service.findAll();
        List<AtorDTO> dto = list.stream().map(AtorDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

}
