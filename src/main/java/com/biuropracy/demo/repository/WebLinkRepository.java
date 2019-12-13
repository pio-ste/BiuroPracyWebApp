package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebLinkRepository extends JpaRepository<WebLink, Integer> {
}
