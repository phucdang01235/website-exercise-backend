package com.example.mywebsite.services.post;

import com.example.mywebsite.entity.Post;
import com.example.mywebsite.repo.PostRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    @Override
    public Collection<Post> getAllPosts() {
        log.info("getAllPosts");
        return postRepo.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        log.info("getPostById : " + id);
        if(postRepo.findById(id).isEmpty() )
            throw new RuntimeException("Post not found");
        return postRepo.findById(id).get();
    }

    @Override
    public Post addPost(Post post) {
        log.info("Adding post : " + post.getTitle());
        return postRepo.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        log.info("Updating post : " + post.getTitle());
        if(postRepo.findById(post.getIdPost()).isEmpty()){
            throw new RuntimeException("Post not found");
        }
        return postRepo.save(post);
    }

    @Override
    public Boolean removePost(Long id) {
        log.info("Removing post : " + id);
        postRepo.deleteById(id);
        return true;
    }
}
