package cmsc256;

import bridges.base.*;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.EarthquakeUSGS;
import bridges.data_src_dependent.OsmVertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GraphEarthquakeData {

	static class MagnitudeComparator implements Comparator<EarthquakeUSGS> {
		@Override
		public int compare(EarthquakeUSGS E1, EarthquakeUSGS E2) {
			if (E1.getMagnitude() < E2.getMagnitude()) {
				return 1;
			}
			else if (E1.getMagnitude() > E2.getMagnitude()) {
				return -1;
			}
			else if (E1.getMagnitude() == E2.getMagnitude()) {
				return 0;
			}
			return 0;
		}
	}

  public static double calcDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
		final int radius = 6371; // Radius of the earth in km

		// Haversine formula to calculate a value between 0 and 1 between 2 points on a sphere,
		//  1 being the opposite side of the sphere
		double laDistance = Math.toRadians(latitude2 - latitude1);
		double loDistance = Math.toRadians(longitude2 - longitude1);

		double a = Math.sin(laDistance / 2) * Math.sin(laDistance / 2)
				+ Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
				* Math.sin(loDistance / 2) * Math.sin(loDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	  return radius * c;
	}


  public static void main(String[] args) throws Exception {
	// Create a Bridges object
	  Bridges bridges = new Bridges(4,"wardpl2","133617755398");

	// Get a DataSource object from Bridges
	  DataSource ds = bridges.getDataSource();
    	// Set an assignment title
	bridges.setTitle("Earthquake Data Graph Lab");
	bridges.setDescription("CMSC 256, Spring 2021");

	// Initialize a Graph
	GraphAdjListSimple<String> graph = new GraphAdjListSimple<>();


	/* TODO:
	* Grab Earthquake data and store it in a List
	* Sort the list by magnitude
	* Retain only 100 earthquakes of highest magnitude
	 */
	  List<EarthquakeUSGS> eq_list = ds.getEarthquakeUSGSData(5000);
	  eq_list.sort(new MagnitudeComparator());

	  List<EarthquakeUSGS> listOfTen = new ArrayList<>();
	  for (int i = 0; i < 100; i++) {
		  listOfTen.add(eq_list.get(i));
	  }


    /* TODO:
    * Add the Earthquakes to the graph
    * Set each earthquake's location based on its latitude and longitude
    * ex: graph.getVisualizer(key).setLocation(earthquake.getLongit(), earthquake.getLatit());
    * Tweak the colors or other visual elements if you wish; For example, if the magnitude is higher than 6, set the color to red
    */

	  for (EarthquakeUSGS eq : listOfTen) {
		  graph.addVertex(eq.getTitle(), String.valueOf(eq.getMagnitude()));
		  graph.getVisualizer(eq.getTitle()).setLocation(eq.getLongit(), eq.getLatit());
		  if (eq.getMagnitude() >= 6.5) {
			  graph.getVisualizer(eq.getTitle()).setSize(2.5);
			  graph.getVisualizer(eq.getTitle()).setColor("red");
		  }
		  else if (eq.getMagnitude() >= 5.5 && eq.getMagnitude() < 7) {
			  graph.getVisualizer(eq.getTitle()).setSize(2);
			  graph.getVisualizer(eq.getTitle()).setColor("orange");
		  } else {
			  graph.getVisualizer(eq.getTitle()).setSize(1);
		  }
	  }


    bridges.setCoordSystemType("equirectangular");
    bridges.setDataStructure(graph);
    bridges.setMapOverlay(true);
    bridges.setTitle("Earthquake Map");
    bridges.visualize();


    /* TODO:
    * Compare the distances between all vertexes in the graph, drawing an edge
    * if they are within 500km. A method is provided to give a rough
    * estimate between 2 lat,long points.
    *
    * example usage: calcDistance(eq1.getLatit(), eq1.getLongit(),
    *                eq2.getLatit(), eq2.getLongit());
    * which returns a double representing the distance of two points in km
    */
	  for (int i = 0; i < 10; i++) {
		  EarthquakeUSGS eq1 = listOfTen.get(i);
		  for (int j = i+1; j < listOfTen.size()-i-1; j++) {
			  EarthquakeUSGS eq2 = listOfTen.get(j);
			  if (calcDistance(eq1.getLatit(), eq1.getLongit(), eq2.getLatit(), eq2.getLongit()) <= 500) {
				  graph.addEdge(eq1.getTitle(), eq2.getTitle());
			  }
		  }
	  }


    bridges.visualize();

    /* TODO:
    * Reset the locations of the vertices by setting their location to
    * Double.POSITIVE_INFINITY
    *
    * ex: graph.getVisualizer(key).setLocation(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
    */

	  for (EarthquakeUSGS eq : listOfTen) {
		  graph.getVisualizer(eq.getTitle()).setLocation(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	  }

    bridges.setMapOverlay(false);
    bridges.visualize();
  }
}
