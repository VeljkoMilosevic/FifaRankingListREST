/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.project.server.model.Confederation;
import spring.project.server.services.ConfederationService;

import java.util.List;

/**
 * @author Veljko
 */
@RequestMapping("api/confederations")
@RestController
public class ConfederationController {

    private ConfederationService confederationService;

    public ConfederationController(final ConfederationService confederationService) {
        this.confederationService = confederationService;
    }

    @GetMapping()
    public List<Confederation> getAllConfederation() {
        return confederationService.getAllConfederation();
    }
}
