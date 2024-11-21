package com.zekiyetekin.surveyhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zekiyetekin.surveyhub.enumuration.role.RoleEnum;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @JsonIgnore
    private Integer id;
    private String name;
    private String job;
    private Integer age;
    private String mail;
    private String password;
    private  boolean isActive;
    private RoleEnum role;

    public UserDto(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.job = builder.job;
        this.age = builder.age;
        this.mail = builder.mail;
        this.password = builder.password;
        this.isActive = builder.isActive;
        this.role = builder.role;
    }

    public static class Builder{
        private Integer id;
        private String name;
        private String job;
        private Integer age;
        private String mail;
        private String password;
        private  boolean isActive;
        private RoleEnum role;

        public Builder() { }

        public Builder(Integer id, String name,
        String job, Integer age, String mail,
        String password, boolean isActive, RoleEnum role){
            this.id = id;
            this.name = name;
            this.job = job;
            this.age = age;
            this.mail = mail;
            this.password = password;
            this.isActive = isActive;
            this.role = role;
        }

        public Builder id(Integer id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder job(String job){
            this.job = job;
            return this;
        }
        public Builder age(Integer age){
            this.age = age;
            return this;
        }
        public Builder mail(String mail){
            this.mail = mail;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder isActive(Boolean isActive){
            this.isActive = isActive;
            return this;
        }
        public Builder role(RoleEnum role){
            this.role = role;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }
    }


}
