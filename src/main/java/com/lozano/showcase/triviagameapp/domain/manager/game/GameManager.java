package com.lozano.showcase.triviagameapp.domain.manager.game;

import com.lozano.showcase.triviagameapp.domain.model.Game;
import com.lozano.showcase.triviagameapp.domain.model.LiveQuestion;
import com.lozano.showcase.triviagameapp.domain.model.Question;
import com.lozano.showcase.triviagameapp.domain.model.Quiz;

public interface GameManager {

    Game createGame(String quizId, String requesterUserId);

    void CancelGame(String gameId, String requesterUserId);

    Game joinGameWithJoinId(String joinId, String requesterUserID);

    LiveQuestion startGame(String gameId, String requesterUserId);
    
    LiveQuestion submitAndGetNextQuestion(String gameId, String requesterUserId);



}
