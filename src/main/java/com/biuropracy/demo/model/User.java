package com.biuropracy.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser")
    private Integer id;

    @Column(name = "name",nullable = false)
    @NotEmpty(message = "Wpisz swoje imię")
    private String name;

    @Column(name = "last_name",nullable = false)
    @NotEmpty(message = "Wpisz swoje nazwisko")
    private String lastName;

    @Column(name = "email",nullable = false, unique = true)
    @NotEmpty(message = "Wpisz swój adres email")
    @Email(message = "Zły adres email")
    private String email;

    @Column(name = "password",nullable = false)
    @NotEmpty(message = "Wpisz hasło")
    @Size(min=4, message = "Minimum 4 znaki")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns={@JoinColumn(name="idUser")},
            inverseJoinColumns={@JoinColumn(name="idRole")})

    private Set<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
