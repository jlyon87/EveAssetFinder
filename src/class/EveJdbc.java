import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class EveJdbc {
	public static List<EveAssetHelper.SolarSystem> querySystems() {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		List<EveAssetHelper.SolarSystem> solarSystems = new ArrayList<EveAssetHelper.SolarSystem>();

		try {
//			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\Phoebe\\universeDataDx.db");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
				"SELECT solarSystemName, solarSystemID, regionID " +
				"FROM mapSolarSystems " +
				"WHERE regionID = \"10000043\" OR " +
				"regionID = \"10000002\" OR " +
				"regionID = \"10000032\" OR " +
				"regionID = \"10000042\"" +
				"ORDER BY regionID, solarSystemName"); // Instead of ORDER BY, try sorting with Comparable and Comparator?

			while(resultSet.next()) {
				solarSystems.add(
					new EveAssetHelper.SolarSystem(
						resultSet.getString("solarSystemName"),
						resultSet.getString("solarSystemID"),
						Integer.parseInt(resultSet.getString("regionID"))
					)
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
				resultSet.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(!solarSystems.isEmpty()) {
			return solarSystems;
		}
		else {
			return new ArrayList<EveAssetHelper.SolarSystem>();
		}
	}
}