package org.example.entity;

import java.time.LocalDateTime;

public class Role {

    private Integer id;
    private String roleCode;
    private String name;
    private Integer status;
    private LocalDateTime createdAt;

    //CONSTRUCTORS
    public Role() {
    }

    public Role(String roleCode, String name, Integer status, LocalDateTime createdAt) {
        this.roleCode = roleCode;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
    }

    //GETTER Y SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
