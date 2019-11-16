package mate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {

    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "userGroup")
    private long userGroup;



    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(String name) {
        this.setId(-1);
        this.setName(name);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(long userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userGroup=" + userGroup +
                '}';
    }
}
