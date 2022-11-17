package application;
import java.awt.Point;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import searches.BFS;
import searches.DFS;
import searches.Greedy;
import searches.Magic;
import searches.RandomWalk;
import searches.SearchAlgorithm;
import searches.SearchFactory;

public class MazeController {
	
	
	private MazeDisplay mazeDisplay;
	// The maze to search
	private Maze maze;
	
	public MazeController(int numRows, int numColumns, MazeDisplay myMazeDisplay) {
		start = new Point(1,1);
		goal = new Point(numRows-2, numColumns-2);
		maze = new Maze(numRows, numColumns);
		mazeDisplay = myMazeDisplay;

	}
	/* 
	 * Logic of the program
	 */
	// The search algorithms
	//private Greedy greedy;				
	//private BFS bfs;
	//private DFS dfs;
	//private RandomWalk rand;
	//private Magic magic;
	private SearchAlgorithm search ;		// This string tells which algorithm is currently chosen.  Anything other than 
	// the implemented search class names will result in no search happening.

	// Where to start and stop the search
	private Point start;
	private Point goal;
	
	//Make a SearchFactory instance variable in the Controller and initialize it.
	private SearchFactory searchFactory;


	
	
	public void startSearch(String searchType) {
		maze.reColorMaze();
		SearchFactory factory = new SearchFactory();
		search = factory.makeSearch(searchType, maze, start, goal);
		// Restart the search.  Since I don't know 
		// which one, I'll restart all of them.
		/*
		BFS bfs = new BFS(maze, start, goal);	// start in upper left and end in lower right corner
		DFS dfs = new DFS(maze, start, goal);
		//Greedy greedy = new Greedy(maze, start, goal);
		RandomWalk rand = new RandomWalk(maze, start, goal);
		//magic = new Magic(maze, start, goal);
		
		if(searchType.equals("DFS")) search = dfs;
		else if (searchType.equals("BFS")) search = bfs;
		//else if (search.equals("Greedy")) search = greedy;
		else if (searchType.equals("RandomWalk")) search = rand;
		//else if (search.equals("Magic")) magic.step();
		*/
	}
	

	
	
	/*
	 * Does a step in the search regardless of pause status
	 */
	public void doOneStep(double elapsedTime){
		if (search != null) {
			search.step();
		}
		mazeDisplay.redraw();
	}
	
	
	
	public int getCellState(Point position) {
		return maze.get(position);
	}
	/*
	 * Re-create the maze from scratch.
	 * When this happens, we should also stop the search.
	 */
	public void newMaze() {
		maze.createMaze(maze.getNumRows(),maze.getNumCols());
		search = null;
		mazeDisplay.redraw();
	}

	
}
