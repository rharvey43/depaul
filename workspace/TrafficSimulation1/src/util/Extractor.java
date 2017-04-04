package util;

import org.json.simple.JSONObject;

import model.MinMax;
import model.RowColumn;
import model.TrafficPattern;

/**
*
*  The parser for the shapes file
* 
* @author Anthony Freund
*
*/

public class Extractor
{
	final public static Integer extractInteger(final Object object)
	{
		return Integer.parseInt(object.toString(), 10);
	}

	final public static Float extractFloat(final Object object)
	{
		return Float.parseFloat(object.toString());
	}
	
	final public static MinMax extractJsonMinMax(final Object object)
	{
		JSONObject jsonObject = (JSONObject)object;
		
		return new MinMax(extractFloat(jsonObject.get("min")),extractFloat(jsonObject.get("max")));
	}
	
	final public static RowColumn extractJsonRowColumn(final Object object)
	{
		JSONObject jsonObject = (JSONObject)object;
		
		return new RowColumn(extractInteger(jsonObject.get("row")),extractInteger(jsonObject.get("column")));
	}
	
	final public static TrafficPattern extractTrafficPattern(final Object object)
	{
		return object.equals("alternating") ? TrafficPattern.ALTERNATING : TrafficPattern.SIMPLE;
	}
}
