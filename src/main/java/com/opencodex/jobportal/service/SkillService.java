package com.opencodex.jobportal.service;

import com.opencodex.jobportal.entity.Skill;
import com.opencodex.jobportal.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getByName(String name) {
        return skillRepository.findByName(name);
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public void deleteSkill(UUID id) {
        skillRepository.deleteById(id);
    }
}
