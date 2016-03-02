package thoughtworks.main.strategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import thoughtworks.main.StopNumberMode;
import thoughtworks.main.nodes.UniDirectionalNode;

/**
 * A UniDirectionalGraphStrategy executes on a graph which contains only UniDirectionalNodes
 * 
 * @author zulfikarakaya
 *
 */
public class UniDirectionalGraphStrategy implements GraphStrategy<UniDirectionalNode> {

	private final Map<String, UniDirectionalNode> graph = new HashMap<String, UniDirectionalNode>();
	
	public void addNodesToGraph(UniDirectionalNode... nodes) {
		for (UniDirectionalNode node : nodes) {
			graph.put(node.getId(), node);
		}
	}
	
	/**
	 * Calculate the length of the route which contains all nodes 
	 * returns -1 if there is no such route
	 */
	public Integer distanceOfRoute(UniDirectionalNode... nodes) {
		return findDistanceOfRoute(0, 0, nodes);
	}
	
	private Integer findDistanceOfRoute(int routeIndex, Integer totalDistance, UniDirectionalNode... nodes) {
		if (nodes == null) return -1; // No such Route
		if (nodes.length < 2) return -1; // No such Route 
		if (routeIndex == nodes.length-1) return totalDistance; // return the distance 
		
		UniDirectionalNode sourceNode = nodes[routeIndex];
		UniDirectionalNode destinationNode = nodes[routeIndex + 1];

		Integer distanceToDestination = sourceNode.getWeight(destinationNode);
		
		if (distanceToDestination == -1) return -1;
		
		return findDistanceOfRoute(routeIndex + 1, totalDistance + distanceToDestination, nodes);
	}

	public UniDirectionalNode getNodeFromGraph(UniDirectionalNode node) {
		return graph.get(node.getId());
	}

	/**
	 * calculate number of trips according to stop number and stop number mode
	 */
	public Integer numberOfTrips(UniDirectionalNode startNode, UniDirectionalNode endNode,
			int stopNumber, StopNumberMode stopNumberMode) {
		
		if (StopNumberMode.MAX == stopNumberMode)
			return findNumberOfTripsWithMaxStop(startNode, endNode, stopNumber, 0);
		else  // StopNumberMode.EXACT == stopNumberMode
			return findNumberOfTripsWithExactStopNumber(startNode, endNode, stopNumber, 0);
	}

	private Integer findNumberOfTripsWithMaxStop(UniDirectionalNode startNode, UniDirectionalNode endNode, int maxStopNumber, int stopNumber) {
		Integer numberOfTrips = 0;
	
		if (stopNumber > 0) {
			if (stopNumber <= maxStopNumber && startNode.equals(endNode)) { 
				return 1;
			}
			else if (stopNumber > maxStopNumber && !startNode.equals(endNode)) { 
				return 0;
			}
		}
		
		for (Entry<UniDirectionalNode, Integer> entry : startNode.getConnectedNodes().entrySet()) {
			UniDirectionalNode connectedNode = entry.getKey();
			numberOfTrips = numberOfTrips + findNumberOfTripsWithMaxStop(connectedNode, endNode, maxStopNumber, stopNumber+1);
		}
		
		return numberOfTrips;
	}

	private Integer findNumberOfTripsWithExactStopNumber(UniDirectionalNode startNode, UniDirectionalNode endNode, int exactStopNumber, int stopNumber) {
		Integer numberOfTrips = 0;
		
		if (stopNumber > 0) {
			if (stopNumber == exactStopNumber && startNode.equals(endNode)) { 
				return 1;
			}
			else if (stopNumber == exactStopNumber && !startNode.equals(endNode)) { 
				return 0;
			}
		}
		
		for (Entry<UniDirectionalNode, Integer> entry : startNode.getConnectedNodes().entrySet()) {
			UniDirectionalNode connectedNode = entry.getKey();
			numberOfTrips = numberOfTrips + findNumberOfTripsWithExactStopNumber(connectedNode, endNode, exactStopNumber, stopNumber+1);
		}
		
		return numberOfTrips;
	}
	
	/**
	 * find the shortest route length between startNode and endNode
	 * returns -1 if there is no such route
	 * 
	 * @param startNode
	 * @param endNode
	 * @param path
	 * @param distance
	 * @return
	 */
	public Integer lengthOfTheShortestRoute(UniDirectionalNode startNode, UniDirectionalNode endNode) {
		return findLengthOfTheShortestRoute(startNode, endNode, "", 0);
	}
	
	private Integer findLengthOfTheShortestRoute(UniDirectionalNode startNode, UniDirectionalNode endNode, String path, Integer distance) {
		
		Integer shortestDistance = Integer.MAX_VALUE; 
		
		if (startNode.equals(endNode) && !path.isEmpty()) {
			return distance;
		}

		if (path.contains(startNode.getId())) {
			return Integer.MAX_VALUE;
		}
		
		path = path + startNode.getId();
		
		for (Entry<UniDirectionalNode, Integer> entry : startNode.getConnectedNodes().entrySet()) {
			UniDirectionalNode connectedNode = entry.getKey();
			Integer distanceOfConnectedNode = entry.getValue();
			
			Integer calculatedDistance = findLengthOfTheShortestRoute(connectedNode, endNode, path, distance + distanceOfConnectedNode);
			if (shortestDistance > calculatedDistance){
				shortestDistance = calculatedDistance;
			}
		}
		
		return (shortestDistance == Integer.MAX_VALUE ? -1 : shortestDistance);
	}
	

	public Map<String, UniDirectionalNode> getGraph() {
		return graph;
	}

	
	/**
	 * find the number of different routes until the distance reached
	 * returns -1 if there is no such route
	 * 
	 * @param startNode
	 * @param endNode
	 * @param distance
	 * @return
	 */
	public Integer numberOfDifferentRoutes(UniDirectionalNode startNode, UniDirectionalNode endNode, Integer maxDistance) {
		Set<String> paths = findDifferentRoutes(startNode, endNode, new HashSet<String>(), "", maxDistance, 0);
		return paths.size();
	}

	private Set<String> findDifferentRoutes(UniDirectionalNode startNode,
			UniDirectionalNode endNode, Set<String> paths, String path, Integer maxDistance, Integer distance) {
		
		Set<String> allPaths = new HashSet<String>();
		
		if (distance >= maxDistance) {
			return paths;
		}
		
		path = path + startNode.getId();
		
		if (distance != 0 && distance < maxDistance && startNode.equals(endNode)) {
			paths.add(path);
		} 

		for (Entry<UniDirectionalNode, Integer> entry : startNode.getConnectedNodes().entrySet()) {
			UniDirectionalNode connectedNode = entry.getKey();
			Integer distanceOfConnectedNode = entry.getValue();
			
			Set<String> calculatedPaths = findDifferentRoutes(connectedNode, endNode, paths, path, maxDistance, distance + distanceOfConnectedNode);
			allPaths.addAll(calculatedPaths);
		}
		
		return allPaths;
	}
	
	
}
