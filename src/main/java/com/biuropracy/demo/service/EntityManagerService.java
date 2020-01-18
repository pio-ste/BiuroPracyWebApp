package com.biuropracy.demo.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class EntityManagerService {

    private SessionFactory sessionFactory;

    @Autowired
    public EntityManagerService(EntityManagerFactory entityManagerFactory) {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("Entity Manager Factory is not a hibernate factory");
        }
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
