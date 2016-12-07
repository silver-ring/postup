package com.appzone.postup.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Mohamed Morsy
 */
@Entity
public class UserEntity implements PostUpEntity {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    private UserRole userRole;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public static enum UserRole {
        ROLE_USER, ROLE_ADMIN
    }

}
