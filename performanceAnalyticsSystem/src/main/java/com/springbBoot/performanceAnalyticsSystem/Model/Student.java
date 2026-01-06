package com.springbBoot.performanceAnalyticsSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
public class Student
{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getPyMarks() {
            return pyMarks;
        }

        public void setPyMarks(int pyMarks) {
            this.pyMarks = pyMarks;
        }

        public int getCheMarks() {
            return cheMarks;
        }

        public void setCheMarks(int cheMarks) {
            this.cheMarks = cheMarks;
        }

        public int getMathMarks() {
            return mathMarks;
        }

        public void setMathMarks(int mathMarks) {
            this.mathMarks = mathMarks;
        }

        @NotBlank(message="Password cannot be empty")
        @Size(min=8,max=32,message="Password must be between 8 to 32 Characters")
        @jakarta.validation.constraints.Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                message = "Password must contain letters, numbers, and special characters")
        @Column(nullable = false,unique = false)
        private String password;
        @Column(nullable = false)
        private String name;

        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[a-z.]+\\.com$",message="Email must end with.com")
        @Column(nullable = false,unique = true)
        private String email;
        private int pyMarks;

    public void setTotal(int total) {
        this.total = total;
    }

    private int cheMarks;
        private int mathMarks;
        @Transient
        public int total;
        public int getTotal() {
            return pyMarks+cheMarks+mathMarks;
        }

    }


