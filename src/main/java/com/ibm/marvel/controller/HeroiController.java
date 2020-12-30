package com.ibm.marvel.controller;

import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.dtos.id.HeroiIdDTO;
import com.ibm.marvel.dtos.insert.HeroiNewDTO;
import com.ibm.marvel.model.Heroi;
import com.ibm.marvel.services.HeroiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/heroi")
public class HeroiController {

    @Autowired
    private HeroiService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<HeroiIdDTO> find (@PathVariable Integer id)  {
        Heroi obj = service.find(id);
        return ResponseEntity.ok().body(new HeroiIdDTO(obj));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody HeroiNewDTO obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value ="/{id}", method =RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody HeroiNewDTO obj){
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
    public ResponseEntity<List<HeroiDTO>> findAll()  {
        List<Heroi> list = service.findAll();
        List<HeroiDTO> dto = list.stream().map(HeroiDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

}
