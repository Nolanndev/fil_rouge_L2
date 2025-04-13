package games.plays;
/*
 * Classe exécutable permettant de jouer aux jeux de nim et au morpion
 * Les saisies utilisateur lors de l'initialisation ne sont pas protégées
 */

import games.genericgames.*;
import games.players.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    /**
     * Permet de nettoyer le terminal
     */
    static void clearConsole() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    /**
     * demande à l'utilisateur de rentrer une valeur
     * @param scanner on passe le scanner en paramètre pour plus de facilité
     * @return un entier positif
     */
    static int getPositiveInteger(Scanner scanner) {
        boolean error = true;
        String str;
        int intInput = 1;
        while (error) {
            str = scanner.next();
            try {
                intInput = Integer.parseInt(str);
                if (intInput <= 0) {
                    System.out.print("Vous devez entrer un nombre positif, réessayez : ");
                } else {
                    error = false;
                }
            } catch (Exception e) {
                System.out.print("Vous devez entrer un nombre positif, réessayez : ");
            }
        }
        return intInput;
    }

    /**
     * fonction qui est le jeu, tout simplement
     * @param args non utilisé
     */
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Player player1, player2;

        clearConsole();
        System.out.println("Bienvenue sur le fil rouge" + System.lineSeparator());
        
        //choix du joueur
        int playerChoice;
        String name;
        Random rand = new Random(123);
        System.out.println("Types de joueurs disponibles : \n\t1 - Human - c'est à vous de jouer \n\t2 - RandomPlayer - laissez l'aléatoire jouer à votre place \n\t3 - NegamaxPlayer - frottez vous à l'aleatoire 2.0" + System.lineSeparator());
        System.out.print("Choisissez le type du premier joueur : ");
        playerChoice = scanner.nextInt();
        if (playerChoice == 1) {
            System.out.print("Nom du joueur 1 : ");
            name = scanner.next();
            player1 = new Human(name, scanner);
        } else if (playerChoice == 2) {
            player1 = new RandomPlayer(rand);
        } else {
            player1 = new NegamaxPlayer();
        }
        System.out.print("Choisissez le type du deuxieme joueur : ");
        playerChoice = scanner.nextInt();
        if (playerChoice == 1) {
            System.out.print("Nom du joueur 2 : ");
            name = scanner.next();
            player2 = new Human(name, scanner);
        } else if (playerChoice == 2) {
            player2 = new RandomPlayer(rand);
        } else {
            player2 = new NegamaxPlayer();
        }
        
        
        clearConsole();
        //choix du jeu
        Game game;
        System.out.println(System.lineSeparator() + "Jeux disponibles : \n\t1 - Nim \n\t2 - Morpion" + System.lineSeparator());
        System.out.print("Choisissez votre jeu en tapant son numéro pour y jouer : ");
        
        int gameChoice = scanner.nextInt();
        if (gameChoice == 1) {
            int initialNbMatches = 16, maxMatchesPerLap = 4;
            boolean error;
            clearConsole();
            System.out.println("JEU DE NIM");
            System.out.print("Combien voulez-vous d'allumettes dans la partie: ");
            initialNbMatches = getPositiveInteger(scanner);
            System.out.print("Nombre maximum d'allumettes retirées en une fois: ");
            error = true;
            while (error) {
                maxMatchesPerLap = getPositiveInteger(scanner);
                if (maxMatchesPerLap <= initialNbMatches) {
                    error = false;
                } else {
                    System.out.print("Erreur, vous ne pouvez pas retirer plus d'allumette qu'il n'y en a sur le plateau");    
                }
        }
        System.out.println("Bien, la partie va commencer" + System.lineSeparator());
            game = new Nim(initialNbMatches,maxMatchesPerLap,player1,player2,scanner);
        } else if (gameChoice == 2){
            clearConsole();
            System.out.println("JEU DU MORPION");
            System.out.println("La partie va commencer");
            game = new TicTacToe(player1, player2);
        } else {
            game = null;
        }
        
        Orchestrator orchestrator = new Orchestrator(game);
        orchestrator.playAffichage();
        
        scanner.close();
    }
}
