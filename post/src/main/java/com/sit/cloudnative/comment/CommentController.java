package com.sit.cloudnative.comment;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.sit.cloudnative.post.Post;
import com.sit.cloudnative.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    @Qualifier("commentService")
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "/allcomment", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getCommetyList() {
        List<Comment> comments = commentService.getAllComment();
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/{userid}/post/{postid}/comment", method = RequestMethod.POST)
    public ResponseEntity<Comment> createComment(@PathVariable("userid") int userId, @PathVariable("postid") Post postId, @Valid @RequestBody Comment comment) {
        Comment comment_object = commentService.create(userId, postId, comment);
        return new ResponseEntity<Comment>(comment_object, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Comment>> getComment(@PathVariable("id") int id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return new ResponseEntity<Optional<Comment>>(comment, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") int id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<Comment>(HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
    public Optional<Comment> updateComment(@PathVariable int id, @Valid @RequestBody Comment commentRequest) {
        return commentRepository.findById(id).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        });
    }
}