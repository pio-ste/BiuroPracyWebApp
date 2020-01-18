package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebLinkRepository extends JpaRepository<WebLink, Integer> {

    List<WebLink> findByUserIdUser(Integer user);

    WebLink findWebLinkByUserIdUser(Integer id);
}
