package model;


/**
 * A road holds cars.
 */
public class SinkRoad extends Road{
	
	private String roadType = "Sink";
	
	SinkRoad() { } // Created only by this package

	@Override
	public String getRoadType()
	{
		return roadType;
	}
}
