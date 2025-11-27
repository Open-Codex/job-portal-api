package com.opencodex.jobportal.service;

import com.opencodex.jobportal.dto.skill.SkillRequest;
import com.opencodex.jobportal.dto.skill.SkillResponse;

import java.util.List;
import java.util.UUID;

public interface SkillService {

    SkillResponse createSkill(SkillRequest request);

    SkillResponse getSkillById(UUID id);

    List<SkillResponse> getAllSkills();

    SkillResponse updateSkill(UUID id, SkillRequest request);

    void deleteSkill(UUID id);
}
