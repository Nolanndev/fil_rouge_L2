package games.genericgames;

import games.players.Player;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbstractGame implements Game{
    
    protected Player player1, player2, currentPlayer;
    protected boolean error;

    public AbstractGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = this.player1;
    }

    protected abstract void doExecute(int move);

    /**
     * exécute le mouvement et change le joueur courant
     */
    public void execute(int move){
        this.doExecute(move);
        if (this.currentPlayer == this.player1) {
            this.currentPlayer = this.player2;
        } else {
            this.currentPlayer = this.player1;
        }
    }

    /**
     * retourne l'autre joueur que le joueur courant
     */
    public Player getOpponentPlayer() {
        return this.currentPlayer.equals(this.player1) ? this.player2 : this.player1;
    }

    /**
     * retourne le joueur courant
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * retourne le premier joueur
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * retourne le deuxième joueur
     */
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * méthodes qui doivent être redéfinies dans les classes héritières
     */
    public abstract ArrayList<Integer> validMoves();
    public abstract boolean isValid(int move);
    public abstract boolean isOver();
    public abstract Player getWinner();
    public abstract String moveToString(int move);
    public abstract String situationToString();
    public abstract Game copy();


    // méthodes rajoutées pour l'esthetique du jeu
    // public abstract void startGame();
    public abstract void viewInformations();
    
    public void clearConsole() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    /**
     * demande un nombre entier positif à l'utilisateur
     */
    public int getPositiveInteger(Scanner scanner) {
        this.error = true;
        String str;
        int intInput = 1;
        while (this.error) {
            str = scanner.next();
            try {
                intInput = Integer.parseInt(str);
                if (intInput <= 0) {
                    System.out.print("Vous devez entrer un nombre positif, réessayez : ");
                } else {
                    this.error = false;
                }
            } catch (Exception e) {
                System.out.print("Vous devez entrer un nombre positif, réessayez : ");
            }
        }
        return intInput;
    }
}