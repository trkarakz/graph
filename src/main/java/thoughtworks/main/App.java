package thoughtworks.main;

import java.util.ArrayList;

import thoughtworks.main.graph.DefaultGraph;
import thoughtworks.main.nodes.MultiDirectionalNode;
import thoughtworks.main.nodes.UniDirectionalNode;
import thoughtworks.main.strategy.MultiDirectionalGraphStrategy;
import thoughtworks.main.strategy.UniDirectionalGraphStrategy;

/**
 * Graph implementation with strategy pattern and java generic types
 *
 */
public class App 
{
    @SuppressWarnings("serial")
	public static void main( String[] args )
    {
        // Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
    	UniDirectionalNode nodeA = new UniDirectionalNode("A");
    	UniDirectionalNode nodeB = new UniDirectionalNode("B");
    	UniDirectionalNode nodeC = new UniDirectionalNode("C");
    	UniDirectionalNode nodeD = new UniDirectionalNode("D");
    	UniDirectionalNode nodeE = new UniDirectionalNode("E");
    	
    	nodeA.addNode(nodeB, 5).addNode(nodeD, 5).addNode(nodeE, 7);
    	nodeB.addNode(nodeC, 4);
    	nodeC.addNode(nodeD, 8).addNode(nodeE, 2);
    	nodeD.addNode(nodeC, 8).addNode(nodeE, 6);
    	nodeE.addNode(nodeB, 3);
    	
    	DefaultGraph<UniDirectionalNode> graph = new DefaultGraph<UniDirectionalNode>(new UniDirectionalGraphStrategy());
    	graph.addNodes(nodeA, nodeB, nodeC, nodeD, nodeE);
    	
    	System.out.println( "Graph with uni directional nodes" );
    	System.out.println( "--------------------------------" );
    	System.out.println("1) The distance of the route A-B-C = " + graph.calculateDistanceOfRoute(nodeA, nodeB, nodeC));
    	System.out.println("2) The distance of the route A-D = " + graph.calculateDistanceOfRoute(nodeA, nodeD));
    	System.out.println("3) The distance of the route A-D-C = " + graph.calculateDistanceOfRoute(nodeA, nodeD, nodeC));
    	System.out.println("4) The distance of the route A-E-B-C-D = " + graph.calculateDistanceOfRoute(nodeA, nodeE, nodeB, nodeC, nodeD));
    	System.out.println("5) The distance of the route A-E-D = " + graph.calculateDistanceOfRoute(nodeA, nodeE, nodeD));
    	System.out.println("6) The number of trips starting at C and ending at C with a maximum of 3 stops = " + graph.calculateTheNumberOfTripsWithMaxStop(nodeC, nodeC, 3));
    	System.out.println("7) The number of trips starting at A and ending at C with exactly 4 stops = " + graph.calculateTheNumberOfTripsWithExactStopNumber(nodeA, nodeC, 4));
    	System.out.println("8) The length of the shortest route from A to C = " + graph.calculateTheLengthOfTheShortestRoute(nodeA, nodeC));
    	System.out.println("9) The length of the shortest route from B to B = " + graph.calculateTheLengthOfTheShortestRoute(nodeB, nodeB));
    	System.out.println("10) The number of different routes from C to C with a distance of less than 30 = " + graph.calculateTheNumberOfDifferentRoutes(nodeC, nodeC, 30));

    	System.out.println( "" );
    	System.out.println( "-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+" );
    	System.out.println( "" );
    	System.out.println( "The graph" );
    	System.out.println( graph );
    	System.out.println( "" );
    	System.out.println( "" );
    	

    	// The graph with UniDirectionalNodes can be implemented with MultiDirectionalNodes with MultiDirectionalGraphStrategy 
    	// This part aim is just sampling the strategy pattern usage
    	
    	MultiDirectionalNode multiNodeA = new MultiDirectionalNode("A");
    	MultiDirectionalNode multiNodeB = new MultiDirectionalNode("B");
    	MultiDirectionalNode multiNodeC = new MultiDirectionalNode("C");
    	MultiDirectionalNode multiNodeD = new MultiDirectionalNode("D");
    	MultiDirectionalNode multiNodeE = new MultiDirectionalNode("E");
    	
    	multiNodeA.addNode(multiNodeB, new ArrayList<Integer>() {{add(5);add(3);}});
    	multiNodeA.addNode(multiNodeD, new ArrayList<Integer>() {{add(5);add(3);}});
    	multiNodeA.addNode(multiNodeE, new ArrayList<Integer>() {{add(7);}});
    	multiNodeB.addNode(multiNodeC, new ArrayList<Integer>() {{add(4);}});
    	multiNodeC.addNode(multiNodeD, new ArrayList<Integer>() {{add(8);}});
    	multiNodeC.addNode(multiNodeE, new ArrayList<Integer>() {{add(2);}});
    	multiNodeD.addNode(multiNodeC, new ArrayList<Integer>() {{add(8);}});
    	multiNodeD.addNode(multiNodeE, new ArrayList<Integer>() {{add(6);}});
    	multiNodeE.addNode(multiNodeB, new ArrayList<Integer>() {{add(3);}});
    	
    	DefaultGraph<MultiDirectionalNode> multiGraph = new DefaultGraph<MultiDirectionalNode>(new MultiDirectionalGraphStrategy());
    	multiGraph.addNodes(multiNodeA, multiNodeB, multiNodeC, multiNodeD, multiNodeE);
    	
    	System.out.println( "Graph with multi directional nodes" );
    	System.out.println( "--------------------------------" );
    	System.out.println("The distance of the route A-B-C = " + multiGraph.calculateDistanceOfRoute(multiNodeA, multiNodeB, multiNodeC));
    	System.out.println("The distance of the route A-D = " + multiGraph.calculateDistanceOfRoute(multiNodeA, multiNodeD));

    	System.out.println( multiGraph );
    
    
    }
}
