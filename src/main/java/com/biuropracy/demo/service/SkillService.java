package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Skill;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> findSkillByUserId(Integer user) {
        return skillRepository.findByUserIdUser(user);
    }

    public Skill getSkillById(Integer id) {
        Optional<Skill> skillOpt = skillRepository.findById(id);
        if (skillOpt.isPresent()) {
            return skillOpt.get();
        } else {
            throw new RuntimeException("Id skill nie znalezione");
        }
    }

    public Skill updateSkill(Skill skill) {
        Optional<Skill> skillOpt = skillRepository.findById(skill.getIdSkill());
        if (skillOpt.isPresent()) {
            Skill newSkill = skillOpt.get();
            newSkill.setSkillName(skill.getSkillName());
            newSkill = skillRepository.save(newSkill);
            return newSkill;
        } else {
            skill = skillRepository.save(skill);
            return skill;
        }
    }

    public Skill createSkill(Skill skill, User user) {
        skill.setUser(user);
        skill = skillRepository.save(skill);
        return skill;
    }

    public void deleteSkillById(Integer id) {
        Optional<Skill> skillOpt = skillRepository.findById(id);
        if (skillOpt.isPresent()) {
            skillRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brak skill o tym id");
        }
    }
}
