import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.abs;

/**
 * Created by Shiha on 10/24/2017.
 */
public class Puzzle
{
    int x = 2;
    int y = 2;
    private int[][] puzzle;
    private ArrayList<Puzzle> stepsToGoal;
    Puzzle()
    {
        this.puzzle = new int[3][3];
        for( int i = 0; i < 3; i++)
        {
            for( int j = 0; j < 3; j++)
            {
                this.puzzle[i][j] = -1;
            }
        }
        stepsToGoal = new ArrayList<Puzzle>();
    }
    Puzzle( int[][] newpuzzle)
    {
        this.puzzle = newpuzzle;
    }
    public boolean makeGoal()
    {
        //Create Goal Puzzle
        this.puzzle[0][0] = 1;
        this.puzzle[0][1] = 2;
        this.puzzle[0][2] = 3;
        this.puzzle[1][0] = 4;
        this.puzzle[1][1] = 5;
        this.puzzle[1][2] = 6;
        this.puzzle[2][0] = 7;
        this.puzzle[2][1] = 8;
        this.puzzle[2][2] = -1; //-1 indicates empty box
        return true;
        //Puzzle looks like this:
        // [1][2][3]
        // [4][5][6]
        // [7][8][-1]
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
    public ArrayList<Puzzle> puzzleGenerator()
    {
        //Puzzle myPuzzle = new Puzzle();
        if( this.makeGoal()) {
            Random randomGenerator = new Random();
            int lastMove = -1;
            for (int i = 0; i < 50; i++) {
                int randomInt = randomGenerator.nextInt(4);
                int temp = 0;
                //0 means up, 1 means down, 2 means right, 3 means left
                if (randomInt == 0) //go up
                {
                    if (lastMove == 1) {
                        i--;
                    } else if (!(this.goUp())) {
                        i--;
                    }
                } else if (randomInt == 1) //go down
                {
                    if (lastMove == 0) {
                        i--;
                    } else if (!this.goDown()) {
                        i--;
                    }
                } else if (randomInt == 2)  //go right
                {
                    if (lastMove == 3) {
                        i--;
                    } else if (!this.goRight()) {
                        i--;
                    }
                } else if (randomInt == 3) //go left
                {
                    if (lastMove == 2) {
                        i--;
                    } else if (!this.goLeft()) {
                        i--;
                    }
                } else {
                    System.out.println("It can't go there!");
                }
                if( (lastMove + randomInt) != 1 && (lastMove + randomInt) != 5)
                {
                    Puzzle newPuzzle = new Puzzle();
                    newPuzzle.copy( this);
                    stepsToGoal.add(newPuzzle);
                }
                lastMove = randomInt;
                //System.out.println( "i:" + i + "\n" + stepsToGoal.get(i).toString());
            }
        }
        else
        {
            System.out.println( "Puzzle does not start from goal state. Puzzle cannot be guaranteed to be solved if" +
                    " created.");
        }
        return stepsToGoal;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public void copy (Puzzle p) {
        this.x = p.getX();
        this.y = p.getY();
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j ++)
                this.puzzle[i][j] = p.getTile(i,j);
        }
        //this.children = p.getChildren();
        //this.parent = p.getParent();
    }
    public int getTile (int i, int j)
    {
        return this.puzzle[i][j];
    }
    public void setTile( int i, int j, int t)
    {
        this.puzzle[i][j] = t;
    }

    public int getHeuristic (){
        int n = 0;

        for ( int i = 0; i < 3; i ++)
            for (int j = 0; j < 3; j ++)
                n += getTileDistance(i,j);

        return n;
    }

    public int getTileDistance (int i, int j){
        int tile = this.puzzle[i][j];

        if (tile == -1)
            return 0;

        int rowDifference = abs (i-(tile-1)/3);
        int columnDifference = abs (j-(tile-1)%3);

        return rowDifference+columnDifference;
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
        ArrayList<Puzzle> list = new ArrayList<Puzzle>();
        goalPuzzle = new Puzzle();
        goalPuzzle.makeGoal();

        System.out.println( "Goal state:\n" + goalPuzzle.toString());
        Puzzle[] puzzleList = new Puzzle[10];
        for( int i = 0; i < 10; i++)
        {
            if( i == 2)
            {
                puzzleList[i] = new Puzzle();
                list = puzzleList[i].puzzleGenerator();
            }
            else
            {
                puzzleList[i] = new Puzzle();
                puzzleList[i].puzzleGenerator();
            }
            //System.out.println( "Puzzle " + (i + 1) + ":\n" + puzzleList[i].toString());
        }
        for( int i = 0; i < list.size(); i++)
        {
            System.out.println( i + ":\n" + list.get(i).toString());
        }

    }
}