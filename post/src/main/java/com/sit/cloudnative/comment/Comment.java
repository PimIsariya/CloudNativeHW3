package com.sit.cloudnative.comment;

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
import com.sit.cloudnative.post.Post;
import com.sit.cloudnative.user.User;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowSetters = true, allowGetters = true)
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    // private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @NotBlank
    private String text;

    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User userId;

    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "postId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post postId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updatedAt;

    public Comment(){

    }
    public Comment(String text){
        this.text = text;
    }

    public int getCommentId(){
        return commentId;
    }

    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }

    // public String getUserName(int id){
    //     String userName = ""+user.getFirstName()+" "+user.getLastName();
    //     return  userName;
    // }
    public User getUser(){
        return userId;
    }
    public void setUser(User user_id){
        this.userId = user_id;
    }

    public Post getPost(){
        return postId;
    }
    public void setPost(Post post_id){
        this.postId = post_id;
    }
    
    public Date getCreatedAt(){
        return createdAt;
    }
    public Date getUpdatedAt(){
        return updatedAt;
    }

}