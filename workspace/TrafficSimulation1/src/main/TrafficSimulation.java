package main;

import parser.JSONFileParameterParser;
import managers.ParameterManager;
import model.Model;
import model.swing.SwingAnimatorBuilder;


/**
 * A static class to demonstrate the visualization aspect of
 * simulation.
 */
public class TrafficSimulation{
	private TrafficSimulation() {}
	public static void main(String[] args) {
		{
			JSONFileParameterParser.loadParameters("simulationparameters.json");
			Model m = new Model(new SwingAnimatorBuilder(), 
					ParameterManager.getParameterManager().getGridSize().getRow(), 
					ParameterManager.getParameterManager().getGridSize().getColumn());
			
			m.run(ParameterManager.getParameterManager().getSimulationRunTime());
			m.dispose();
		}
		System.exit(0);
	}
}

