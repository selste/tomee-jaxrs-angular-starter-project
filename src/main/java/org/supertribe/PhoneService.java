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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
        final TypedQuery<Carrier> query = entityManager.createNamedQuery(Carrier.FIND_ALL, Carrier.class);

        List<Carrier> result = query.getResultList();

        return result;
    }
}
