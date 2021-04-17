package io.vepo.tutorial.quarkus.issue;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Transactional
@ApplicationScoped
public class Issues {
    @PersistenceContext
    EntityManager em;

    public Issue create(@Valid Issue issue) {
        em.persist(issue);
        em.detach(issue);
        return issue;
    }

    public Issue get(int issueId) {
        return em.find(Issue.class, issueId);
    }

}
