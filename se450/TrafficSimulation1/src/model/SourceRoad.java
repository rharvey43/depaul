package model;


/**
 * A road holds cars.
 */
public class SourceRoad extends Road{
	
	private String roadType = "Source";
	SourceRoad() { } // Created only by this package

	@Override
	public String getRoadType()
	{
		return roadType;
	}
	
	
}
