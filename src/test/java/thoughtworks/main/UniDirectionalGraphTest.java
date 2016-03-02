package thoughtworks.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import thoughtworks.main.graph.DefaultGraph;
import thoughtworks.main.nodes.UniDirectionalNode;
import thoughtworks.main.strategy.UniDirectionalGraphStrategy;


public class UniDirectionalGraphTest {

	UniDirectionalNode nodeA, nodeB, nodeC, nodeD, nodeE; 
	DefaultGraph<UniDirectionalNode> graph;
	
	@Before
	public void prepareNodes() {
    	nodeA = new UniDirectionalNode("A");
    	nodeB = new UniDirectionalNode("B");
    	nodeC = new UniDirectionalNode("C");
    	nodeD = new UniDirectionalNode("D");
    	nodeE = new UniDirectionalNode("E");

    	// Desired Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		nodeA.addNode(nodeB, 5);
		nodeA.addNode(nodeD, 5);
		nodeA.addNode(nodeE, 7);
		nodeB.addNode(nodeC, 4);
		nodeC.addNode(nodeD, 8);
		nodeC.addNode(nodeE, 2);
		nodeD.addNode(nodeC, 8);
		nodeD.addNode(nodeE, 6);
		nodeE.addNode(nodeB, 3);

		graph = new DefaultGraph<UniDirectionalNode>(new UniDirectionalGraphStrategy());
		graph.addNodes(nodeA, nodeB, nodeC, nodeD, nodeE);
	}
	
	@Test
	public void uniDirectionalNodeAddNodeShouldBeSuccessful() {
    	Assert.assertEquals(true, nodeA.isConnected(nodeB));
    	Assert.assertEquals(false, nodeA.isConnected(nodeC));
	}
	
	@Test
	public void distanceOfRouteShouldBeCalculated() {
		Assert.assertEquals("9", graph.calculateDistanceOfRoute(nodeA, nodeB, nodeC));
		Assert.assertEquals("5", graph.calculateDistanceOfRoute(nodeA, nodeD));
		Assert.assertEquals("13", graph.calculateDistanceOfRoute(nodeA, nodeD, nodeC));
		Assert.assertEquals("22", graph.calculateDistanceOfRoute(nodeA, nodeE, nodeB, nodeC, nodeD));
		Assert.assertEquals("NO SUCH ROUTE", graph.calculateDistanceOfRoute(nodeA, nodeE, nodeD));
	}
	
	@Test
	public void numberOfTripsShouldBeCalculated() {
		Assert.assertEquals(new Integer(2), graph.calculateTheNumberOfTripsWithMaxStop(nodeC, nodeC, 3));
		Assert.assertEquals(new Integer(3), graph.calculateTheNumberOfTripsWithExactStopNumber(nodeA, nodeC, 4));
	}
	
	@Test
	public void lengthOfTheShortestRouteShouldBeCalculated() {
		Assert.assertEquals("9", graph.calculateTheLengthOfTheShortestRoute(nodeA, nodeC));
		Assert.assertEquals("9", graph.calculateTheLengthOfTheShortestRoute(nodeB, nodeB));
	}
	
	@Test
	public void numberOfDifferentRoutesShouldBeCalculated() {
		Assert.assertEquals(new Integer(7), graph.calculateTheNumberOfDifferentRoutes(nodeC, nodeC, 30));
	}
	
	@Test
	public void uniDirectionalGraphAddNodesShouldBeSuccessful() {
    	Assert.assertEquals(nodeA, graph.getNode(nodeA));
    	Assert.assertEquals(nodeB, graph.getNode(nodeB));
    	Assert.assertEquals(nodeC, graph.getNode(nodeC));
    	Assert.assertEquals(nodeD, graph.getNode(nodeD));
    	Assert.assertEquals(nodeE, graph.getNode(nodeE));
		
	}
	
	@Test
	public void uniDirectionalGraphShouldBeInstalledSuccessfully() {
		String graphString = graph.toString();
		
		Assert.assertEquals(true, graphString.contains("AB5"));
		Assert.assertEquals(true, graphString.contains("BC4"));
		Assert.assertEquals(true, graphString.contains("CD8"));
		Assert.assertEquals(true, graphString.contains("DC8"));
		Assert.assertEquals(true, graphString.contains("DE6"));
		Assert.assertEquals(true, graphString.contains("AD5"));
		Assert.assertEquals(true, graphString.contains("CE2"));
		Assert.assertEquals(true, graphString.contains("EB3"));
		Assert.assertEquals(true, graphString.contains("AE7"));
		Assert.assertEquals(false, graphString.contains("AB6"));
		Assert.assertEquals(false, graphString.contains("AC"));
		Assert.assertEquals(false, graphString.contains("EC"));
		Assert.assertEquals(false, graphString.contains("DD"));
		Assert.assertEquals(false, graphString.contains("BD"));
	}
	
}
