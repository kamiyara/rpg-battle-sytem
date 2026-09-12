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
	DisplayManager displayManager;
	//MakeLoopEnd
	static boolean roopEnd1 = false;
	static boolean roopEnd2 = false;

	BattleManager(Map<String, Character> charaList, List<String> team1List, List<String> team2List) {
		this.charaList = charaList;
		this.team1List = team1List;
		this.team2List = team2List;
		gameUtils = new GameUtils(charaList);
		displayManager = new DisplayManager(charaList);
	}

	//processCharacterTurn 
	public void processCharacterTurn(int whichTeam, String attacker) {

		//Cheak attacker is attackable ☆UpdateCode
		Character chara = charaList.get(attacker);
		if (chara instanceof Attackable) {
			if (!(((Attackable) chara).attackable())) {
				System.out.println(attacker + " has alredy been dead.");
				return;
			}
		}

		//Display ActionTurn Message
		System.out.println("Action Turn Of " + chara.getJob() + " " + attacker);

		//Reset roopEnd And RoopStart
		roopEnd1 = false;
		while (!roopEnd1) {
			try {

				//Display ActionList And Scan The Select
				System.out.println("Action List\n1,attack\t2,other");
				selectedNum = Integer.parseInt(scanner.nextLine());

				//Execute Selected Action
				switch (selectedNum) {
				case 1:
					executeAction(attacker, whichTeam);
					break;
				case 2:
					otherAction();
					break;
				}

				//Error Message
			} catch (Exception e) {
				System.out.println("Error in Battle Turn Progression\n" + e.getMessage());
			}
		}
	}

	//Battle Program 1,NormalSkill 2,UniqueSkill 3,UniqueAction 4,SpecialSkill
	public void executeAction(String attacker, int whichTeam) {
		Character chara = charaList.get(attacker);
		double dmg = 0;
		String target;
		//Reset roopEnd2 And Roop Start
		roopEnd2 = false;
		while (!roopEnd2) {
			try {
				//Display Skill Select Message
				System.out
						.println(chara.getJob() + " " + attacker
								+ "'s action select\n1,NormalSkill\t2,UniqueSkill\n3,UniqueAction\t4,SpecialSkill");
				selectedNum = Integer.parseInt(scanner.nextLine());

				//Execute Skill Choose Target
				if (selectedNum == 1 || selectedNum == 2 || selectedNum == 4) {
					System.out.println("What Character is target？");
					if (whichTeam == 1) {
						displayManager.displayTeamMember(team2List);
						target = team2List.get(Integer.parseInt(scanner.nextLine()) - 1);
					} else if (whichTeam == 2) {
						displayManager.displayTeamMember(team1List);
						target = team1List.get(Integer.parseInt(scanner.nextLine()) - 1);
					} else {
						System.out.println("Unknown team");
						target = null;
					}

					//Caluculate Skill Damage
					switch (selectedNum) {
					case 1:
						dmg = chara.normalSkill();
						break;
					case 2:
						dmg = chara.uniqueSkill();
						break;
					case 4:
						dmg = chara.specialSkill();
					}

					//Caluculate Take Damage
					charaList.get(target).beAttacked(dmg);
					roopEnd1 = true;
					roopEnd2 = true;

					//Execute No Damage Action
				} else if (selectedNum == 3) {
					chara.uniqueAction();
					roopEnd1 = true;
					roopEnd2 = true;

					//Display Error Message
				} else {
					System.out.println("BattleProgramError");
				}

				//Display Error Message
			} catch (Exception e) {
				System.out.println("BattleProgramError");
			}
		}
	}

	//Battle OtherAction
	public void otherAction() {

		//Select What Other Action To Do
		System.out.println(
				"1,Display all character data\n2,Display higher Hp Character\n3,Display sorted Atk descending order List");
		selectedNum = Integer.parseInt(scanner.nextLine());

		//Execute Selected Action
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
