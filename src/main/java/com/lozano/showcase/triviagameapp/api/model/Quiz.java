package com.lozano.showcase.triviagameapp.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Quiz {

    private String id;

    private String authorId;

    @NotBlank(message = "title cannot be blank")
    @NotNull(message = "title cannot be null")
    @Pattern(regexp = "[a-zA-Z0-9 ]*", message = "title must not contain special characters")
    @Size(max = 60, message = "title exceeds max length")
    private String title;

    @NotBlank(message = "description cannot be blank")
    @NotNull(message = "description cannot be null")
    @Pattern(regexp = "[a-zA-Z0-9 ]*", message = "description must not contain special characters")
    @Size(max = 200, message = "description exceeds max length")
    private String description;

    private List<Question> questions;

    private LocalDateTime createDateTime;

    private LocalDateTime lastModifiedDateTime;
}
