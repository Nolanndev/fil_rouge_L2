package games.genericgames;

import java.util.ArrayList;
import java.util.Scanner;

import games.players.Player;

public class Nim extends AbstractGame implements Game{

    private int maxMatchesPerLap;
    private int initialNbMatches;
    private int matchesAlive;
    protected Scanner scanner;

    /**
     * Constructeur qui passe les tests
     */
    public Nim(int n, int k, Player joueur1, Player joueur2) {
        super(joueur1, joueur2);
        this.initialNbMatches = n;
        this.maxMatchesPerLap = k;
        this.matchesAlive = this.initialNbMatches;
    }

    /**
     * Constructeur utilisé pour jouer au jeu
     */
    public Nim(int n, int k, Player joueur1, Player joueur2, Scanner scanner) {
        super(joueur1, joueur2);
        this.initialNbMatches = n;
        this.maxMatchesPerLap = k;
        this.matchesAlive = this.initialNbMatches;
        this.scanner = scanner;
    }

    /**
     * retourne le nombre d'allumettes de départ
     */
    public int getInitialNbMatches() {
        return this.initialNbMatches;
    }

    /**
     * retourne le nombre d'allumettes restantes dans la partie
     */
    public int getCurrentNbMatches() {
        return this.matchesAlive;
    }

    /**
     * retire le nombre d'allumettes du coup du tas d'allumettes
     */
    @Override
    public void doExecute(int move) {
        this.matchesAlive -= move;
    }

    /**
     * renvoie vrai si le coup est valide et faux sinon
     */
    @Override
    public boolean isValid(int coup) {
        if (coup <= 0 || coup > this.matchesAlive || coup > this.maxMatchesPerLap) {
            // System.out.println("Erreur, le coup n'est pas valide");
            return false;
        }
        // System.out.println("Le coup est correct");
        return true;
    }

    /**
     * renvoie vrai si la partie est finie, et faux sinon
     */
    @Override
    public boolean isOver() {
        if (this.matchesAlive <= 0) {
            return true;
        }
        return false;
    }

    /**
     * retourne le nom du joueur qui gagne et la partie et renvoie null s'il n'y a pas de gagnants
     */
    @Override
    public Player getWinner() {
        if (this.isOver()) {
            return super.getCurrentPlayer();
        }
        return null;
    }

    /**
     * renvoie le coup sous forme de chaine de caractères
     */
    @Override
    public String moveToString(int move) {
        return Integer.toString(move);
    }
    
    /**
     * renvoie la situation sous forme de chaine de caractères
     */
    @Override
    public String situationToString() {
        if (this.matchesAlive > 1) {
            return "Il reste " + this.matchesAlive + " allumettes";
        }
        return "Il reste 1 allumette";
    }

    /**
     * renvoie une copy du jeu
     */
    @Override
    public Nim copy() {
        Nim res = new Nim(this.initialNbMatches, this.matchesAlive, this.player1, this.player2);
        res.matchesAlive = this.matchesAlive;
        res.currentPlayer = super.currentPlayer;
        return res;
    }

    /**
     * renvoie une liste de tous les coups possibles
     */
    @Override
    public ArrayList<Integer> validMoves() {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int maxMove = (getCurrentNbMatches() > this.maxMatchesPerLap) ? this.maxMatchesPerLap : getCurrentNbMatches();
        for (int i=1; i<=maxMove; i++) {
            res.add(i);
        }
        return res;
    }

    /**
     * Affiche le nombre d'allumettes restantes (purement esthetique)
     */
    @Override
    public void viewInformations() {
        String chaine = "";
        for (int i=0; i<this.matchesAlive; i++) {
            chaine += "|";
        }
        System.out.println(chaine);
    }

}