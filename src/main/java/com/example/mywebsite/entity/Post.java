package com.example.mywebsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPost;
    private String title;
    private String permalink;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "idCategory")
    private Category category;

    private String postImgPath;
    private String excerpt;
    private String content;

    private boolean isFeatured;
    private long views;
    private String status;
    private Date createAt;
}
