package mate.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UsersDataSet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    public UsersDataSet() {
    }

    public UsersDataSet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
