package org.supertribe;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = Carrier.FIND_ALL, query = "SELECT c FROM Carrier c")
})
public class Carrier {

    public static final String FIND_ALL = "Carrier.FIND_ALL";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
