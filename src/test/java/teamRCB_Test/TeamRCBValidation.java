package teamRCB_Test;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TeamRCBValidation {

	@BeforeClass
	public JSONArray teamRCB() throws IOException, ParseException {

		JSONParser jsonparser = new JSONParser();
		FileReader reader = new FileReader(".\\JsonFiles\\TeamRCB.json");

		Object team = jsonparser.parse(reader);
		JSONObject teamRCB = (JSONObject) team;

		JSONArray array = (JSONArray) teamRCB.get("player");
		return array;
	}

	@Test
	public void validateForeignPlayers() throws IOException, ParseException {

		TeamRCBValidation obj = new TeamRCBValidation();
		JSONArray playersArray = obj.teamRCB();
		int totalPlayers = playersArray.size();

		int count = 0;

		for (int i = 0; i < totalPlayers; i++) {

			JSONObject player = (JSONObject) playersArray.get(i);

			String country = (String) player.get("country");

			if (country.equals("India")) {
				count = count + 1;
			}
		}

		int totalForeignPlayers = totalPlayers - count;

		if (totalForeignPlayers != 4) {
			System.out.println("Test Failed");
			System.out.println("Number of Foreign Players are: " + totalForeignPlayers);
		} else {
			System.out.println("Test Passed");
			System.out.println("Number of Foreign Players are: " + totalForeignPlayers);
		}
	}

	@Test
	public void validateWicketKeeper() throws IOException, ParseException {
		TeamRCBValidation obj = new TeamRCBValidation();
		JSONArray playersArray = obj.teamRCB();
		int totalPlayers = playersArray.size();

		int count = 0;

		for (int i = 0; i < totalPlayers; i++) {
			JSONObject player = (JSONObject) playersArray.get(i);

			String role = (String) player.get("role");

			if (role.equals("Wicket-keeper")) {
				count = count + 1;

			}
		}
		if (count < 1) {
			System.out.println("Test Failed");
		} else {
			System.out.println("Test Passed");
			System.out.println("Number of Wicket-Keepers are : " + count);

		}

	}
}
