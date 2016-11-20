/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.supertribe;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.lang.Math.abs;

@Stateless
@Path("/gallery")
@Produces(MediaType.APPLICATION_JSON)
public class PhoneService {

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Path("phones")
    public List<Phone> getPhones() {
        final TypedQuery<Phone> query = entityManager.createNamedQuery(Phone.FIND_ALL, Phone.class);

        List<Phone> result = query.getResultList();

        return result;
    }

    @GET
    @Path("carriers")
    public List<Carrier> getCarriers() {
        // Because we can ...
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Carrier> criteriaQuery = criteriaBuilder.createQuery(Carrier.class);
        Root<Carrier> root = criteriaQuery.from(Carrier.class);
        CriteriaQuery<Carrier> all = criteriaQuery.select(root);

        TypedQuery<Carrier> typedQuery = entityManager.createQuery(all);

        return typedQuery.getResultList();
    }

    @POST
    @Path("save")
    public Phone savePhone(Phone phone) {
        Phone entity = new Phone();
        entity.setAge(phone.getAge());
        entity.setName(phone.getName());
        entity.setCarrier(phone.getCarrier());
        entity.setSnippet(phone.getSnippet());

        long id = abs(generateHashCode(phone.getAge(), phone.getName(), phone.getCarrier(), phone.getSnippet()));

        entity.setId(new Long(id));

        System.out.println(entity.toString());

        /// entityManager.merge(entity);
        entityManager.persist(entity);

        return phone;
    }

    int generateHashCode(int age, String name, Carrier carrier, String snippet) {
        int result = 7;
        result = 17 * result + age;
        result = 17 * result + (carrier != null ? carrier.hashCode() : 0);
        result = 17 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
