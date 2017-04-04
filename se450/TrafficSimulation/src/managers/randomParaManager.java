package managers;


public final class randomParaManager 
{
	private double         randCarEntryRate;
	private double         randRoadSegmentLength;
	private double         randIntersectionLength;
	private double         randCarLength;
	private	double         randCarMaximumVelocity;
	private	double         randCarStopDistance;
	private	double         randCarBrakeDistance;
	private	double         randTrafficLightGreenTime;
	private double         randTrafficLightYellowTime;
	
	public randomParaManager()
	{
	ParameterManager.getParameterManager();
	}
	public double getRandCarEntryRate()
	{
		randCarEntryRate = Math.random() * (ParameterManager.getParameterManager().getCarMaximumVelocity().getMax() 
				- ParameterManager.getParameterManager().getCarMaximumVelocity().getMin())
				+ ParameterManager.getParameterManager().getCarMaximumVelocity().getMin();
		return randCarEntryRate;
	}
	public double getRandRoadSegmentLength()
	{
		randRoadSegmentLength = Math.random() * (ParameterManager.getParameterManager().getRoadSegmentLength().getMax() 
				- ParameterManager.getParameterManager().getRoadSegmentLength().getMin())
				+ ParameterManager.getParameterManager().getRoadSegmentLength().getMin();
		return randRoadSegmentLength;
	}
	public double getRandIntersectionLength()
	{
		randIntersectionLength = Math.random() * (ParameterManager.getParameterManager().getIntersectionLength().getMax() 
				- ParameterManager.getParameterManager().getIntersectionLength().getMin())
				+ ParameterManager.getParameterManager().getIntersectionLength().getMin();
		return randIntersectionLength;
	}
	public double getRandCarLength()
	{
		randCarLength = Math.random() * (ParameterManager.getParameterManager().getCarLength().getMax() 
				- ParameterManager.getParameterManager().getCarLength().getMin())
				+ ParameterManager.getParameterManager().getCarLength().getMin();
		return randCarLength;
	}
	public double getRandCarMaximumVelocity()
	{
		randCarMaximumVelocity = Math.random() * (ParameterManager.getParameterManager().getCarMaximumVelocity().getMax() 
				- ParameterManager.getParameterManager().getCarMaximumVelocity().getMin())
				+ ParameterManager.getParameterManager().getCarMaximumVelocity().getMin();
		return randCarMaximumVelocity;
	}
	public double getRandCarStopDistance()
	{
		randCarStopDistance = Math.random() * (ParameterManager.getParameterManager().getCarStopDistance().getMax() 
				- ParameterManager.getParameterManager().getCarStopDistance().getMin())
				+ ParameterManager.getParameterManager().getCarStopDistance().getMin();
		return randCarStopDistance;
	}
	public double getRandCarBrakeDistance()
	{
		randCarBrakeDistance = Math.random() * (ParameterManager.getParameterManager().getCarBrakeDistance().getMax() 
				- ParameterManager.getParameterManager().getCarBrakeDistance().getMin())
				+ ParameterManager.getParameterManager().getCarBrakeDistance().getMin();
		return randCarBrakeDistance;
	}
	public double getRandTrafficLightGreenTime()
	{
		randTrafficLightGreenTime = Math.random() * (ParameterManager.getParameterManager().getTrafficLightGreenTime().getMax() 
				- ParameterManager.getParameterManager().getTrafficLightGreenTime().getMin())
				+ ParameterManager.getParameterManager().getTrafficLightGreenTime().getMin();
		return randTrafficLightGreenTime;
	}
	public double getRandTrafficLightYellowTime()
	{
		randTrafficLightYellowTime = Math.random() * (ParameterManager.getParameterManager().getTrafficLightYellowTime().getMax() 
				- ParameterManager.getParameterManager().getTrafficLightYellowTime().getMin())
				+ ParameterManager.getParameterManager().getTrafficLightYellowTime().getMin();
		return randTrafficLightYellowTime;
	}
}
