package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import application.Maze;

public class DFS extends SearchAlgorithm{
	
	
	public DFS(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint,goalPoint);
		// The data structure for DFS is a stack.
		Stack<Point> stack =new Stack<>();
		stack.push(startPoint);
		data = stack;
	}
	
	/*
	 * Depth-First Search Algorithm.
	 */
	public boolean step(){
		// Don't keep computing after goal is reached or determined impossible.
		if(searchOver){
			return searchResult;
		}
		// Find possible next steps
		Collection<Point> neighbors = getNeighbors();
		// Choose one to be a part of the path
		Point next = chooseNeighbor(neighbors);
		// mark the next step
		if(next!=null){
			maze.markPath(next);
			recordLink(next);
		}
		// if no next step is found
		else{	
			maze.markVisited(current);
			Stack<Point> stack = (Stack<Point>)data;
			stack.pop();
		}
		resetCurrent();
		checkSearchOver();
		return searchResult;	
	}
	
	@Override 
	protected void whenNextNull(){
		super.whenNextNull();
		Stack<Point> stack = (Stack<Point>)data;
		stack.pop();
	}
	
	/*
	 * This method defines which "neighbors" or next points can be reached in the maze from
	 * the current one.  
	 * 
	 * In this method, the neighbors are defined as the four squares immediately to the north, south,
	 * east, and west of the current point, but only if they are in the bounds of the maze.  It does 
	 * NOT check to see if the point is a wall, or visited.  
	 * 
	 * Any other definition of "neighbor" indicates the search subclass should override this method.
	 */
	
	/*
	 * This method defines the neighbor that gets chosen as the newest "fringe" member
	 * 
	 * It chooses the first point it finds that is empty.
	 */
	public Point chooseNeighbor(Collection<Point> neighbors){
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				return p;
			}
		}
		return null;
	}
	
	// When a new node is chosen, push it on the stack
	@Override
	public void recordLink(Point next){
		Stack<Point> stack = (Stack<Point>)data;
		// FIXME: add try/catch for ClassCastException
		stack.push(next);
	}

	
	
	
	/*
	 * Get the next fringe point to consider.
	 * 
	 * This implementation resets the "current" instance variable 
	 * to be the next one on the fringe data structure.
	 */
	@Override
	protected void resetCurrent(){
		Stack<Point> stack = (Stack<Point>)data;
		current = stack.peek();
	}
	
	
	
	
	/*
	 * Search is over and unsuccessful if there are no more fringe points to consider.
	 * Search is over and successful if the current point is the same as the goal.
	 */
	
		
	
}
