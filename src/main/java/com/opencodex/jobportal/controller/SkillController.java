package com.opencodex.jobportal.controller;

import com.opencodex.jobportal.entity.Skill;
import com.opencodex.jobportal.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getAll() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{name}")
    public Optional<Skill> getByName(@PathVariable String name) {
        return skillService.getByName(name);
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable UUID id) {
        skillService.deleteSkill(id);
    }
}
