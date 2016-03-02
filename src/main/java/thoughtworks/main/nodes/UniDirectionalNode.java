package thoughtworks.main.nodes;

import java.util.HashMap;
import java.util.Map;

public class UniDirectionalNode extends BaseNode<UniDirectionalNode> {

	private Map<UniDirectionalNode, Integer> connectedNodes = new HashMap<UniDirectionalNode, Integer>();

	public UniDirectionalNode(String id) {
		super(id);
	}

	public void addNode(UniDirectionalNode node, Integer weight) {
		connectedNodes.put(node, weight);
	}

	public boolean isConnected(UniDirectionalNode destinationNode) {
		return connectedNodes.containsKey(destinationNode);
	}
	
	public Integer getWeight(UniDirectionalNode destinationNode) {
		if (isConnected(destinationNode))
			return connectedNodes.get(destinationNode);
		else 
			return -1; // no connection
	}
	
	public Map<UniDirectionalNode, Integer> getConnectedNodes() {
		return connectedNodes;
	}

	public void setConnectedNodes(Map<UniDirectionalNode, Integer> connectedNodes) {
		this.connectedNodes = connectedNodes;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		
		for (Map.Entry<UniDirectionalNode, Integer> entry : connectedNodes.entrySet()) {
			result.append("[").append(getId()).append(entry.getKey().getId()).append(entry.getValue()).append("]");
		}		
		
		return result.toString();
	}


}
