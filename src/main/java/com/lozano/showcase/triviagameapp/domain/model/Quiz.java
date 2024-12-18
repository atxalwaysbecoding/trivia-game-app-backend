package com.lozano.showcase.triviagameapp.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "QUIZ")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "QUIZ_ID")
    private String id;

    @Column(name = "AUTHOR_ID")
    private String authorId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESC")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "QUIZ_ID", referencedColumnName = "QUIZ_ID")
    private List<Question> questions;

    @CreationTimestamp
    @Column(name = "CREATE_DATETIME")
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name = "MODIFIED_DATETIME")
    private LocalDateTime lastModifiedDateTime;

    @Column(name = "LOCKED_FROM_EDIT")
    private boolean isLocked;

}
