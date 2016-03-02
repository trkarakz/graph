package thoughtworks.main.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thoughtworks.main.StopNumberMode;
import thoughtworks.main.nodes.MultiDirectionalNode;

/**
 * !!!! IMPORTANT This is class is not in the scope of the assignment, it is just a sample of strategy pattern usage
 * 
 * A MultiDirectionalGraphStrategy executes on a graph which contains only MultiDirectionalNodes
 * It can be implemented a UniDirectionalGraphStrategy with MultiDirectionalGraphStrategy and MultiDirectionalNodes
 * 
 * @author zulfikarakaya
 *
 */
public class MultiDirectionalGraphStrategy implements GraphStrategy<MultiDirectionalNode> {

	private final Map<String, MultiDirectionalNode> graph = new HashMap<String, MultiDirectionalNode>();
	
	public void addNodesToGraph(MultiDirectionalNode... nodes) {
		for (MultiDirectionalNode node : nodes) {
			graph.put(node.getId(), node);
		}
	}
	
	public Map<String, MultiDirectionalNode> getGraph() {
		return graph;
	}
	
	public Integer distanceOfRoute(MultiDirectionalNode... nodes) {
		return findDistanceOfRoute(0, 0, nodes);
	}
	
	private Integer findDistanceOfRoute(int routeIndex, Integer totalDistance, MultiDirectionalNode... nodes) {
		if (nodes == null) return -1; // No such Route
		if (nodes.length < 2) return -1; // No such Route 
		if (routeIndex == nodes.length-1) return totalDistance; // return the distance 
		
		MultiDirectionalNode sourceNode = nodes[routeIndex];
		MultiDirectionalNode destinationNode = nodes[routeIndex + 1];

		List<Integer> distanceListToDestination = sourceNode.getWeight(destinationNode);
		
		if (distanceListToDestination == null) return -1;
		
		return findDistanceOfRoute(routeIndex + 1, totalDistance + findMinDistance(distanceListToDestination), nodes);
	}

	private Integer findMinDistance(List<Integer> distanceListToDestination) {
		Integer minDistance = Integer.MAX_VALUE;
		
		for (Integer distance : distanceListToDestination) {
			if (distance < minDistance) minDistance = distance;
		}
		
		return minDistance;
	}

	public MultiDirectionalNode getNodeFromGraph(MultiDirectionalNode node) {
		return graph.get(node.getId());
	}

	public Integer numberOfTrips(MultiDirectionalNode startNode, MultiDirectionalNode endNode,
			int maxStopNumber) {
		return -1; // No Such Route
	}

	public Integer numberOfTrips(MultiDirectionalNode startNode, MultiDirectionalNode endNode,
			int maxStopNumber, StopNumberMode stopNumberMode) {
		// TODO this methods left empty intentionally, because it is not in this assignment scope
		return null;
	}

	public Integer lengthOfTheShortestRoute(MultiDirectionalNode startNode,
			MultiDirectionalNode endNode) {
		// TODO this methods left empty intentionally, because it is not in this assignment scope
		return null;
	}

	public Integer numberOfDifferentRoutes(MultiDirectionalNode startNode,
			MultiDirectionalNode endNode, Integer maxDistance) {
		// TODO this methods left empty intentionally, because it is not in this assignment scope
		return null;
	}


}
