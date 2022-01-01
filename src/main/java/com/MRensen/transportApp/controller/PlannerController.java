package com.MRensen.transportApp.controller;

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
    public ResponseEntity<List<Planner>> getAllPlanners(){
       var planners = plannerService.getAllPlanners();
       return ResponseEntity.ok().body(planners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planner> getPlanner(@PathVariable Long id){
        var planner = plannerService.getPlanner(id);
        return ResponseEntity.ok().body(planner);
    }

    @PostMapping("")
    public ResponseEntity<Object> postPlanner(@RequestBody Planner planner){
        Planner p = plannerService.addPlanner(planner);
        Long id = p.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePlanner(@PathVariable Long id){
        plannerService.deletePlanner(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePlanner(@PathVariable Long id, @RequestBody Planner planner){
        plannerService.updatePlanner(id, planner);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patchPlanner(@PathVariable Long id, @RequestBody Planner planner){
        plannerService.patchPlanner(id, planner);
        return  ResponseEntity.noContent().build();
    }
}