package games.plays;

import games.genericgames.Game;
import games.players.Player;
import games.players.Human;
import games.players.NegamaxPlayer;
import games.players.RandomPlayer;
import java.lang.Thread;

/*
 * Classe permettant d'orchestrer une partie d'un jeu de type Game
 */
public class Orchestrator {
    
    private Game game;
    private int lastMove;

    public Orchestrator(Game game) {
        this.game = game;
        this.lastMove = -1;
    }

    /*
     * Fonction qui fait office de boucle de jeu pour les jeux de type Game
     * Cette fonction passe les tests mais n'est pas utilisée dans le jeu final
     */
    public void play() {
        int playerMove;
        while(!this.game.isOver()) {
            playerMove = game.getCurrentPlayer().chooseMove(this.game);
            this.game.execute(playerMove);
        }
        if (game.isOver()) {
            System.out.println("Félicitation " + this.game.getWinner() + " vous avez gagné");
        } else {
            System.out.println("La partie est finie, il n'y a pas de gagnant");
        }
    }

    /*
     * Fonction qui fait office de boucle de jeu pour les jeux de type Game
     * Cette fonction ne passe pas les tests, car elle comprend des affichages et un appel au constructeur de jeu avec un paramètre en plus (le scanner)
     */
    public void playAffichage() {
        // this.game.startGame();
        int playerMove;
        while(!this.game.isOver()) {
            if (this.lastMove != -1) {
                System.out.println("Dernier coup joué : " + this.lastMove + System.lineSeparator());
            }
            System.out.println(this.game.situationToString());
            this.game.viewInformations();
            playerMove = game.getCurrentPlayer().chooseMove(this.game);
            this.game.execute(playerMove);
            // si les deux joueurs sont des joueurs aléatoires, on attends 1 seconde pour voir le déroulé de la partie
            // if (RandomPlayer.class.isInstance(game.getPlayer1()) || RandomPlayer.class.isInstance(game.getPlayer2()) || 
            // NegamaxPlayer.class.isInstance(game.getPlayer1()) || NegamaxPlayer.class.isInstance(game.getPlayer2())) {
            if (!Human.class.isInstance(game.getPlayer1()) && !Human.class.isInstance(game.getPlayer2())) {
                System.out.println("");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                this.game.clearConsole();
            }
            this.lastMove = playerMove;
        }
        if (game.isOver()) {
            if (this.game.getWinner() == null) {
                System.out.println("La partie est finie, il n'y a pas de gagnant");
            } else {
                System.out.println("Félicitation " + this.game.getWinner() + " vous avez gagné");
            }
        }
    }
}
