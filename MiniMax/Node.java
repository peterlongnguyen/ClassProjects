import java.util.*;

public class Node {
	public String name = null, maxOrMin = null;
	public Integer value = null;
	public Float beta = Float.POSITIVE_INFINITY, alpha = Float.NEGATIVE_INFINITY;
	public Boolean isMax = null, isSkipped = false, isChecked = false, isRoot = false;
	public ArrayList<Node> children = new ArrayList<Node>();
	public Node parent = null;
	
	public Node() {}
	
	public Node(boolean m, String n) {
		isMax = m;
		name = n;
		if(isMax) maxOrMin = "Max";
		else maxOrMin = "Min";
	}
	
	public Node(int v, boolean m, String n) {
		value = v;
		isMax = m;
		name = n;
	}
	
	// setters
	
	public void setValue(int v) {
		value = v;
	}
	
	public void setIsMax(boolean b) {
		isMax = b;
	}
	
	public void setBeta(float s) {
		beta = s;
	}
	
	public void setAlpha(float s) {
		alpha = s;
	}
	
	public void setParent(Node p) {
		parent = p;
	}
	
	// getters 
	
	public Node getParent() {
		return parent;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isMax() {
		return isMax;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
	
	// add children
	
	public void addChildren(Node n) {
		n.setParent(this);
		children.add(n);
	}
	
	// if has children
	
	public boolean hasChildren() {
		if(children.size() > 0) return true;
		return false;
	}
	
	public boolean childrenAreChecked() {
		for(int i = 0; i < children.size(); i++) {
			if(!children.get(i).isChecked) return false;
		}
		return true;
	}
	
	public void skipRest(String s) {
		for(int i = 0; i < children.size(); i++) {
			if(!children.get(i).isChecked) {
				if(s.equals("a")) System.out.println("Skipping expansion of Node " + children.get(i).name + " because it cannot make beta lower.");
				if(s.equals("b")) System.out.println("Skipping expansion of Node " + children.get(i).name + " because it cannot make alpha higher.");
				children.get(i).isChecked = true;
				children.get(i).isSkipped = true;
			}
		}
	}

}
