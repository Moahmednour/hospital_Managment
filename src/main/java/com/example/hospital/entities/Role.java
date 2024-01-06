package com.example.hospital.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;

@Entity
@Getter @Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;// Should be in the format "ROLE_USER", "ROLE_ADMIN", etc.

    @Override
    public String getAuthority() {
        return name;
    }
}
