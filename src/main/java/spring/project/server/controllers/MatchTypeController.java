/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.project.server.model.MatchType;
import spring.project.server.services.MatchTypeService;

import java.util.List;

@RequestMapping("api/matchTypes")
@RestController
public class MatchTypeController {

    private final MatchTypeService matchTypeService;

    public MatchTypeController(final MatchTypeService matchTypeService) {
        this.matchTypeService = matchTypeService;
    }

    @GetMapping()
    public List<MatchType> getAllMatchTypes() {
        return matchTypeService.getAllMatchTypes();
    }
}
