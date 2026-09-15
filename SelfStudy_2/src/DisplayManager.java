import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayManager {
	///Make CharaList
	Map<String, Character> characterList = new HashMap<>();
	//Make GameUtils Instance
	GameUtils gameUtils;

	//Input CharaList
	DisplayManager(Map<String, Character> charaList) {
		this.characterList = charaList;
		gameUtils = new GameUtils(charaList);
	}

	//Execute Display TeamMember
	public void displayTeamMember(int teamNum) {
		//Make DisplayNumber
		int displayNumber;
		//Make Alive TeamMember List
		List<Character> aliveTeamMember = gameUtils.getAliveTeamMemberList(teamNum);
		//Loop AliveTeamMember Size
		for (int i = 0; i < aliveTeamMember.size(); i++) {
			//Display AliveTeamMember
			displayNumber = i + 1;
			System.out.print(displayNumber + "," + aliveTeamMember.get(i).getName() + "\t");
		}
		System.out.println("");
	}
}
