package managers;

import model.MinMax;
import model.RowColumn;
import model.TrafficPattern;

public class ParameterManager
{
	private static ParameterManager parameterManager = new ParameterManager();
	
	private float          simulationTimeStep     = 0.1f;
	private float          simulationRunTime      = 1000.0f;
	private RowColumn      gridSize               = new RowColumn(2,3); 
	private TrafficPattern trafficPattern         = TrafficPattern.ALTERNATING;
	private MinMax         carEntryRate           = new MinMax(  2.0f,  25.0f);
	private MinMax         roadSegmentLength      = new MinMax(200.0f, 500.0f);
	private MinMax         intersectionLength     = new MinMax( 10.0f,  15.0f);
	private MinMax         carLength              = new MinMax(  5.0f,  10.0f);
	private	MinMax         carMaximumVelocity     = new MinMax(10.0f,   30.0f);
	private	MinMax         carStopDistance        = new MinMax(  0.5f,   5.0f);
	private	MinMax         carBrakeDistance       = new MinMax(  9.0f,  10.0f);
	private	MinMax         trafficLightGreenTime  = new MinMax(30.0f,  180.0f);
	private MinMax         trafficLightYellowTime = new MinMax(  4.0f,   5.0f);
	
	private ParameterManager()
	{
	}
	
	public static ParameterManager getParameterManager()
	{
		return parameterManager;
	}
	
	public void load(float newSimulationTimeStep, 
					 float newSimulationRunTime, 
					 RowColumn newGridSize, 
					 TrafficPattern newTrafficPattern,
					 MinMax newCarEntryRate,
					 MinMax newRoadSegmentLength,
					 MinMax newIntersectionLength,
					 MinMax newCarLength,
					 MinMax newCarMaximumVelocity,
					 MinMax newCarStopDistance,
					 MinMax newCarBrakeDistance,
					 MinMax newTrafficLightGreenTime,
					 MinMax newTrafficLightYellowTime)
	{
		simulationTimeStep     = newSimulationTimeStep;
		simulationRunTime      = newSimulationRunTime;
		gridSize               = newGridSize; 
		trafficPattern         = newTrafficPattern;
		carEntryRate           = newCarEntryRate;
		roadSegmentLength      = newRoadSegmentLength;
	    intersectionLength     = newIntersectionLength;
	    carLength              = newCarLength;
		carMaximumVelocity     = newCarMaximumVelocity;
		carStopDistance        = newCarStopDistance;
		carBrakeDistance       = newCarBrakeDistance;
		trafficLightGreenTime  = newTrafficLightGreenTime;
		trafficLightYellowTime = newTrafficLightYellowTime;
	}
	
	public float getSimulationTimeStep()
	{
		return simulationTimeStep; 
	}
	
	public float getSimulationRunTime()
	{
		return simulationRunTime;
	}
	
	public RowColumn getGridSize()
	{
		return gridSize;
	}
	
	public TrafficPattern getTrafficPattern()
	{
		return trafficPattern;
	}
	
	public MinMax getCarEntryRate()
	{
		return carEntryRate;
	}
	
	public MinMax getRoadSegmentLength()
	{
		return roadSegmentLength;
	}
	
	public MinMax getIntersectionLength() 
	{
		return intersectionLength;
	}
	
	public MinMax getCarLength()
	{ 
		return carLength;
	}
	
	public MinMax getCarMaximumVelocity()
	{
		return carMaximumVelocity;
	}
	
	public MinMax getCarStopDistance()
	{
		return carStopDistance;
	}
	
	public MinMax getCarBrakeDistance()
	{
		return carBrakeDistance;
	}
	
	public MinMax getTrafficLightGreenTime()
	{
		return trafficLightGreenTime;
	}
	
	public MinMax getTrafficLightYellowTime()
	{
		return trafficLightYellowTime;
	}
}