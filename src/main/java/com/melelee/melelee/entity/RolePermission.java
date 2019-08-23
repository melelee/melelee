package com.melelee.melelee.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色-权限值
 *
 * @author - melelee
 */
@Data
@Entity
@Table(name = "role_permission")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -5979636077649378677L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_id")
    private long roleId;


    @Column(name = "permission_id")
    private long permissionId;
}
