package games.players;

/*
 * Interface pour les différents types de joueur
 */

import games.genericgames.Game;

public interface Player {    
    public int chooseMove(Game game);
}
