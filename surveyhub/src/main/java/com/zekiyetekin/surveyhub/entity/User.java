package com.zekiyetekin.surveyhub.entity;

import com.zekiyetekin.surveyhub.enumuration.role.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    private String job;
    private Integer age;

    @Column(unique = true)
    private String mail;
    private String password;
    private  boolean isActive;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword(){ return password; }
    @Override
    public String getUsername(){ return mail; }
    @Override
    public boolean isAccountNonExpired(){ return true; }
    @Override
    public boolean isAccountNonLocked(){ return true; }
    @Override
    public boolean isCredentialsNonExpired(){ return true; }
    @Override
    public boolean isEnabled(){ return true; }

}
