package com.lozano.showcase.triviagameapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name = "GAME")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "GAME_ID")
    private String id;

    @Column(name = "HOST_USER_ID")
    private String hostUserId;

    @Column(name = "QUIZ_ID")
    private String quizId;

    @Enumerated(EnumType.ORDINAL)
    private GameStateEnum gameState;

    @CreationTimestamp
    @Column(name = "CREATE_DATETIME")
    private LocalDateTime createDateTime;

    @CreationTimestamp
    @Column(name = "STARTED_DATETIME")
    private LocalDateTime startedDateTime;

    @CreationTimestamp
    @Column(name = "ENDED_DATETIME")
    private LocalDateTime endedDateTime;

    @Column(name = "PARTICIPANTS_LIST")
    private String participants;

    @Transient
    public List<String> getParticipantsAsList(){
        List<String> parsedList = Arrays.asList(this.participants.split("\\|"));
        return new ArrayList<>();
    }

    //todo set participants
}
