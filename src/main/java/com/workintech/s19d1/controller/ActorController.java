package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
@Slf4j
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> getAll() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor getById(@PathVariable Long id) {
        return actorService.findById(id);
    }

    @PostMapping
    public Actor create(@RequestBody Actor actor) {
        return actorService.save(actor);
    }

    @PutMapping("/{id}")
    public Actor update(@PathVariable Long id, @RequestBody Actor actor) {
        Actor existing = actorService.findById(id);

        existing.setFirstName(actor.getFirstName());
        existing.setLastName(actor.getLastName());
        existing.setGender(actor.getGender());
        existing.setBirthDate(actor.getBirthDate());
        existing.setMovies(actor.getMovies());

        return actorService.save(existing);
    }

    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return actor;
    }
}
