package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.PlannerDto;
import com.MRensen.transportApp.exception.BadRequestException;
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
       var planners = plannerService.getAll().stream().map(PlannerDto::fromPlanner).toList();
       return ResponseEntity.ok().body(planners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlannerDto> getPlanner(@PathVariable Long id){
        var planner = PlannerDto.fromPlanner(plannerService.getOne(id));
        return ResponseEntity.ok().body(planner);
    }

    @PostMapping("")
    public ResponseEntity<Object> postPlanner(@RequestBody PlannerDto planner){
        if(planner.username == null){
            throw new BadRequestException("Bad request: Planner requires a username");
        }
        Planner p = plannerService.addOne(planner.toPlanner());
        Long id = p.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePlanner(@PathVariable Long id){
        plannerService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePlanner(@PathVariable Long id, @RequestBody PlannerDto planner){
        plannerService.updateOne(id, planner.toPlanner());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patchPlanner(@PathVariable Long id, @RequestBody PlannerDto planner){
        plannerService.patchOne(id, planner.toPlanner());
        return  ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/{route}")
    public ResponseEntity<Object> addPlannerRoute(@PathVariable Long id, @PathVariable Long route){
        plannerService.addPlannerRoute(route, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }
}