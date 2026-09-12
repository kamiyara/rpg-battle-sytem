import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameUtils {
	
	//Make HashMap
	Map<String, Character> characterList = new HashMap<>();
	
	//Input CharaList
	GameUtils(Map<String, Character> characterList) {
		this.characterList = characterList;
	}

	//Display All Character (Functional Requirement2)
	public void displayAllCharacterinfo() {
		characterList.values().stream().forEach(Character::printAllData);
	}

	//Display chracters at x％Hp or higher (/Functional requirement3)
	public void displayXPercentHpOrHigherCharacter(int leastPercent) {
		
		//Cheak Apporopriate Percent And Display Message
		if (leastPercent < 0 || leastPercent > 100) {
			System.out.println("This percent is not appropriate");
			return;
		}
		
		//Display Apply Character List
		System.out.println("Character at " + leastPercent + "％ Hp or higher List");
		characterList.values().stream()
				.filter(c -> c.getHp() >= (c.getMaxHp() * leastPercent / 100.0))
				.forEach(c -> System.out.println("・" + c.getName() + " Hp:" + c.getHp() + "/" + c.getMaxHp()));
	}

	//Sorted Character By Atk (Functional requirement4)
	public void sortAtkDescendingOrder() {
		
		//Make Sorted List By Stream
		List<Character> sorted = characterList.values().stream()
				.sorted(Comparator.comparingInt(Character::getAtk).reversed())
				.collect(Collectors.toList());
		
		//Display Sorted List
		System.out.println("Sorted Atk");
		sorted.forEach(c -> System.out.println("・" + c.getName() + " Atk:" + c.getAtk() + " Team" + c.getTeamNum()));
	}

	//Cheak Game Finish
	public boolean cheakGameFinish() {
		
		//Determining GameFinish
		if (characterList.values().stream()
				.filter(c -> c.getTeamNum() == 1)
				.allMatch(c -> c.cheakDead())) {
			System.out.println("Team2 Victory");
			return true;
		} else if (characterList.values().stream()
				.filter(c -> c.getTeamNum() == 2)
				.allMatch(c -> c.cheakDead())) {
			System.out.println("Team1 Victory");
			return true;
		
		} else {
			return false;
		}
	}
}
