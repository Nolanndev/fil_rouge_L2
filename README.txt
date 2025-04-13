Projet _fil rouge_ réalisé dans le cadre de l'UE _Programmation Orientée Objet_
Projet réalisé en binôme avec LE BRIS Ilan

Structure des packages:
	games
	 | genericgames
	 |  | AbstractGame.java : classe abstraite pour représenter les jeux de nim et du morption (et d'autre si besoin)
	 |  | Game.java : interface qui permet d'implémenter des jeux, et donc de factoriser le code
	 |  | Nim.java : classe qui instancie les méhodes de nim
	 |  | TicTacToe.java : classe qui instancie les méthodes de tictactoe
	 | players
	 |	| Human.java : joueur qui doit rentrer manuellement ses choix de jeux
	 |	| NegamaxPlayer.java : joueur qui choisit un coup de manière optimisée pour gagner
	 |	| Player.java : interface pour représenter les joueurs
	 |	| RandomPlayer.java : joueur qui choisit un coup aléatoire
	 | plays
	 |	| Main.java : classe exécutable pour jouer aux jeux de nim et du morpion
	 |	| Orchestrator.java : classe permettant d'instancier un orchestrateur qui s'occupe des boucles de jeux de type 'Game'

Pour les classes Orchestrator, nous avons utilisé la surcharge de classe, ayant rencontré des problèmes avec l'utilisation de java.utils.Scanner, nous avons créer un constructeur pour Nim et TicTacToe qui passent les tests, et un autre qui prend un scanner en paramètre afin de pouvoir l'utiliser sans problème.

Utiliser les classes exécutables:
	Pour jouer au jeu, il suffit de compiler le package 'game' et de lancer la classe exécutable game.plays.Main
	Outre dans le menu de sélection, toutes les erreurs que l'utilisateur peut faire lors de la saisie d'informations sont gérées

Parties non traitées:
	- parties optionnelles
