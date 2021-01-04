package com.ibm.marvel.controller;

import com.ibm.marvel.dtos.MidiaDTO;
import com.ibm.marvel.dtos.id.MidiaIdDTO;
import com.ibm.marvel.dtos.insert.MidiaNewDTO;
import com.ibm.marvel.model.Midia;
import com.ibm.marvel.services.MidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/midia")
public class MidiaController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private MidiaService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<MidiaIdDTO> find (@PathVariable Integer id)  {
        Midia obj = service.find(id);
        return ResponseEntity.ok().body(service.createMidia(obj));
    }

    @ResponseBody
    @RequestMapping(value = "/filme", method = RequestMethod.POST)
    public ResponseEntity<Void> insertFilme(@RequestBody MidiaNewDTO obj) {
        obj = service.insertFilme(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/revista", method = RequestMethod.POST)
    public ResponseEntity<MidiaIdDTO> insertRevista(@RequestBody MidiaNewDTO obj) {
        obj = service.insertRevista(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value ="/filme/{id}", method =RequestMethod.PUT)
    public ResponseEntity<Void> updateFilme(@PathVariable Integer id, @RequestBody MidiaNewDTO obj){
        obj.setId(id);
        service.updateFilme(obj);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value ="/revista/{id}", method =RequestMethod.PUT)
    public ResponseEntity<Void> updateRevista(@PathVariable Integer id, @RequestBody MidiaNewDTO obj){
        obj.setId(id);
        service.updateRevista(obj);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete (@PathVariable Integer id)  {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MidiaDTO>> findAll()  {
        List<Midia> list = service.findAll();
        List<MidiaDTO> dto = list.stream().map(MidiaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

}
