package com.museums.world.controller;

import com.museums.world.model.Work;
import com.museums.world.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @GetMapping(path = "/museum/{museumId}/work/{workId}")
    public ResponseEntity<Work> findWork(@PathVariable Integer workId) {
        return ResponseEntity.ok(workService.findWork(workId));
    }

    @PostMapping(path = "/museum/{museumId}/work")
    public ResponseEntity<Work> saveWork(@PathVariable Integer museumId, @RequestBody @Valid Work work) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workService.saveWork(museumId, work));
    }

    @PutMapping(path = "/museum/{museumId}/work/{workId}")
    public ResponseEntity<Work> updateWork(@PathVariable Integer museumId, @PathVariable Integer workId, @RequestBody @Valid Work work) {
        return ResponseEntity.ok(workService.updateWork(museumId, workId, work));
    }

    @DeleteMapping(path = "/museum/{museumId}/work/{workId}")
    public ResponseEntity<Work> deleteWork(@PathVariable Integer workId) {
        workService.deleteWork(workId);

        return ResponseEntity.noContent().build();
    }

}
