package com.sit.cloudnative.UserServiceForAdapter;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
// @JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowSetters = true)
@Entity
@Table(name = "user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    // @CreatedDate
    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(name = "create_at")
    // private Date createdAt;

    // @LastModifiedDate
    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(name = "update_at")
    // private Date updatedAt;

    public User(){

    }

    public User(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getFirstName(){
        return firstname;
    }
    public void setFirstName(String firstname){
        this.firstname = firstname;
    }

    public String getLastName(){
        return lastname;
    }
    public void setLastName(String lastname){
        this.lastname = lastname;
    }

    // public Date getCreatedAt(){
    //     return createdAt;
    // }
    // public Date getUpdatedAt(){
    //     return updatedAt;
    // }

}