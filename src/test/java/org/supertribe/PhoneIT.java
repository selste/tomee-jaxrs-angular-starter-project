package org.supertribe;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PhoneIT {

    EntityManager em;
    EntityTransaction tx;

    Carrier carrier;

    @Before
    public void init() throws Exception {
        System.out.println("init");

        em = Persistence.createEntityManagerFactory("test").createEntityManager();
        tx = em.getTransaction();

        carrier = new Carrier();
        carrier.setId(new Long(1));
        carrier.setName("Dummy Carrier");

        tx.begin();
        em.persist(carrier);
        tx.commit();
    }

    @Test
    public void createPhone() throws Exception {
        System.out.println("init");

        Phone phone = new Phone();
        phone.setAge(42);
        phone.setName("Motorola Defy");
        phone.setCarrier(carrier);
        phone.setSnippet("Snippet");
        phone.setId(new Long(4));

        tx.begin();
        em.merge(phone);
        tx.commit();

        TypedQuery<Phone> query = em.createNamedQuery(Phone.FIND_ALL, Phone.class);

        assertTrue("Expected one entity", 1 == query.getResultList().size());
    }
}
