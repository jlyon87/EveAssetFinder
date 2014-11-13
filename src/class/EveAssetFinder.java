import java.util.List;
import java.util.ArrayList;

public class EveAssetFinder {
	public static void main(String[] args) {
		List<EveAssetHelper.SolarSystem> solarSystems = EveJdbc.querySystems();

		System.out.printf("%-20s %-20s %-10s %n","System Name:","System ID:", "Region ID:");
		for(EveAssetHelper.SolarSystem ss : solarSystems) {
			System.out.printf("%-20s %-20s %-8d %n", ss.Name, ss.Id, ss.RegionID);
		}
	}
}