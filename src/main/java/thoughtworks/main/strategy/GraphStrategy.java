package thoughtworks.main.strategy;

import java.util.Map;

import thoughtworks.main.StopNumberMode;
import thoughtworks.main.nodes.BaseNode;

public interface GraphStrategy<T extends BaseNode<T>> {
	public Map<String, T> getGraph();
	public void addNodesToGraph(T... nodes);
	public T getNodeFromGraph(T node);
	
	public Integer distanceOfRoute(T... nodes);
	public Integer numberOfTrips(T startNode, T endNode, int maxStopNumber, StopNumberMode stopNumberMode);
	public Integer lengthOfTheShortestRoute(T startNode, T endNode);
	public Integer numberOfDifferentRoutes(T startNode, T endNode, Integer maxDistance);
	
}
