import java.util.*;

/**
 * Created by Shiha on 10/24/2017.
 */
public class Puzzle
{
    private int[][] puzzle;
    Puzzle()
    {
        this.puzzle = new int[3][3];
        //Create Goal Puzzle
        puzzle[0][0] = 1;
        puzzle[0][1] = 2;
        puzzle[0][2] = 3;
        puzzle[1][0] = 4;
        puzzle[1][1] = 5;
        puzzle[1][2] = 6;
        puzzle[2][0] = 7;
        puzzle[2][1] = 8;
        puzzle[2][2] = -1; //-1 indicates empty box
        //Puzzle looks like this:
        // [1][2][3]
        // [4][5][6]
        // [7][8][-1]
    }
    Puzzle( int[][] newpuzzle)
    {
        this.puzzle = newpuzzle;
    }

    public int[][] getPuzzleArr()
    {
        return this.puzzle;
    }
    public void setPuzzleArr( int[][] newPuzzle)
    {
        this.puzzle = newPuzzle;
    }

    public void puzzleGenerator()
    {
        int[][] puzzleInstance = new int[3][3];
        for( int i = 0; i < 3; i++)
        {
            for( int j = 0; j < 3; j++)
            {
                puzzleInstance[i][j] = 0;
            }
        }
        this.setPuzzleArr( puzzleInstance);
    }

    public String toString()
    {
        String s;
        s = "";
        for (int i = 0; i < 3; i++)
        {
            for( int j = 0; j < 3; j++)
            {
                s = s +  "[" + this.puzzle[i][j] + "]" ;
            }
            s = s + "\n";
        }
        //s = s + "\n";
        return s;
    }
    //for testing purposes
    //will provide listings of program
    public static void main( String[] args)
    {
        Puzzle puzzle;
        puzzle = new Puzzle();
        System.out.println( puzzle.toString());
        puzzle.puzzleGenerator();
        System.out.println( puzzle.toString());
    }
}
