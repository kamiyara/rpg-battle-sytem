import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestField {

	static Map<String, Character> charaList = new HashMap<>();
	static List<String> team1List = new ArrayList<>();
	static List<String> team2List = new ArrayList<>();

	static CharacterFactory characterFactory = new CharacterFactory(charaList, team1List, team2List);

	static GameUtils gameUtils = new GameUtils(charaList);

	public static void main(String[] args) {

		characterFactory.createCharacter("Warrior", "tanaka", 300, 100, 100, 1);
		characterFactory.createCharacter("Wizard", "satou", 260, 80, 120, 1);
		characterFactory.createCharacter("Warrior", "suzuki", 400, 100, 80, 2);
		characterFactory.createCharacter("Wizard", "yamada", 230, 70, 200, 2);

		gameUtils.displayAllCharacterinfo();
		gameUtils.displayXPercentHpOrHigherCharacter(50);

	}

}
