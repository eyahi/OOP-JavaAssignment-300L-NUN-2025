package com.tictactoe.model;
public class Board {
    private char[][]grid;
    public Board(){
        grid = new char[3][3];
        resetBoard();
    }
    public boolean mark(int row, int col, char player){
        if (grid[row][col] == ' '){
            grid[row][col] = player;
            return true;
        }
        return false; 
    }
    public char getCell(int row, int col){
        return grid[row][col];
    }
    public boolean isFull(){
        for (int r = 0; r < 3; r++)
        for (int c = 0; c < 3; c++)
        if (grid[r][c] == "") return false;
        else return true;
    }
    public void reset(){
        for(int r = 0; r < 3; r++)
        for(int c = 0; c < 3; c++)
        grid[r][c] = "";
    }
    }

