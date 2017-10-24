import java.util.*;

/**
 * Created by Shiha on 10/24/2017.
 */
public class Puzzle
{
    int x = 2;
    int y = 2;
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
    public boolean goUp()
    {
        if( y == 0)
        {
            return false;
        }
        int temp = this.puzzle[y - 1][x];
        this.puzzle[y - 1][x] = -1;
        this.puzzle[y][x] = temp;
        y = y - 1;
        return true;
    }
    public boolean goDown()
    {
        if( y == 2)
        {
            return false;
        }
        int temp = this.puzzle[y + 1][x];
        this.puzzle[y + 1][x] = -1;
        this.puzzle[y][x] = temp;
        y = y + 1;
        return true;
    }
    public boolean goRight()
    {
        if( x == 2)
        {
            return false;
        }
        int temp = this.puzzle[y][x + 1];
        this.puzzle[y][x + 1] = -1;
        this.puzzle[y][x] = temp;
        x = x + 1;
        return true;
    }
    public boolean goLeft()
    {
        if( x == 0)
        {
            return false;
        }
        int temp = this.puzzle[y][x - 1];
        this.puzzle[y][x - 1] = -1;
        this.puzzle[y][x] = temp;
        x = x - 1;
        return true;
    }
    public void puzzleGenerator()
    {
        Random randomGenerator = new Random();
        int lastMove = -1;
        for( int i = 0; i < 50; i++)
        {
            int randomInt = randomGenerator.nextInt(4);
            int temp = 0;
            //0 means up, 1 means down, 2 means right, 3 means left
            if( randomInt == 0) //go up
            {
                if( lastMove == 1 )
                {
                    i--;
                }
                else if ( !(this.goUp()))
                {
                    i--;
                }
            }
            else if( randomInt == 1) //go down
            {
                if( lastMove == 0 )
                {
                    i--;
                }
                else if ( !this.goDown())
                {
                    i--;
                }
            }
            else if( randomInt == 2)  //go right
            {
                if( lastMove == 3 )
                {
                    i--;
                }
                else if( !this.goRight())
                {
                    i--;
                }
            }
            else if( randomInt == 3) //go left
            {
                if( lastMove == 2 )
                {
                    i--;
                }
                else if( !this.goLeft())
                {
                    i--;
                }
            }
            else
            {
                System.out.println( "It can't go there!");
            }
            lastMove = randomInt;
            //System.out.println( "i:" + i + "\n" + this.toString());
        }
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
        Puzzle goalPuzzle;
        goalPuzzle = new Puzzle();
        System.out.println( "Goal state:\n" + goalPuzzle.toString());
        Puzzle[] puzzleList = new Puzzle[10];
        for( int i = 0; i < 10; i++)
        {
            puzzleList[i] = new Puzzle();
            puzzleList[i].puzzleGenerator();
            System.out.println( "Puzzle " + (i + 1) + ":\n" + puzzleList[i].toString());
        }

    }
}