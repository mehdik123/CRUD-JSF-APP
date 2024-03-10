package com.example.demo13;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email ;

    private boolean edit ;
    public Employee(){

    }

    public Employee( String name , String email  ){
        this.name = name ;
        this.email = email ;
        this.edit=false;

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isEdit() {
        return edit;
    }
    public void setEdit(boolean edit) {
        this.edit = edit;
        System.out.println(this.name);
        System.out.println(this.edit);
    }
    public void setId(long id) {
        this.id=id;
    }
    public long getId() {
        return id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", edit=" + edit +
                '}';
    }
}
