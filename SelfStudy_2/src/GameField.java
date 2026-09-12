import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameField {
	//Make Scanner
	static Scanner scanner = new Scanner(System.in);
	//Make Common HashMap
	static Map<String, Character> charaList = new HashMap<>();
	//Make Team List
	static List<String> team1List = new ArrayList<>();
	static List<String> team2List = new ArrayList<>();
	//Make Other Class instance
	static BattleManager battleManager = new BattleManager(charaList, team1List, team2List);
	static CharacterFactory characterFactory = new CharacterFactory(charaList, team1List, team2List);
	static GameUtils gameUtils = new GameUtils(charaList);

	public static void main(String[] args) {

		//Make Character
		characterFactory.createCharacter("Warrior", "tanaka", 300, 100, 100, 1);
		characterFactory.createCharacter("Wizard", "satou", 260, 80, 120, 1);
		characterFactory.createCharacter("Warrior", "suzuki", 400, 100, 80, 2);
		characterFactory.createCharacter("Wizard", "yamada", 230, 70, 200, 2);

		//Make Counter
		int allTurnCount;
		int battleCount;

		//Make Initialization Of Counter
		int initialTurnCount = 1;
		int initialBattleCount = 1;

		//Make Upper Limit
		int maxTurn = 20;
		int maxBattleTurn = 2;

		//Set All Turn Count
		allTurnCount = initialTurnCount;

		//Turn Progression
		while (allTurnCount <= maxTurn) {
			System.out.println("Turn:" + allTurnCount);

			//BattleCount Is Initialized With Each Turn Update
			battleCount = initialBattleCount;

			//The Team Corresponding To The Battle Count Takes Turn
			while (battleCount <= maxBattleTurn) {
				if (battleCount == 1) {
					System.out.println("Team1 Turn\n");
					
					//All Character Of The Team1 Move 
					for (int i = 0; i < team1List.size(); i++) {
						battleManager.processCharacterTurn(battleCount, team1List.get(i));
						System.out.println();

						//Cheak Winner
						if (gameUtils.cheakGameFinish()) {
							System.out.println("Game End");
							return;
						}
					}
					battleCount++;

					//All Character Of The Team2 Move 
				} else if (battleCount == 2) {
					System.out.println("Team2 Turn\n");
					for (int i = 0; i < team2List.size(); i++) {
						battleManager.processCharacterTurn(battleCount, team2List.get(i));
						System.out.println();

						//Cheak Winner
						if (gameUtils.cheakGameFinish()) {
							System.out.println("Game End");
							return;
						}
					}
					battleCount++;

					//Error Message
				} else {
					System.out.println("Unknown turn");
					battleCount = 1;
					return;
				}
			}
			//Game End When TurnCount Over MaxTurn
			if (allTurnCount == maxTurn) {
				System.out.println("Drow");
				System.out.println("Game End");
			}
			allTurnCount++;
		}

	}

}
