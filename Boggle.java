import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle
{
	static String[][] board;
	static long startTime,endTime; // for timing
	static final double MILLISEC_PER_SEC = 1000;

	static TreeSet<String> dict;
	static TreeSet<String> hits;

	// define your dictionary set and and your hits set UP HERE as TreeSets

	public static void main( String args[] ) throws Exception
	{
		startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );
		dict = loadDict(args[0]);
		hits = new TreeSet<String>();

		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, "" );

		for(String word : hits)	System.out.println(word);

		//System.out.println(hits.size());
		endTime =  System.currentTimeMillis(); // for timing
		System.out.println("GENERATION COMPLETED: runtime = " + (endTime-startTime)/MILLISEC_PER_SEC + " seconds");
	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{
		word += board[r][c];

		if(word.length() >= 3 && dict.contains(word)) hits.add(word);

		if(!hasSubstring(word)) return;

		// THIS IS THE FORM OF EACH OF YOUR N,NE,E,SE,S,SW,W,NW BLOCKS
		// NORTH IS [r-1][c]
		if ( r-1 >= 0 && board[r-1][c] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r-1, c, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// NE IS [r-1][c+1]  YOU WILL NEED TO TEST BOTH r-1 AND c+1 FOR OUT OF BOUNDS
		if ( r-1 >= 0 && c+1 < board.length && board[r-1][c+1] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r-1, c + 1, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// E IS [r][c+1]
		if ( c+1 < board.length && board[r][c+1] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r, c+1, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// SE IS ...
		if ( r+1 < board.length && c+1 < board.length && board[r+1][c+1] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r+1, c+1, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// S IS ...
		if ( r+1 < board.length && board[r+1][c] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r+1, c, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// SW IS ...
		if ( r+1 < board.length && c-1 >= 0 && board[r+1][c-1] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r+1, c-1, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// W IS ...
		if ( c-1 >= 0 && board[r][c-1] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r, c-1, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// NW IS ...
		if ( r-1 >= 0 && c-1 >= 0 && board[r-1][c-1] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r-1, c-1, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

	} // END DFS ----------------------------------------------------------------------------

	//=======================================================================================
	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD
	static TreeSet<String> loadDict( String fileName ) throws Exception
	{
		TreeSet<String> tempDict = new TreeSet<String>();
		Scanner file = new Scanner( new File(fileName));

		while(file.hasNext()) tempDict.add(file.next());

		return tempDict;
	}
	static boolean hasSubstring(String substring)
	{
		if(dict.ceiling(substring).contains(substring)) return true;
		return false;
	}
} // END BOGGLE CLASS
