package s14.dao;

public class Region {
	private int regionID;
	private String regionName;
	

	public Region(int region_id, String region_name) {
		this.regionID = region_id;
		this.regionName = region_name;
	}
	
	public int getRegionID() {
		return regionID;
	}
	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	@Override
	public String toString() {
		return "Region [regionID=" + regionID + ", regionName=" + regionName + "]";
	}
	
	
}
