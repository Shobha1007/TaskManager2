package com.techsteed.TaskManager.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends Auditable<String>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(length = 100)
    private String name;

    @Column(nullable = false, length = 512)
    private String password;

    @Value("true")
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="recordId")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="Id")})
    private List<Role> roles = new ArrayList<>();

    public User(String createdBy, Date createdAt, String modifiedBy, Date modifiedAt, String email, String name, String password, Boolean active, List<Role> roles) {
        super(createdBy, createdAt, modifiedBy, modifiedAt);
        this.email = email;
        this.name = name;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User(String createdBy, Date createdAt, String modifiedBy, Date modifiedAt, String email, String name, String password, Boolean active) {
        super(createdBy, createdAt, modifiedBy, modifiedAt);
        this.email = email;
        this.name = name;
        this.password = password;
        this.active = active;
    }

    public User() {
        super();
    }
}
