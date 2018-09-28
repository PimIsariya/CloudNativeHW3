package com.sit.cloudnative.post;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sit.cloudnative.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowSetters = true, allowGetters = true)

@Entity
@Table(name="post")
public class Post implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int postId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User userId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at",  nullable = false)
    private Date updatedAt;



    public Post(){
        super();
    }
    public Post(String title, String description, Date createdAt, Date updatedAt){
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getPostId(){
        return postId;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public Date getCreatedAt(){
        return createdAt;
    }
    public Date getUpdatedAt(){
        return updatedAt;
    }
    
	public void setUser(User user_id) {
        this.userId = user_id;
    }
    public User getUser(){
        return userId;
    }
	public void setUser(int user_id) {
        
	}
    
}