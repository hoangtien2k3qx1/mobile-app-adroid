package com.hoangtien2k3.appapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String userType;  // facebook or defaults
    private String password;

    public User(String name, String email, String userType, String password) {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

}
