package games.genericgames;

import java.util.ArrayList;
import games.players.Player;
import java.util.Scanner;

public class TicTacToe extends AbstractGame implements Game{

    private String[][] tab;
    private int lastMove; 

    /**
     * Constructeur de la classe
     * @param player1 instance représentant le premier joueur
     * @param player2 instance représentant le deuxième joueur
     */
    public TicTacToe(Player player1, Player player2){
        super(player1, player2);
        this.tab = new String[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                this.tab[i][j]="-";
            }
        }
    }
    
    /**
     * @return le joueur courant
     */
    @Override
    public Player getCurrentPlayer() {
        return super.currentPlayer;
    }

    /**
     * @return le joueur ayant joué le dernier coup
     */
    public Player getLastPlayer(){
        Player lastPlayer;
        if(this.currentPlayer == this.player1){
            lastPlayer = this.player2;
		} else {
			lastPlayer = this.player1;
		}
		return lastPlayer;
	}

    /**
     * exécute le mouvement et change le joueur courant
     */
    @Override
    public void doExecute(int move){
        this.lastMove = move;
        if(this.currentPlayer == this.player1){
            this.tab[row(move)][column(move)]="X";
		}
		else{
            this.tab[row(move)][column(move)]="O";
		}
    }

    /**
     * @return le numéro de la colonne correspondant au mouvement
     */
    public int row(int move){
        return (move/3);
    }
    
    /**
     * @return le numéro de la ligne correspondant au mouvement
     */
    public int column(int move){
        return (move%3);
    }

    /**
     * retourne vrai si le mouvement passé en paramètre est valide
     */
    @Override
    public boolean isValid(int move){
		if(move > 8 || move < 0 || this.tab[row(move)][column(move)]!="-"){
			return false;
		}
		return true;
	}

    /**
     * retourne un tableau contenant la liste de tous les coups possibles
     */
    @Override
    public ArrayList<Integer> validMoves(){
        ArrayList<Integer> listMove = new ArrayList<Integer>();
        int possibleMove=0;
        for(int i=0; i<this.tab.length; i++){
            for(int j=0; j<this.tab.length; j++){
                if(this.tab[i][j]=="-"){
                    possibleMove=3*i+j;
                    listMove.add(possibleMove);
                }
            }
        }
        return listMove;
    }

    /**
     * retourne le nom du joueur courant si la situation est gagante pour qqn ou perdante pour tous
     */
    @Override
    public Player getWinner(){
        if(wins(getLastPlayer(),0, column(this.lastMove), 1, 0)==true){
            return getLastPlayer();
        }
        if(wins(getLastPlayer(), row(this.lastMove),0, 0, 1)==true){
            return getLastPlayer();
        }
        if(wins(getLastPlayer(),0, 0, 1, 1)==true){
            return getLastPlayer();
        }
        if(wins(getLastPlayer(), 0,2, 1, -1)==true){
            return getLastPlayer();
        }
        return null;
    }

    /**
     * retourne vrai si la situation est gagante pour le joueur courant
     */
    public boolean wins(Player j, int r, int c, int deltaR, int deltaC){
        String symbol;
        if(j==this.player1){
            symbol="X";
        }
        else{
            symbol="O";
        }
        if(this.tab[r][c]==this.tab[r+deltaR][c+deltaC] && this.tab[r][c]==this.tab[r+2*deltaR][c+2*deltaC] && this.tab[r][c]==symbol){  
            return true;
        }
        return false;
    }

    /**
     * vérifie si la partie est finie
     * @return un booléen qui vaut vrai si la partie est finie et faux sinon
     */
    @Override
    public boolean isOver(){
        if(this.validMoves().isEmpty() || this.getWinner()==getLastPlayer()){
            return true;
        }
        return false;
    }

    /**
     * Affiche le jeu sous forme de grille
     */
    public void grid(){
        for(int i=0; i<3; i++){
            System.out.println("");
            for(int j=0; j<3; j++){
                System.out.print(" " + this.tab[i][j]);
            }
        }
    }

    /**
     * Retourne le mouvement passé en paramètre sous forme de chaine de caractères
     */
    @Override
    public String moveToString(int move) {
        return Integer.toString(move);
    }

    /**
     * affiche un état de jeu
     */
    @Override
    public String situationToString() {
        String string = "";
        for (int i=0; i<3; i++) {
            string += System.lineSeparator();
            for (int j=0; j<3; j++) {
                string += this.tab[i][j] + ' ';
            }
        }
        string += System.lineSeparator();
        return string;
    }

    /**
     * copie le jeu afin de différencier deux états de partie
     */
    @Override
    public TicTacToe copy() {
        TicTacToe res = new TicTacToe(super.getPlayer1(), super.getPlayer2());
        String[][] board = new String[3][3];
        for(int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                board[i][j] = this.tab[i][j];
            }
        }
        res.tab = board;
        res.currentPlayer = super.getCurrentPlayer();
        return res;
    }

    /**
     * pas utile, mais doit être redéclaré à cause de l'héritage
     */
    @Override
    public void viewInformations() {
        ;
    }
}
