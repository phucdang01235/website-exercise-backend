package com.example.mywebsite.dtos;

import com.example.mywebsite.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private String title;
    private String permalink;

    @JsonProperty("category_id")
    @NotNull(message = "Category id is required")
    private Long categoryId;

    @JsonProperty("post_img_path")
    @NotNull(message = "Image path is required")
    private String postImgPath;

    private String excerpt;
    private String content;
}
