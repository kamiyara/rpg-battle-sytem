import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameField {
	//MakeScanner
	static Scanner scanner = new Scanner(System.in);
	//MakeCommonHashMap
	static Map<String, Character> charaList = new HashMap<>();
	//MakeTeamList
	static List<String> team1List = new ArrayList<>();
	static List<String> team2List = new ArrayList<>();
	//Make OtherClass instance
	static BattleManager battleManager = new BattleManager(charaList, team1List, team2List);
	static CharacterFactory characterFactory = new CharacterFactory(charaList, team1List, team2List);
	static GameUtils gameUtils = new GameUtils(charaList);

	public static void main(String[] args) {

		//MakeCharacter
		characterFactory.createCharacter("Warrior", "tanaka", 300, 100, 100, 1);
		characterFactory.createCharacter("Wizard", "satou", 260, 80, 120, 1);
		characterFactory.createCharacter("Warrior", "suzuki", 400, 100, 80, 2);
		characterFactory.createCharacter("Wizard", "yamada", 230, 70, 200, 2);

		//MakeCounter
		int allTurnCount;
		int battleCount;

		//MakeInitializationOfCounter
		int initialTurnCount = 1;
		int initialBattleCount = 1;

		//MakeUpperLimit
		int maxTurn = 20;
		int maxBattleTurn = 2;

		//SetAllTurnCount
		allTurnCount = initialTurnCount;

		//Turn Progression
		while (allTurnCount <= maxTurn) {
			System.out.println("Turn:" + allTurnCount);

			//BattleCount is initialized with each turn update
			battleCount = initialBattleCount;

			//The team corresponding to the battle count takes turn
			while (battleCount <= maxBattleTurn) {
				if (battleCount == 1) {
					System.out.println("Team1 Turn\n");
					//All character of the team move 
					for (int i = 0; i < team1List.size(); i++) {
						battleManager.processCharacterTurn(battleCount, team1List.get(i));
						System.out.println();
						
						//CheakWinner
						if (gameUtils.cheakGameFinish()) {
							System.out.println("Game End");
							return;
						}
					}
					battleCount++;

				} else if (battleCount == 2) {
					System.out.println("Team2 Turn\n");
					for (int i = 0; i < team2List.size(); i++) {
						battleManager.processCharacterTurn(battleCount, team2List.get(i));
						System.out.println();
						
						//CheakWinner
						if (gameUtils.cheakGameFinish()) {
							System.out.println("Game End");
							return;
						}
					}
					battleCount++;

				} else {
					System.out.println("Unknown turn");
					battleCount = 1;
					return;
				}
			}
			//最終ターン終了時、引き分け
			if (allTurnCount == maxTurn) {
				System.out.println("Drow");
				System.out.println("Game End");
			}
			allTurnCount++;
		}

	}

}
