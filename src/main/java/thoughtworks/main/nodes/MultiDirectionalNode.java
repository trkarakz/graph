package thoughtworks.main.nodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiDirectionalNode extends BaseNode<MultiDirectionalNode> {

	private Map<MultiDirectionalNode, List<Integer>> connectedNodes = new HashMap<MultiDirectionalNode, List<Integer>>();

	public MultiDirectionalNode(String id) {
		super(id);
	}

	public void addNode(MultiDirectionalNode node, List<Integer> weight) {
		connectedNodes.put(node, weight);
	}

	public boolean isConnected(MultiDirectionalNode destinationNode) {
		return connectedNodes.containsKey(destinationNode);
	}
	
	public List<Integer> getWeight(MultiDirectionalNode destinationNode) {
		if (isConnected(destinationNode))
			return connectedNodes.get(destinationNode);
		else 
			return null; // no connection
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		for (Map.Entry<MultiDirectionalNode, List<Integer>> entry : connectedNodes.entrySet()) {
			for (Integer weight : entry.getValue()) {
				result.append("[").append(getId()).append(entry.getKey().getId()).append(weight).append("]");
			}
		}		
		
		return result.toString();
	}


}
