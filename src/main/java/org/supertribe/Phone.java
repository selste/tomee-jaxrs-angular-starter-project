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

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = Phone.FIND_ALL, query = "SELECT p FROM Phone p")
})
public class Phone {

    public static final String FIND_ALL = "Phone.FIND_ALL";

    @Id
    @GeneratedValue
    private Long id;

    private int age;

    @ManyToOne
    private Carrier carrier;

    private String name;

    private String snippet;

    public Phone() {}

    public Phone(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "age=" + age +
                ", carrier='" + carrier + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", snippet='" + snippet + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (age != phone.age) return false;
        if (!id.equals(phone.id)) return false;
        if (carrier != null ? !carrier.equals(phone.carrier) : phone.carrier != null) return false;
        return name != null ? name.equals(phone.name) : phone.name == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + age;
        result = 31 * result + (carrier != null ? carrier.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
