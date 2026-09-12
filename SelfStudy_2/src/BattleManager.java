import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BattleManager {

	///MakeCharaList
	Map<String, Character> charaList = new HashMap<>();
	//MakeTeamList
	List<String> team1List = new ArrayList<>();
	List<String> team2List = new ArrayList<>();
	//MakeScanner
	Scanner scanner = new Scanner(System.in);
	int selectedNum;
	//MakeGameUtilsInstance
	GameUtils gameUtils;
	//MakeLoopEnd
	static boolean roopEnd1 = false;
	static boolean roopEnd2 = false;

	BattleManager(Map<String, Character> charaList, List<String> team1List, List<String> team2List) {
		this.charaList = charaList;
		this.team1List = team1List;
		this.team2List = team2List;
		gameUtils = new GameUtils(charaList);
	}

	//processCharacterTurn
	public void processCharacterTurn(int whichTeam, String attacker) {

		//Cheak attacker is attackable
		if (charaList.get(attacker) instanceof Warrior) {
			if (!((Warrior) (charaList.get(attacker))).attackable()) {
				System.out.println("Warrior " + attacker + " has alredy been dead.");
				return;
			}
		}
		if (charaList.get(attacker) instanceof Wizard) {
			if (!((Wizard) (charaList.get(attacker))).attackable()) {
				System.out.println("Wizard " + attacker + " has alredy been dead.");
				return;
			}
		}

		System.out.println("Action Turn Of " + charaList.get(attacker).getJob() + " " + attacker);
		try {
			//Reset roopEnd
			roopEnd1 = false;
			while (!roopEnd1) {
				System.out.println("Action List\n1,attack\t2,other");
				selectedNum = Integer.parseInt(scanner.nextLine());
				switch (selectedNum) {
				case 1://AttackAction
					executeAction(attacker, whichTeam);
					break;
				case 2://OtherAction
					otherAction();
					break;
				}
			}
			//後でしっかり書く
		} catch (Exception e) {
			System.out.println("Error in Battle Turn Progression\n" + e.getMessage());
		}
	}

	//Battle Program 1,NormalSkill 2,UniqueSkill 3,UniqueAction 4,SpecialSkill
	public void executeAction(String attacker, int whichTeam) {
		double dmg = 0;
		String target;
		roopEnd2 = false;
		while (!roopEnd2) {
			try {
				System.out
						.println(charaList.get(attacker).getJob() + " " + attacker
								+ "'s action select\n1,NormalSkill\t2,UniqueSkill\n3,UniqueAction\t4,SpecialSkill");
				selectedNum = Integer.parseInt(scanner.nextLine());

				if (selectedNum == 1 || selectedNum == 2 || selectedNum == 4) {
					System.out.println("What Character is target？");
					if (whichTeam == 1) {
						DisplayManager.displayTeamMember(team2List);
						target = team2List.get(Integer.parseInt(scanner.nextLine()) - 1);
					} else if (whichTeam == 2) {
						DisplayManager.displayTeamMember(team1List);
						target = team1List.get(Integer.parseInt(scanner.nextLine()) - 1);
					} else {
						System.out.println("Unknown team");
						target = null;
					}
					switch (selectedNum) {
					case 1:
						dmg = charaList.get(attacker).normalSkill();
						break;
					case 2:
						dmg = charaList.get(attacker).uniqueSkill();
						break;
					case 4:
						dmg = charaList.get(attacker).specialSkill();
					}
					charaList.get(target).beAttacked(dmg);
					roopEnd1 = true;
					roopEnd2 = true;
				} else if (selectedNum == 3) {
					charaList.get(attacker).uniqueAction();
					roopEnd1 = true;
					roopEnd2 = true;
				} else {
					System.out.println("BattleProgramError");
				}
			} catch (Exception e) {
				System.out.println("BattleProgramError");
			}
		}
	}

	//Battle OtherAction
	public void otherAction() {
		System.out.println(
				"1,Display all character data\n2,Display higher Hp Character\n3,Display sorted Atk descending order List");
		selectedNum = Integer.parseInt(scanner.nextLine());
		switch (selectedNum) {
		case 1:
			gameUtils.displayAllCharacterinfo();
			return;
		case 2:
			System.out.println("What percentage？");
			selectedNum = Integer.parseInt(scanner.nextLine());
			gameUtils.displayXPercentHpOrHigherCharacter(selectedNum);
			return;

		case 3:
			gameUtils.sortAtkDescendingOrder();
			return;
		}

	}
}
