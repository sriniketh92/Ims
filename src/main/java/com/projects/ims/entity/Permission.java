package com.projects.ims.entity;

import com.projects.ims.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permissions")
@Data
public class Permission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;
    @Column(unique = true)
    private String permissionName;
    private String permissionDescription;
    private boolean isEnabled;
}
