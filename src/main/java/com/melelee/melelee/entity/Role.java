package com.melelee.melelee.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 角色
 *
 * @author - melelee
 */
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = -1153854616385727165L;

    /**
     * The constant STATUS_NORMAL.
     */
    public static int STATUS_NORMAL = 0;
    /**
     * The constant STATUS_CLOSED.
     */
    public static int STATUS_CLOSED = 1;

    /**
     * The constant ROLE_ADMIN.
     */
    public static String ROLE_ADMIN = "admin";

    /**
     * The constant ADMIN_ID.
     */
    public static long ADMIN_ID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false, length = 32)
    private String name;

    @Column(length = 140)
    private String description;

    private int status;

    @Transient
    private List<Permission> permissions;

}
