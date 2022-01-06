package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.IdDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<PlannerDto> getPlanner(@PathVariable IdDto id){
        var planner = PlannerDto.fromPlanner(plannerService.getPlanner(id.id));
        return ResponseEntity.ok().body(planner);
    }

    @PostMapping("")
    public ResponseEntity<Object> postPlanner(@RequestBody PlannerDto planner){
        Planner p = plannerService.addPlanner(planner.toPlanner());
        Long id = p.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePlanner(@PathVariable IdDto id){
        plannerService.deletePlanner(id.id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePlanner(@PathVariable IdDto id, @RequestBody PlannerDto planner){
        plannerService.updatePlanner(id.id, planner.toPlanner());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patchPlanner(@PathVariable IdDto id, @RequestBody PlannerDto planner){
        plannerService.patchPlanner(id.id, planner.toPlanner());
        return  ResponseEntity.noContent().build();
    }
}