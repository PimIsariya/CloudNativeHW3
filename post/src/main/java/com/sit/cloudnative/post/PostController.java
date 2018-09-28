package com.sit.cloudnative.post;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.sit.cloudnative.user.User;
import com.sit.cloudnative.user.UserAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ResourceBanner;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PostController{

    @Autowired
    @Qualifier("postService")
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserAdapter UserAdapter;

    @RequestMapping(value = "/allpost", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPostList(){
        List<Post> posts = postService.getAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
    // @RequestMapping(value = "/allpost", method = RequestMethod.GET)
    // public ResponseEntity<List<Post>> getPostList(@Valid Post post){
    //     Object posts = postService.getListAllPost(post);
    //     return new ResponseEntity<List<Post>>(HttpStatus.OK);
    // }

    @RequestMapping(value = "/user/{userid}/post", method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@PathVariable("userid") int userid, @Valid @RequestBody Post post){
        Post post_object = postService.create(userid,post);
        return new ResponseEntity<Post>(post_object, HttpStatus.OK);
    }

    // @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    // public ResponseEntity<Optional<Post>> getPost(@PathVariable("id") int id) {
    //     Optional<Post> post = postService.getPostById(id);
    //     return new ResponseEntity<Optional<Post>>(post, HttpStatus.OK);
    // }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Post> deletePost(@PathVariable("id") int id){
        postService.deletePostById(id);
        return new ResponseEntity<Post>(HttpStatus.OK);
    }

    @RequestMapping(value="/post/{id}", method = RequestMethod.PUT)
    public Optional<Post> updatePost(@PathVariable int id, @Valid @RequestBody Post postRequest) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            return postRepository.save(post);
        });
    }
    
}