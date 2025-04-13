package games.players;

import java.util.Random;
import games.genericgames.Game;

/*
 * Classe représentant un joueur qui effectue des coups aléatoires
 */
public class RandomPlayer implements Player{
    
    private Random random;

    public RandomPlayer(Random random) {
        this.random = random;
    }

    /*
     * Méthode permettant au joueur de choisir un coup
     */
    @Override
    public int chooseMove(Game game) {
        int choix = 0;
        for (int i: game.validMoves()) {
            choix = this.random.nextInt(game.validMoves().size());
        }
        return game.validMoves().get(choix);
    }

    /*
     * Permet d'afficher le n° du joueur
     */
    @Override
    public String toString() {
        return "Joueur aléatoire n°" + this.hashCode();
    }
}
