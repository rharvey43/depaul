package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import managers.ParameterManager;
import util.Extractor;

public class JSONFileParameterParser
{
	private JSONFileParameterParser()
	{
	}
	
	public static void loadParameters(String fileName)
	{
		try
		{ 
	        JSONParser parser = new JSONParser();
	 
	        Object obj = parser.parse(new FileReader(fileName));
	 
	        JSONObject jsonObject = (JSONObject) obj;
	 
	        JSONArray jsonArray = (JSONArray) jsonObject.get("simulationparameters");
	        
        	JSONObject someShape = (JSONObject)jsonArray.get(0);

		    ParameterManager.getParameterManager().load(Extractor.extractFloat(someShape.get("simulationTimeStep")),
							  	  						Extractor.extractFloat(someShape.get("simulationRunTime")),
							  							Extractor.extractJsonRowColumn(someShape.get("gridSize")),
							  							Extractor.extractTrafficPattern(someShape.get("trafficPattern")),
							  							Extractor.extractJsonMinMax(someShape.get("carEntryRate")),
							  							Extractor.extractJsonMinMax(someShape.get("roadSegmentLength")),
							  							Extractor.extractJsonMinMax(someShape.get("intersectionLength")),
							  							Extractor.extractJsonMinMax(someShape.get("carLength")),
							  							Extractor.extractJsonMinMax(someShape.get("carMaximumVelocity")),
							  							Extractor.extractJsonMinMax(someShape.get("carStopDistance")),
							  							Extractor.extractJsonMinMax(someShape.get("carBrakeDistance")),
							  							Extractor.extractJsonMinMax(someShape.get("trafficLightGreenTime")),
							  							Extractor.extractJsonMinMax(someShape.get("trafficLightYellowTime")));
		}
        catch(FileNotFoundException eFileNotFound)
        {
        }
        catch(IOException eIOException)
        {
        	
        }
        catch(ParseException eParseException)
        {
        }
	}
}
      