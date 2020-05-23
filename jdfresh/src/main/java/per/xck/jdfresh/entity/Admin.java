package per.xck.jdfresh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

    @Column
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

}
