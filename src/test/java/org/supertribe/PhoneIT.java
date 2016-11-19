package org.supertribe;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import static org.junit.Assert.assertTrue;

public class PhoneIT {

    EntityManager em;
    EntityTransaction tx;

    @Before
    public void init() throws Exception {
        System.out.println("init");

        em = Persistence.createEntityManagerFactory("test").createEntityManager();
        tx = em.getTransaction();
    }

    @Test
    public void createPhone() throws Exception {
        System.out.println("init");

        Phone phone = new Phone();
        phone.setAge(2);
        phone.setCarrier("AT&T");
        phone.setName("Xperia Z1");
        phone.setSnippet("Snippet");

        tx.begin();
        em.merge(phone);
        tx.commit();

        TypedQuery<Phone> query = em.createNamedQuery(Phone.FIND_ALL, Phone.class);

        assertTrue("Expected one entity", 1 == query.getResultList().size());
    }
}
