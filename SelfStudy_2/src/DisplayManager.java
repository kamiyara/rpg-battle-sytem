import java.util.List;

public class DisplayManager {

	public static void displayTeamMember(List<String> teamList) {
		if (teamList == null) {
			System.out.println("This team is unknown");
		} else {
			int displayNumber;
			for (int i = 0; i < teamList.size(); i++) {
				displayNumber = i + 1;
				System.out.print(displayNumber + "," + teamList.get(i) + "\t");
			}
			System.out.println("");
		}
	}
}
