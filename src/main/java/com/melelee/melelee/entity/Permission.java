package com.melelee.melelee.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限值
 *
 * @author - melelee
 */
@Data
@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = -5979636077639378677L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "parent_id", updatable = false)
    private long parentId;

    /**
     * 权限值
     */
    @Column(nullable = false, unique = true, length = 32)
    private String name;

    /**
     * 描述
     */
    @Column(length = 140)
    private String description;

    private int weight;

    @Version
    private Integer version;

}
