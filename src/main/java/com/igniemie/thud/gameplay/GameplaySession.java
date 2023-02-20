package com.igniemie.thud.gameplay;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.igniemie.thud.model.Board;
import com.igniemie.thud.model.GameStatus;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.model.PlayerType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GameplaySession {

//    TODO: separate autogenerated id below needed for DB
    private UUID gameUUID;
    private Round round;
    private Board board;
    private Player player1;
    private Player player2;
    private GameStatus status;

    public GameplaySession(Player player) {
        this.gameUUID = UUID.randomUUID();
        this.round = Round.FIRST;
        this.board = new Board();
        this.player1 = player;
        this.player1.setType(PlayerType.DWARF);
        this.player2 = null;
        this.status = GameStatus.NEW;
    }

    public void joinToGame(Player player) {
        this.player2 = player;
        this.setStatus(GameStatus.IN_PROGRESS);
    }

    public GameplayDTO toGameplayDTO() {
        return new GameplayDTO(
                this.gameUUID.toString(),
                status.toString(),
                player1);
    }

    public Map.Entry<String, String> toGameplayListEntity() {
        return Map.entry(gameUUID.toString(), player1.getNickname());
    }
}