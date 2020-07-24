package com.alex.mysickwell.controller;

import com.alex.mysickwell.service.InsertService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/insert")
public class InsertController {

    private InsertService insertService;

    @PutMapping
    public ResponseEntity<?> insertToTable(@RequestBody String query) throws Exception {
        Integer created = insertService.insertToTable(query);
        return new ResponseEntity<>(created + " rows created.", HttpStatus.CREATED);
    }
}
