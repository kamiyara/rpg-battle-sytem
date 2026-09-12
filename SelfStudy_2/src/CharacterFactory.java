import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterFactory {
	///Make CharaList
	Map<String, Character> characterList = new HashMap<>();

	//Make TeamList
	List<String> team1List = new ArrayList<>();
	List<String> team2List = new ArrayList<>();
	
	//Input All List
	CharacterFactory(Map<String, Character> charaList, List<String> team1List, List<String> team2List) {
		this.characterList = charaList;
		this.team1List = team1List;
		this.team2List = team2List;
	}

	//Create Character
	public void createCharacter(String job, String name, int hp, int atk,
			int originalStatus, int teamNum) {
		try {
			
			//Separate By Job
			if (job.equals("Warrior")) {
				Warrior c = new Warrior(name, hp, atk, originalStatus, teamNum);
				characterList.put(c.getName(), c);
			} else if (job.equals("Wizard")) {
				Wizard c = new Wizard(name, hp, atk, originalStatus, teamNum);
				characterList.put(c.getName(), c);
			} else {
				System.out.println("Error");
				return;
			}
			
			//Separate By TeamNum
			if (teamNum == 1) {
				team1List.add(name);
			} else if (teamNum == 2) {
				team2List.add(name);
			} else {
				System.out.println("teamNumError");
			}
			
			//Error Message
		} catch (Exception e) {
			System.out.println("Error\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
