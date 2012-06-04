import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//import java.lang.Object;

public class hw2 {
 
	/**
	 * @param args
	 */
	static double x1 = -999, y1 = -999, x2 = -999, y2 = -999, size = -999;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] temp = null;
		String line = null;
		boolean targetFound = false, noSolution1 = false, noSolution2 = false, noSolution3 = false;
		ArrayList<String> solutions = new ArrayList<String>(); // holds solutions
		ArrayList<Double> coords = new ArrayList<Double>(); // holds coords of 3 starting positions
		//for(int i = 0; i < 9; i++)
		try {



		    
		    FileInputStream fstream = new FileInputStream("input.txt");
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    
		    for(int a = 0; a < 3; a++) {
			    line = br.readLine();		    
			    size = Integer.parseInt(line); // reads size
			    line = br.readLine();
				temp = line.split(" ");
				x1 = Integer.parseInt(temp[0]); y1 = Integer.parseInt(temp[1]);	// reads coordinates of A		
				line = br.readLine();
				temp = line.split(" ");
				x2 = Integer.parseInt(temp[0]); y2 = Integer.parseInt(temp[1]);	// reads coordinates of B
				coords.add(x1);
				coords.add(y1);
			
				double max = (size*size*size*size) - (size*size);
				if(x1 == x2 && y1 == y2) initialIsTarget(); // if initial coordinates are stacked program returns
				else {		
						/********************** Euclidean calculations **********************/
						int count = 0;
						ArrayList<Node> tree1 = new ArrayList<Node>(); // euclidean distance
						tree1.add(new Node(x1, y1, x2, y2, 0, getEuclid(x1, y1, x2, y2), "", "")); // 1 input = euclidean method
						while(!targetFound) {
							Node n = tree1.get(0);
							double tx1, ty1, tx2, ty2; // temporary x and y values
							tx1 = n.getX1(); // list is ordered from lowest to highest, so pulls values from the first index
							ty1 = n.getY1();
							tx2 = n.getX2();
							ty2 = n.getY2();
							
							// move up
							if(tx1 == 0 || ty2 == size) {}
							else {
								tree1.add(new Node(tx1-1, ty1, tx2, ty2+1, n.getPresent(), getEuclid(tx1-1, ty1, tx2, ty2+1), n.getHistory(), "1"));
								if(tree1.get(tree1.size()-1).isTarget()) {
									System.out.println(tree1.get(tree1.size()-1).getHistory());
									//if(bestSolutions.get(0).equals("-1") || tree1.get(tree1.size()-1).getHistory().length() < bestSolutions.get(0).length())
									solutions.add(tree1.get(tree1.size()-1).getHistory());
									//System.out.println("1"); 
									break;
								}
							}
							
							// move down
							if(tx1 == size || ty2 == 0) {}
							else {
								tree1.add(new Node(tx1+1, ty1, tx2, ty2-1, n.getPresent(), getEuclid(tx1+1, ty1, tx2, ty2-1), n.getHistory(), "2"));
								if(tree1.get(tree1.size()-1).isTarget()) {
									System.out.println(tree1.get(tree1.size()-1).getHistory());
									solutions.add(tree1.get(tree1.size()-1).getHistory());
									//System.out.println("2"); 
									break;
								}
							}
							
							// move left
							if(ty1 == 0 || tx2 == 0) {}
							else {
								tree1.add(new Node(tx1, ty1-1, tx2-1, ty2, n.getPresent(), getEuclid(tx1, ty1-1, tx2-1, ty2), n.getHistory(), "3"));
								if(tree1.get(tree1.size()-1).isTarget()) {
									System.out.println(tree1.get(tree1.size()-1).getHistory());
									solutions.add(tree1.get(tree1.size()-1).getHistory());
									//System.out.println("3");
									break;
								}
							}
							
							// move right
							if(ty1 == size || tx2 == size) {}
							else {
								tree1.add(new Node(tx1, ty1+1, tx2+1, ty2, n.getPresent(), getEuclid(tx1, ty1+1, tx2+1, ty2), n.getHistory(), "4"));
								if(tree1.get(tree1.size()-1).isTarget()) {
									System.out.println(tree1.get(tree1.size()-1).getHistory());
									solutions.add(tree1.get(tree1.size()-1).getHistory());
									//System.out.println("4");
									break;
								}
							}
							
							count++;
							if(count == max) {
								System.out.println("No solution!");
								solutions.add(0+(3*a), "-");
								break;
							}
							
							tree1.remove(0); // pops the lowest valued node after expanding to its children
							Collections.sort(tree1);
						}
						
						/********************** Manhattan calculations **********************/
						count = 0;
						ArrayList<Node> tree2 = new ArrayList<Node>(); // manhattan distance
						tree2.add(new Node(x1, y1, x2, y2, 0, getManhat(x1, y1, x2, y2), "", "")); // 2 input = manhattan distance
						while(!targetFound) {
							Node n = tree2.get(0); 
							double tx1, ty1, tx2, ty2; // temporary x and y values
							tx1 = tree2.get(0).getX1(); // list is ordered from lowest to highest, so pulls values from the first index
							ty1 = tree2.get(0).getY1();
							tx2 = tree2.get(0).getX2();
							ty2 = tree2.get(0).getY2();
							
							// move up
							if(tx1 == 0 || ty2 == size) {}
							else {
								tree2.add(new Node(tx1-1, ty1, tx2, ty2+1, n.getPresent(), getManhat(tx1-1, ty1, tx2, ty2+1), n.getHistory(), "1"));
								if(tree2.get(tree2.size()-1).isTarget()) {
									System.out.println(tree2.get(tree2.size()-1).getHistory());
									//if(bestSolutions.get(1).equals("-1") || tree2.get(tree2.size()-1).getHistory().length() < bestSolutions.get(1).length())
									solutions.add(tree2.get(tree2.size()-1).getHistory());
									break;
								}
							}
							
							// move down
							if(tx1 == size || ty2 == 0) {}
							else {
								tree2.add(new Node(tx1+1, ty1, tx2, ty2-1, n.getPresent(), getManhat(tx1+1, ty1, tx2, ty2-1), n.getHistory(), "2"));
								if(tree2.get(tree2.size()-1).isTarget()) {
									System.out.println(tree2.get(tree2.size()-1).getHistory());
									solutions.add(tree2.get(tree2.size()-1).getHistory());
									break;
								}
							}
							
							// move left
							if(ty1 == 0 || tx2 == 0) {}
							else {
								tree2.add(new Node(tx1, ty1-1, tx2-1, ty2, n.getPresent(), getManhat(tx1, ty1-1, tx2-1, ty2), n.getHistory(), "3"));
								if(tree2.get(tree2.size()-1).isTarget()) {
									System.out.println(tree2.get(tree2.size()-1).getHistory());
									solutions.add(tree2.get(tree2.size()-1).getHistory());
									break;
								}
							}
							
							// move right
							if(ty1 == size || tx2 == size) {}
							else {
								tree2.add(new Node(tx1, ty1+1, tx2+1, ty2, n.getPresent(), getManhat(tx1, ty1+1, tx2+1, ty2), n.getHistory(), "4"));
								if(tree2.get(tree2.size()-1).isTarget()) {
									System.out.println(tree2.get(tree2.size()-1).getHistory());
									solutions.add(tree2.get(tree2.size()-1).getHistory());
									break;
								}
							}
							
							count++;
							if(count == max) {
								System.out.println("No solution!");
								solutions.add(1+(3*a), "-");
								break;
							}
							
							tree2.remove(0); // pops the lowest valued node after expanding to its children
							Collections.sort(tree2);
						}	
						
						/********************** Chess calculations **********************/
						count = 0;
						ArrayList<Node> tree3 = new ArrayList<Node>(); // chess distance
						tree3.add(new Node(x1, y1, x2, y2, 0, getChess(x1, y1, x2, y2), "", "")); // 3 input = chess distance
						while(!targetFound) {
							Node n = tree3.get(0);
							double tx1, ty1, tx2, ty2; // temporary x and y values
							tx1 = tree3.get(0).getX1(); // list is ordered from lowest to highest, so pulls values from the first index
							ty1 = tree3.get(0).getY1();
							tx2 = tree3.get(0).getX2();
							ty2 = tree3.get(0).getY2();
							
							// move up
							if(tx1 == 0 || ty2 == size) {}
							else {
								tree3.add(new Node(tx1-1, ty1, tx2, ty2+1, n.getPresent(), getChess(tx1-1, ty1, tx2, ty2+1), n.getHistory(), "1"));
								if(tree3.get(tree3.size()-1).isTarget()) {
									System.out.println(tree3.get(tree3.size()-1).getHistory());
									//if(bestSolutions.get(2).equals("-1") || tree3.get(tree3.size()-1).getHistory().length() < bestSolutions.get(2).length())
									solutions.add(tree3.get(tree3.size()-1).getHistory());
									break;
								}
							}
							
							// move down
							if(tx1 == size || ty2 == 0) {}
							else {
								tree3.add(new Node(tx1+1, ty1, tx2, ty2-1, n.getPresent(), getChess(tx1+1, ty1, tx2, ty2-1), n.getHistory(), "2"));
								if(tree3.get(tree3.size()-1).isTarget()) {
									System.out.println(tree3.get(tree3.size()-1).getHistory());
									solutions.add(2, tree3.get(tree3.size()-1).getHistory());
									break;
								}
							}
							
							// move left
							if(ty1 == 0 || tx2 == 0) {}
							else {
								tree3.add(new Node(tx1, ty1-1, tx2-1, ty2, n.getPresent(), getChess(tx1, ty1-1, tx2-1, ty2), n.getHistory(), "3"));
								if(tree3.get(tree3.size()-1).isTarget()) {
									System.out.println(tree3.get(tree3.size()-1).getHistory());
									solutions.add(tree3.get(tree3.size()-1).getHistory());
									break;
								}
							}
							
							// move right
							if(ty1 == size || tx2 == size) {}
							else {
								tree3.add(new Node(tx1, ty1+1, tx2+1, ty2, n.getPresent(), getChess(tx1, ty1+1, tx2+1, ty2), n.getHistory(), "4"));
								if(tree3.get(tree3.size()-1).isTarget()) {
									System.out.println(tree3.get(tree3.size()-1).getHistory());
									solutions.add(tree3.get(tree3.size()-1).getHistory());
									break;
								}
							}
							
							count++;
							if(count == max) {
								System.out.println("No solution!");
								solutions.add(2+(3*a), "-");
								break;
							}
							
							tree3.remove(0); // pops the lowest valued node after expanding to its children
							Collections.sort(tree3);
						}
				}
		    }
		    
		    // begins print out
		    
		    String one = "-", two = "-", three = "-", four = "-", five = "-", six = "-", seven = "-", eight =  "-", nine = "-"; // this is a copy of the history, automatically set to non-solution
		    ArrayList<String> numOfNodes = new ArrayList<String>();
		   
		    // loops through the String solutions, adding (the length of all the valid solutions) and (the non-solutions as "-") to a list to be printed as "Nodes Expanded"
		    for(int i = 0; i < solutions.size(); i++) { 
		    	if(solutions.get(i).equals("-")) numOfNodes.add("-");
		    	else numOfNodes.add(String.valueOf(solutions.get(i).length()+1));
		    }
		    
		    // just creates variables for easy reference
		    if(!solutions.get(0).equals("-")) one = solutions.get(0);
		    if(!solutions.get(1).equals("-")) two = solutions.get(1);
		    if(!solutions.get(2).equals("-")) three = solutions.get(2);
		    if(!solutions.get(3).equals("-")) four = solutions.get(3);
		    if(!solutions.get(4).equals("-")) five = solutions.get(4);
		    if(!solutions.get(5).equals("-")) six = solutions.get(5);
		    if(!solutions.get(6).equals("-")) seven = solutions.get(6);
		    if(!solutions.get(7).equals("-")) eight = solutions.get(7);
		    if(!solutions.get(8).equals("-")) nine = solutions.get(8);
		    String bestOne = "-", bestTwo = "-", bestThree = "-"; // assumes the best solutions are non-solutions
		    
		    // compares lengths of the history to find shortest solution
		    if(one.length() <= two.length() &&  one.length() <= three.length()) bestOne = one; 
		    else if(two.length() <= one.length() && two.length() <= three.length()) bestOne = two;
		    else if(three.length() <= one.length() && three.length() <= two.length()) bestOne = three;
		    
		    if(four.length() <= five.length() && four.length() <= six.length()) bestTwo = three;
		    if(five.length() <= four.length() && five.length() <= six.length()) bestTwo = four;
		    if(six.length() <= five.length() && six.length() <= four.length()) bestTwo = five;
		    
		    if(seven.length() <= eight.length() && seven.length() <= nine.length()) bestThree = seven;
		    if(eight.length() <= seven.length() && eight.length() <= nine.length()) bestThree = eight;
		    if(nine.length() <= eight.length() && nine.length() <= seven.length()) bestThree = nine;	    
		    
		    BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
			out.write("\t\t\t\tNodes Expanded"); out.newLine();
			out.write("\t Euclidean\t Manhattan\t Chessboard"); out.newLine();
		    out.write("Input 1: \t" + numOfNodes.get(0) + "\t\t " + numOfNodes.get(1) + " \t\t " + numOfNodes.get(2)); out.newLine();
		    out.write("Input 2: \t" + numOfNodes.get(3) + "\t\t " + numOfNodes.get(4) + " \t\t " + numOfNodes.get(5)); out.newLine();
		    out.write("Input 3: \t" + numOfNodes.get(6) + "\t\t " + numOfNodes.get(7) + " \t\t " + numOfNodes.get(8)); out.newLine(); out.newLine();
		    out.write("Optimal Path Solution: "); out.newLine();
		    out.write("Input 1: " + printOutput(bestOne, out, 1, coords)); out.newLine();
		    out.write("Input 2: " + printOutput(bestTwo, out, 2, coords)); out.newLine();
		    out.write("Input 3: " + printOutput(bestThree, out, 3, coords)); out.newLine();
		    
			in.close();
			out.close();
		} catch(IOException e) { // reading in and writing out
			e.printStackTrace();
		}
	}
	
	public static double getEuclid(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	
	public static double getManhat(double x1, double y1, double x2, double y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
	public static double getChess(double x1, double y1, double x2, double y2) {
		return Math.max(Math.abs(x1-x2), Math.abs(y1-y2));
	}
	
	public static void initialIsTarget() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
			out.write("1");
			out.newLine();
			out.write(x1 + " " + y1);
			out.newLine();
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printList(List<Node> l) {
		for(int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}
	}
	
	public static String printOutput(String s, BufferedWriter o, int z, ArrayList<Double> coords) { // converts history to coordinates
		double xtemp = -999, ytemp = -999;
		switch(z) {
			case 1: xtemp = coords.get(0); ytemp = coords.get(1); break;
			case 2: xtemp = coords.get(2); ytemp = coords.get(3); break;
			case 3: xtemp = coords.get(4); ytemp = coords.get(5);break;
			default: break;
		}
		
		String histCoords = "";
		System.out.println(xtemp +  " " + ytemp);
		System.out.println(coords.toString());
		if(!s.equals("-")) {
			for(int i = 0; i < s.length(); i++) {
				switch (s.charAt(i)) {
					case '1': xtemp--; histCoords = histCoords + "(" + (int)xtemp + "," + (int)ytemp + ") "; break;
					case '2': xtemp++; histCoords = histCoords + "(" + (int)xtemp + "," + (int)ytemp + ") "; break;
					case '3': ytemp--; histCoords = histCoords + "(" + (int)xtemp + "," + (int)ytemp + ") "; break;
					case '4': ytemp++; histCoords = histCoords + "(" + (int)xtemp + "," + (int)ytemp + ") "; break;
					default: break;			
				}
			}
		}
		else histCoords = "-1";
		return histCoords;
	}

}
