package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.PlannerDto;
import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Planner;
import com.MRensen.transportApp.service.PlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/planners")
public class PlannerController {
    private PlannerService plannerService;

    @Autowired
    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @GetMapping("")
    public ResponseEntity<List<PlannerDto>> getAllPlanners(){
       var planners = plannerService.getAllPlanners().stream().map(PlannerDto::fromPlanner).toList();
       return ResponseEntity.ok().body(planners);
    }

    @GetMapping("/{username}")
    public ResponseEntity<PlannerDto> getPlanner(@PathVariable String username){
        var planner = PlannerDto.fromPlanner(plannerService.getPlanner(username));
        return ResponseEntity.ok().body(planner);
    }

    @PostMapping("")
    public ResponseEntity<Object> postPlanner(@RequestBody PlannerDto planner){
        Planner p = plannerService.addPlanner(planner.toPlanner());
        String username = p.getUsername();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(username).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Object> deletePlanner(@PathVariable String username){
        plannerService.deletePlanner(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{username}")
    public ResponseEntity<Object> updatePlanner(@PathVariable String username, @RequestBody PlannerDto planner){
        plannerService.updatePlanner(username, planner.toPlanner());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{username}")
    public ResponseEntity<Object> patchPlanner(@PathVariable String username, @RequestBody PlannerDto planner){
        plannerService.patchPlanner(username, planner.toPlanner());
        return  ResponseEntity.noContent().build();
    }
}