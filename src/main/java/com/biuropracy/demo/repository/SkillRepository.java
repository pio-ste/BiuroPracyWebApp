package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    List<Skill> findByUserIdUser(Integer user);
}
