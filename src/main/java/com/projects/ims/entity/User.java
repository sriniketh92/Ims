package com.projects.ims.entity;

import com.projects.ims.audit.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import lombok.Data;


@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String contact;
    private boolean enabled;
    private boolean accountNonLocked;

}
