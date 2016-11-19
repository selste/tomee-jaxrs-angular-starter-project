package org.supertribe;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CarrierIT {

    EntityManager em;
    EntityTransaction tx;

    @Before
    public void init() throws Exception {
        System.out.println("init");

        em = Persistence.createEntityManagerFactory("test").createEntityManager();
        tx = em.getTransaction();
    }


    @Test
    public void createCarrier() throws Exception {
        System.out.println("init");

        Carrier carrier = new Carrier();
        carrier.setName("T-Mobile");

        tx.begin();
        em.merge(carrier);
        tx.commit();

        final TypedQuery<Carrier> query = em.createNamedQuery(Carrier.FIND_ALL, Carrier.class);

        assertTrue("Expected one entity", 1 == query.getResultList().size());
    }
}
