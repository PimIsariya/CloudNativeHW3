package com.sit.cloudnative.post;
import java.util.List;
import java.util.Optional;
import com.sit.cloudnative.user.User;
import com.sit.cloudnative.user.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

        @Autowired
        private PostRepository postRepository;
        
        private Post postRequest;

        @Autowired
        private UserAdapter userAdapter;


        public List<Post> getAllPost() {           
            return postRepository.findAll();
        }
        /*ลองadd เพิ่มนะ */
        // public Object getListAllPost(Post post) {
        //     Object[] listAllPost = new Object[2];
        //     listAllPost[0] = getAllPost();
        //     listAllPost[1] = post.getUser();
        //     return postRepository.findAll();
        // }

        public Post create(int user_id, Post post){
            post.setUser(userAdapter.getUserDetail(user_id));
            return postRepository.save(post);
        }

        // public Optional<Post> getPostById(int id) {
        //     return postRepository.findById(id);
        // }

        public void deletePostById(int id){
            postRepository.deleteById(id);
        
        }

        // public Optional<Post> updatePostById(int id) {
        //     return postRepository.findById(id).map(post -> {
        //         post.setTitle(postRequest.getTitle());
        //         post.setDescription(postRequest.getDescription());
        //         return postRepository.save(post);
        //     });
        // }
}