import java.util.*;

public class hw3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node a1 = new Node(true, "A1"); a1.isRoot = true;
		
		Node b1 = new Node(false, "B1");
		Node b2 = new Node(false, "B2");
		
		Node c1 = new Node(true, "C1");
		Node c2 = new Node(true, "C2");
		Node c3 = new Node(true, "C3");
		Node c4 = new Node(true, "C4");
		Node c5 = new Node(true, "C5");
		
		Node d1 = new Node(false, "D1");
		Node d2 = new Node(false, "D2");
		Node d3 = new Node(false, "D3");
		Node d4 = new Node(false, "D4");
		Node d5 = new Node(false, "D5");
		Node d6 = new Node(false, "D6");
		Node d7 = new Node(false, "D7");
		Node d8 = new Node(false, "D8");
		
		Node e1 = new Node(0, true, "E1");
		Node e2 = new Node(-2, true, "E2");
		Node e3 = new Node(4, true, "E3");
		Node e4 = new Node(8, true, "E4");
		Node e5 = new Node(6, true, "E5");
		Node e6 = new Node(1, true, "E6");
		Node e7 = new Node(3, true, "E7");
		Node e8 = new Node(-1, true, "E8");
		Node e9 = new Node(0, true, "E9");
		Node e10 = new Node(2, true, "E10");
		Node e11 = new Node(5, true, "E11");
		Node e12 = new Node(4, true, "E12");
		Node e13 = new Node(-2, true, "E13");
		Node e14 = new Node(3, true, "E14");
		
		a1.addChildren(b1); a1.addChildren(b2);
		
		b1.addChildren(c1); b1.addChildren(c2); b1.addChildren(c3);
		c1.addChildren(d1); c1.addChildren(d2);
		c2.addChildren(d3);
		c3.addChildren(d4); c3.addChildren(d5);
		d1.addChildren(e1); d1.addChildren(e2);
		d2.addChildren(e3); d2.addChildren(e4);
		d3.addChildren(e5); d3.addChildren(e6);
		d4.addChildren(e7);
		d5.addChildren(e8); d5.addChildren(e9);
		
		b2.addChildren(c4); b2.addChildren(c5);
		c4.addChildren(d6);
		c5.addChildren(d7); c5.addChildren(d8);
		
		d6.addChildren(e10); d6.addChildren(e11);
		d7.addChildren(e12);
		d8.addChildren(e13); d8.addChildren(e14);
		
		miniMax(a1);
		System.out.println(d2.alpha + " <= " + d2.beta);
	}
	
	public static void miniMax(Node n) { // turn on isChecked on the way up to the parent node
		Float pInfinity = new Float(Float.POSITIVE_INFINITY), nInfinity = new Float(Float.NEGATIVE_INFINITY);
		//System.out.println("Currently looking at node: " + n.name);
		//if(n.hasChildren() && n.childrenAreChecked()) System.out.println(n.name + ": " + n.alpha + " <= " + n.beta + " -- Node Value: " + n.value);
		if(n.hasChildren() && !n.childrenAreChecked()) { 
			for(int i = 0; i < n.getChildren().size(); i++) {
				if(!n.getChildren().get(i).isChecked) miniMax(n.getChildren().get(i));
			}
		}
		else {
			if(!n.isRoot) {
				//if(!n.isRoot) System.out.println("Expanding " + n.parent.maxOrMin + " " + n.parent.name + ": " + n.parent.alpha + " <= " + n.parent.beta);
				n.isChecked = true;
				if(n.parent.getChildren().get(0) != n) { 
					n.alpha = n.parent.alpha;
					n.beta = n.parent.beta;
				}

				
				// if parent has no node value, assigns current node value to parent's
				if(n.parent.value == null) {
					n.parent.value = n.value; // if first child, automatically sets parent's value and alpha/beta
					if(n.parent.isMax()) n.parent.alpha = (float)n.value; // passes up current value into parent's alpha
					else if(!n.parent.isMax()) n.parent.beta = (float)n.value; // passes up current value into parent's beta

				}
				
				if(n.parent.isMax()) { // if parent is max node
					//System.out.println("1. " + n.name + ": " + n.parent.alpha + " <= " + n.parent.beta);
					if(n.parent.value < n.value) { // if parent's value is less than current, passes up current value
						n.parent.alpha = (float)n.value; // passes up current value into parent's alpha
						n.parent.value = n.value;
					}
					//System.out.println("2. " + n.parent.name + ": " + n.parent.alpha + " <= " + n.parent.beta);
					if(n.parent.alpha >= n.parent.beta) {
						System.out.println("a skip is true");
						n.parent.skipRest("a"); 
					}
				}
				
				else if(!n.parent.isMax()) { // if parent is min node
					//System.out.println("1. " + n.name + ": " + n.parent.alpha + " <= " + n.parent.beta);
					if(n.parent.value > n.value) {
						n.parent.beta = (float)n.value; // passes up current value into parent's beta
						n.parent.value = n.value; 
					}
					//System.out.println("2. " + n.name + ": " + n.parent.alpha + " <= " + n.parent.beta);
					if(n.parent.alpha >= n.parent.beta) {
						System.out.println("b skip is true");
						n.parent.skipRest("b"); 
					}
				}
				
				//System.out.println(n.parent.name + ": " + n.parent.alpha + " <= " + n.parent.beta + " -- Node Value: " + n.parent.value);

				
				// prints out the alpha beta values of the current parent being explored
				//if(!n.isRoot) System.out.println("Expanding " + n.parent.maxOrMin + " " + n.parent.name + ": " + n.parent.alpha + " <= " + n.parent.beta);
				if(n.hasChildren()) System.out.println(n.name + ": " + n.alpha + " <= " + n.beta + " -- Node Value: " + n.value);
				miniMax(n.parent);
			}
		}
	}
}
