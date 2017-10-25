import java.util.*;

import static java.lang.Math.abs;

/**
 * Created by Shiha on 10/24/2017.
 */
public class Puzzle
{
    private int x;
    private int y;
    private int[][] puzzle;

    private Puzzle parent;
    private Puzzle[] children;

    // --------------CONSTRUCTORS---------------
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
        this.x = -1;
        this.y = -1;
        //this.childNum = 0;
        initializeChildren();
    }
    Puzzle(int [][] array, int x, int y){
        this.puzzle = array;
        this.x = x;
        this.y = y;
        this.parent = null;
        //this.childNum = 0;
        initializeChildren();
    }
    Puzzle(int [][] array, int x, int y, Puzzle parent){
        this.puzzle = array;
        this.x = x;
        this.y = y;
        this.parent = parent;
        //this.childNum = 0;
        initializeChildren();
    }

    // -----------------SETTERS-------------------
    public void setPuzzleArr( int[][] newPuzzle)
    {
        this.puzzle = newPuzzle;
    }
    public void setTile( int i, int j, int t)
    {
        this.puzzle[i][j] = t;
    }
    public void setParent (Puzzle parent){ this.parent = parent; }
   // public void setWidth (int w) { this.w = w; }
    public void addChild (Puzzle child){
        if (getChildNum() < 4) {
            int i = 0;
            while (this.children[i] != null) i++;

            this.children[i] = child;
            this.children[i].setParent(this);
        }
    }
    public void initializeChildren (){
        this.children = new Puzzle[4];
        for (int i = 0; i < 4; i ++){
            this.children[i] = null;
        }
    }
    // -----------------GETTERS-------------------
    public int[][] getPuzzleArr()
    {
        return this.puzzle;
    }
    public int getTile (int i, int j)
    {
        return this.puzzle[i][j];
    }
    public Puzzle getParent () { return this.parent; }
    //public int getWidth () { return this.w; }
    public Puzzle[] getChildren() { return this.children;}
    public Puzzle getChild (int i) {
        if ( i < getChildNum() )
            return this.children[i];

        return null;
    }
    public int getChildNum (){
        if (this.children==null)
            return 0;
        int i = 0;
        while (i < 4 && this.children[i] != null) i++;
        return i;
    }
    public int getX (){ return this.x; }
    public int getY (){ return this.y; }
    // ---------CALCULATING METHODS---------------
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
        this.x = 2;
        this.y = 2;
        return true;
        //Puzzle looks like this:
        // [1][2][3]
        // [4][5][6]
        // [7][8][-1]
    }

    public void puzzleGenerator()
    {
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
                lastMove = randomInt;
                //System.out.println( "i:" + i + "\n" + this.toString());
            }
        }
        else
        {
            System.out.println( "Puzzle does not start from goal state. Puzzle cannot be guaranteed to be solved if" +
                    " created.");
        }
    }

    public int getHeuristic (){
        if (this == null){
            return 1000;
        }
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

    public void generateNextMoves (){
        //Puzzle node = this;
        Puzzle up = new Puzzle();
        up.copy(this);
        if (up.goUp()){
            this.addChild(up);
            System.out.println( "\nChild Up Created\n");
        }

        Puzzle down = new Puzzle();
        down.copy(this);
        if (down.goDown()){
            this.addChild(down);
            System.out.println( "\nChild Down Created\n");
        }

        Puzzle left = new Puzzle();
        left.copy(this);
        if (left.goLeft()){
            this.addChild(left);
            System.out.println( "\nChild Left Created\n");
        }
        Puzzle right = new Puzzle();
        right.copy(this);
        if (right.goRight()){
            this.addChild(right);
            System.out.println( "\nChild Right Created\n");
        }


        /*Puzzle up = this;
        up.goUp();
        this.addChild(up);

        Puzzle down = this;
        down.goDown();
        this.addChild(down);

        Puzzle left = this;
        left.goLeft();
        this.addChild(left);

        Puzzle right = this;
        right.goRight();
        this.addChild(right);
        */
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
    // -----------------MOVES-------------------
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

    // -----------------PRINT-------------------
    public String toString()
    {
        if (this == null)
            return "";
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
    // -----------------MAIN-------------------
    public static void main( String[] args)
    {
        Puzzle goalPuzzle;
        goalPuzzle = new Puzzle();
        goalPuzzle.makeGoal();

        System.out.println( "Goal state:\n" + goalPuzzle.toString());
        Puzzle[] puzzleList = new Puzzle[10];
        for( int i = 0; i < 10; i++)
        {
            puzzleList[i] = new Puzzle();
            puzzleList[i].puzzleGenerator();
            System.out.println( "Puzzle " + (i + 1) + ":\n" + puzzleList[i].toString());
        }

        ////////////////////////////

        System.out.println( "Now work with individual puzzle:\n");

        Puzzle p = new Puzzle();
        p.copy(puzzleList[9]);
        Puzzle t = new Puzzle();
        t.copy(p);
        p.generateNextMoves();

        Puzzle[] children = p.getChildren();

        System.out.println( "Puzzle " + ":\n" + p.toString());

        for ( int i = 0; i < p.getChildNum(); i++ )
            System.out.println( "Child " + (i + 1) + ":\n" + children[i].toString());


    }
}