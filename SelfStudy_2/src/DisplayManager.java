import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayManager {
	///Make CharaList
	Map<String, Character> characterList = new HashMap<>();

	//Input CharaList
	DisplayManager(Map<String, Character> charaList) {
		this.characterList = charaList;
	}

	//Execute Display TeamMember
	public void displayTeamMember(List<String> teamList) {

		//Confirming Team Exist 
		if (teamList == null) {
			System.out.println("This team is unknown");
		} else {

			//Make DisplayNumber And TargetChara
			int displayNumber = 1;

			//Displaying All Attackable TeamMember
			for (int i = 0; i < teamList.size(); i++) {

				//Make TargetChara
				Character targetChara = characterList.get(teamList.get(i));

				//Check TargetChara Alive
				if (targetChara instanceof Attackable attackableChara && attackableChara.attackable()) {
					System.out.print(displayNumber + "," + teamList.get(i) + "\t");
					displayNumber += 1;
				}

			}
			System.out.println("");
		}
	}
}
