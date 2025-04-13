package games.genericgames;

import games.players.Player;

import java.util.ArrayList;

public interface Game {
    public Player getCurrentPlayer();
    public Player getPlayer1();
    public Player getPlayer2();
    public ArrayList<Integer> validMoves();
    public boolean isValid(int move);
    public void execute(int move);
    public boolean isOver();
    public Player getWinner();

    public String moveToString(int move);
    public String situationToString();

    public Game copy();
    public Player getOpponentPlayer();

    public void clearConsole();
    // public void startGame();
    public void viewInformations();

}