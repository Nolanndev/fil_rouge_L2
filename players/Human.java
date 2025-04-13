package games.players;

import java.util.Scanner;
import games.genericgames.Game;

/*
 * Classe représentant un joueur humain
 */
public class Human implements Player{

    protected String name;
    private Scanner scanner;
    
    public Human(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    /*
     * 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Méthode permettant à un joueur de choisir un coup
     * @return retourne un entier qui représente le coup du joueur
     */
    @Override
    public int chooseMove(Game game) {
        System.out.println(System.lineSeparator() + "C'est votre tour : " + this.name);
        String chaine = "";
        System.out.print("Coups possibles : ");
        for (int i: game.validMoves()) {
            chaine += Integer.toString(i) + " ";
        }
        System.out.println(chaine);

        int userInput = 0;
        while (true) {
            System.out.print("Votre coup : ");
            String input = scanner.next();
            try {
                userInput = Integer.parseInt(input);
                if (game.isValid(userInput)) {
                    return userInput;
                } else {
                    System.out.println("Entrée invalide, vous devez saisir l'un des coups possibles !!");
                }
            } catch (Exception e) {
                System.out.println("Entrée invalide, vous devez saisir l'un des coups possibles !!");
            }
        }
    }

    /**
     * @return le nom du joueur
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Permet de changer le hashcode de la classe par celui du nom
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Permet pour les vérifications d'égalité de voir si deux joueurs sont égaux
     * @return vrai si l'objet comparé est le même que l'instance qui appelle la fonction, et faux sinon
     */
    @Override
    public boolean equals(Object other) {
        if (Human.class.isInstance(other)) {
            if (this.name.equals(((Human) other).getName())) {
                return true;
            }
        }
        return false;
    }
}
