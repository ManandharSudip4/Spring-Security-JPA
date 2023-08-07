package com.mstech.springsecurityjpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String userName;
  private String password;
  private boolean active;
  private String roles;

  // Default constructor is required if we create a instance of class empty.
  public Users() {}

  public Users(
    int id,
    String userName,
    String password,
    boolean active,
    String roles
  ) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.active = active;
    this.roles = roles;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }
}
