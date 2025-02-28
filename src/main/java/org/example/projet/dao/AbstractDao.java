package org.example.projet.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class AbstractDao {

    @Autowired
    private EntityManager entityManager;
    public JPAQueryFactory getFactory()
    {
        return new JPAQueryFactory(entityManager);
    }


}
