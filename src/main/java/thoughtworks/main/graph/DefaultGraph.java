package thoughtworks.main.graph;

import thoughtworks.main.StopNumberMode;
import thoughtworks.main.nodes.BaseNode;
import thoughtworks.main.strategy.GraphStrategy;


/**
 * default graph implementation
 * 
 * @author zulfikarakaya
 *
 */
public class DefaultGraph<T extends BaseNode<T>> {

	private GraphStrategy<T> strategy;

	public DefaultGraph(GraphStrategy<T> strategy) {
		super();
		this.strategy = strategy;
	}

	public void addNodes(T... nodes){
		strategy.addNodesToGraph(nodes);
	}
	
	public T getNode(T node){
		return strategy.getNodeFromGraph(node);
	}
	
	public String calculateDistanceOfRoute(T... nodes) {
		Integer distanceOfRoute = strategy.distanceOfRoute(nodes);
		
		if (distanceOfRoute == -1)
			return "NO SUCH ROUTE";
		
		return distanceOfRoute.toString();
	}
	
	public Integer calculateTheNumberOfTripsWithMaxStop(T startNode, T endNode, int maxStopNumber) {
		return strategy.numberOfTrips(startNode, endNode, maxStopNumber, StopNumberMode.MAX);
	}
	
	public Integer calculateTheNumberOfTripsWithExactStopNumber(T startNode, T endNode, int stopNumber) {
		return strategy.numberOfTrips(startNode, endNode, stopNumber, StopNumberMode.EXACT);
	}
	
	public Integer calculateTheNumberOfDifferentRoutes(T startNode, T endNode, Integer maxDistance) {
		return strategy.numberOfDifferentRoutes(startNode, endNode, maxDistance);
	}
	
	public String calculateTheLengthOfTheShortestRoute(T startNode, T endNode) {
		Integer lengthOfTheShortestRoute = strategy.lengthOfTheShortestRoute(startNode, endNode);
		
		if (lengthOfTheShortestRoute == -1)
			return "NO SUCH ROUTE";
		
		return lengthOfTheShortestRoute.toString();
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("Graph {");
		
		for (T node : strategy.getGraph().values()) {
			result.append(node);
		}
		
		return result.append("}").toString();
	}

	public void setStrategy(GraphStrategy<T> strategy) {
		this.strategy = strategy;
	}

}
