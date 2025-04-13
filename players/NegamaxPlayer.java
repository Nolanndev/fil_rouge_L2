package games.players;

import games.genericgames.Game;

public class NegamaxPlayer implements Player{
    public NegamaxPlayer() {}
    
    /**
     * @param game etat d'un jeu
     * @return un entier qui sera le coup joué par l'algorithme
     */
    public int chooseMove(Game game) {
        Integer meilleurValeur = null;
        Integer meilleurCoup = null;
        for (int c: game.validMoves()) {
            Game sp = game.copy();
            sp.execute(c);
            int v = -(this.evaluate(sp));
            if (meilleurValeur == null || v > meilleurValeur) {
                meilleurValeur = v;
                meilleurCoup = c;
            }
        }
        return meilleurCoup;
    }

    /**
     * @param situation etat de jeu sur lequel on va exécuter l'algorithme
     * @return un entier qui donne l'état pensé par l'algorithme
     */
    public int evaluate(Game situation) {
        Player player = situation.getCurrentPlayer();
        if (situation.isOver()) {
            if (situation.getWinner() == null) {
                return 0;
            } else if (player.equals(situation.getWinner())) {
                return 1;
            } else {
                return -1;
            }
        } else {
            Integer res = null;
            for (int move: situation.validMoves()) {
                Game sp = situation.copy();
                sp.execute(move);
                int v = -(evaluate(sp));
                if (res == null || v > res) {
                    res = v;
                }
            }
            return res;
        }
    }

}
