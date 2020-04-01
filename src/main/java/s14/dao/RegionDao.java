package s14.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegionDao implements Dao<Region> {
	static final String GET_BY_PK = "SELECT region_id, region_name FROM regions WHERE region_id = ?";
	private static final String GET_ALL = "SELECT region_id, region_name FROM regions";
	private static final String INSERT = "INSERT INTO regions(region_id, region_name) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE regions SET region_name = ? " + "WHERE region_id = ?";;
	private static final String DELETE = "DELETE FROM regions WHERE region_id = ?";

	public List<Region> getAll() {
		List<Region> results = new ArrayList<>();

		try (Connection conn = Connector.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(GET_ALL)) {
			while (rs.next()) {
				Region region = new Region(rs.getInt(1), rs.getString(2));
				results.add(region);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return results;
	}

	@Override
	public Optional<Region> get(long id) {
		try (Connection conn = Connector.getConnection(); //
				PreparedStatement ps = conn.prepareStatement(GET_BY_PK)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Region my = new Region(rs.getInt(1), rs.getString(2));
					return Optional.of(my);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return Optional.empty();
	}

	  @Override
	    public void save(Region region) {
	        try (Connection conn = Connector.getConnection(); //
	                PreparedStatement ps = conn.prepareStatement(INSERT)) {
	            ps.setDouble(1, region.getRegionID());
	            ps.setString(2, region.getRegionName());
	            ps.executeUpdate();
	            
	        } catch (SQLException se) {
	            se.printStackTrace();
	        }
	    }

	  @Override
	    public void update(Region region) {
	        try (Connection conn = Connector.getConnection(); //
	                PreparedStatement ps = conn.prepareStatement(UPDATE)) {
	            ps.setInt(2, region.getRegionID());
	            ps.setString(1, region.getRegionName());
	            int count = ps.executeUpdate();
	            if (count != 1) {
	                System.out.println("Warning! Updated " + count + " lines for " + region);
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	        }
	    }

	  @Override
	    public void delete(long id) {
	        try (Connection conn = Connector.getConnection(); //
	                PreparedStatement ps = conn.prepareStatement(DELETE)) {
	            ps.setLong(1, id);
	            int count = ps.executeUpdate();
	            if (count != 1) {
	                System.out.println("Warning! Deleted " + count + " lines for " + id);
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	        }
	    }

}
