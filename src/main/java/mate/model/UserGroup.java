package mate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_groups")
public class UserGroup implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "group", nullable = false, unique = true)
    private String groupName;

@Column(name = "group1")
    private String group1Name;



    public UserGroup() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public String getGroup1Name() {
        return group1Name;
    }

    public void setGroup1Name(String group1Name) {
        this.group1Name = group1Name;
    }
}
