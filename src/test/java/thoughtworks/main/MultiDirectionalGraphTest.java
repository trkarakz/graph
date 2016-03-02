package thoughtworks.main;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import thoughtworks.main.graph.DefaultGraph;
import thoughtworks.main.nodes.MultiDirectionalNode;
import thoughtworks.main.strategy.MultiDirectionalGraphStrategy;


public class MultiDirectionalGraphTest {

	@Mock
	MultiDirectionalNode nodeA, nodeB, nodeC, nodeD, nodeE; 
	
	@Before
	public void prepareNodes() {
    	nodeA = new MultiDirectionalNode("A");
    	nodeB = new MultiDirectionalNode("B");
    	nodeC = new MultiDirectionalNode("C");
    	nodeD = new MultiDirectionalNode("D");
    	nodeE = new MultiDirectionalNode("E");
	}
	
	@Test
	public void MultiDirectionalNodeAddNodeShouldBeSuccessful() {
		ArrayList<Integer> distanceList = new ArrayList<Integer>();
		distanceList.add(5);
    	nodeA.addNode(nodeB, distanceList);
    	Assert.assertEquals(true, nodeA.isConnected(nodeB));
    	Assert.assertEquals(false, nodeA.isConnected(nodeC));
	}
	
	@Test
	public void uniDirectionalGraphAddNodesShouldBeSuccessful() {
    	DefaultGraph<MultiDirectionalNode> graph = new DefaultGraph<MultiDirectionalNode>(new MultiDirectionalGraphStrategy());
    	graph.addNodes(nodeA, nodeB, nodeC, nodeD, nodeE);
    	
    	Assert.assertEquals(nodeA, graph.getNode(nodeA));
    	Assert.assertEquals(nodeB, graph.getNode(nodeB));
    	Assert.assertEquals(nodeC, graph.getNode(nodeC));
    	Assert.assertEquals(nodeD, graph.getNode(nodeD));
    	Assert.assertEquals(nodeE, graph.getNode(nodeE));
		
	}
	
	@SuppressWarnings("serial")
	@Test
	public void uniDirectionalGraphShouldBeInstalledSuccessfully() {
		// Desired Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		nodeA.addNode(nodeB, new ArrayList<Integer>() {{add(5);}});
		nodeA.addNode(nodeD, new ArrayList<Integer>() {{add(5);}});
		nodeA.addNode(nodeE, new ArrayList<Integer>() {{add(7);}});
		nodeB.addNode(nodeC, new ArrayList<Integer>() {{add(4);}});
		nodeC.addNode(nodeD, new ArrayList<Integer>() {{add(8);}});
		nodeC.addNode(nodeE, new ArrayList<Integer>() {{add(2);}});
		nodeD.addNode(nodeC, new ArrayList<Integer>() {{add(8);}});
		nodeD.addNode(nodeE, new ArrayList<Integer>() {{add(6);}});
		nodeE.addNode(nodeB, new ArrayList<Integer>() {{add(3);}});
		
    	DefaultGraph<MultiDirectionalNode> graph = new DefaultGraph<MultiDirectionalNode>(new MultiDirectionalGraphStrategy());
		graph.addNodes(nodeA, nodeB, nodeC, nodeD, nodeE);
		
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
