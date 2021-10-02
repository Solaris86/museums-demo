package com.museums.world.museumsdemo.controller;

import com.museums.world.museumsdemo.model.Museum;
import com.museums.world.museumsdemo.service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MuseumController {

    private final MuseumService museumService;

    @GetMapping(path = "/museum/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Museum> findMuseum(@PathVariable Integer id) {
        return ResponseEntity.ok(museumService.findMuseumWithWorks(id));
    }

    @GetMapping(path = "/museum", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Museum>> findAllMuseums() {
        return ResponseEntity.ok(museumService.findAllMuseumsWithWorks());
    }

    @PostMapping(path = "/museum", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Museum> saveMuseum(@RequestBody Museum museum) {
        return ResponseEntity.status(HttpStatus.CREATED).body(museumService.saveMuseum(museum));
    }

    @PutMapping(path = "/museum/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Museum> updateMuseum(@PathVariable Integer id, @RequestBody Museum museum) {
        return ResponseEntity.ok(museumService.updateMuseum(id, museum));
    }

    @DeleteMapping(path = "/museum/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Museum> deleteMuseum(@PathVariable Integer id) {
        museumService.deleteMuseum(id);

        return ResponseEntity.noContent().build();
    }

}
