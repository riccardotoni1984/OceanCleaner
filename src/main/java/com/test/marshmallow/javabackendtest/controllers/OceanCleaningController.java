package com.test.marshmallow.javabackendtest.controllers;

import com.test.marshmallow.javabackendtest.services.OceanCleaningService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OceanCleaningController {

    @Autowired
    OceanCleaningService oceanCleaningService;

    @PostMapping(path= "/clean", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addEmployee(@RequestBody String requestBody)
    {
        JSONObject jsonRequest = new JSONObject(requestBody);
        JSONObject response = oceanCleaningService.process(jsonRequest);

        return ResponseEntity.ok(response.toString());
    }
}
