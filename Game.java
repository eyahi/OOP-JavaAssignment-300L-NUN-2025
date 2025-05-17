package com.tictactoe.controller;
import com.tictactoe.model.*;

public class Game {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private boolean GameOver;

    public Game(Player x, Player o) {
        this.board = new Board();
        this.playerX = x;
        this.playerO = o;
        this.currentPlayer = x;
        this.GameOver = false;
    }

    public boolean makeMove(int row, int col) {
        if (gameOver) return false;
        if(board.mark(row, col, currentPlayer.getSymbol())){
            return true;
        }
        return false;
    }
    public void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean GameOver() {
        return GameOver;
    }

    public void resetGame() {
        board.resetBoard();
        currentPlayer = playerX;
        GameOver = false;
    }

    public Board getBoard() {
        return board;
    }

    public void setGameOver(boolean status){
        gameOver = status;
    }
    public boolean isGameOver(){
        return gameOver;
    }
    }
}
