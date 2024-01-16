package com.example.mywebsite.controller;

import com.example.mywebsite.entity.Post;
import com.example.mywebsite.model.Response;
import com.example.mywebsite.services.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Response> getPosts(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Posts received")
                        .data(Map.of("objects", postService.getAllPosts()))
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addPost(@RequestBody Post post){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Added posts")
                        .data(Map.of("object", postService.addPost(post)))
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updatePost(@RequestBody Post post){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Updated post")
                        .data(Map.of("object", postService.updatePost(post)))
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deletePostById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Deleted post")
                        .data(Map.of("object", postService.removePost(id)))
                        .build()
        );
    }


    @GetMapping("/post/{id}")
    public ResponseEntity<Response> getPostById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Posts received")
                        .data(Map.of("object", postService.getPostById(id)))
                        .build()
        );
    }

}
