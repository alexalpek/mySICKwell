package com.alex.mysickwell.controller;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.model.Table;
import com.alex.mysickwell.service.SelectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/select")
public class SelectController {

    private SelectService selectService;

    @PostMapping
    public ResponseEntity<?> selectTable(@RequestBody String query) throws MySickWellException {
        Table table = selectService.selectTable(query);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }


}
