package com.example.mywebsite.services.post;

import com.example.mywebsite.entity.Post;


import java.util.Collection;


public interface PostService {
    Collection<Post> getAllPosts();
    Post getPostById(Long id);
    Post addPost(Post post);
    Post updatePost(Post post);
    Boolean removePost(Long id);
}
