public class PuzzleTree {

    private Puzzle root;
    private int w = 2;
    private int level = 0;
    private int maxLevel = 5;

    // --------------CONSTRUCTORS---------------
    PuzzleTree (){
        this.root = new Puzzle();
        this.root.puzzleGenerator();
        this.root.setParent(null);
    }

    PuzzleTree (Puzzle node){
        this.root = node;
    }

    // -----------------SETTERS-------------------
    public void setRoot (Puzzle node){ this.root = node; }
    public void setWidth (int w) { this.w = w; }

    // -----------------GETTERS-------------------
    public Puzzle getRoot () { return this.root; }
    public int getWidth () { return w; }

    // ---------CALCULATING METHODS---------------
    public void generateTree (){
        generateTreeHelper(this.root);

        root.generateNextMoves();

    }

    public void generateTreeHelper (Puzzle node){
        if (level == maxLevel)
            return;
        int [] heuristics = new int [4];
        int [] indeciesToExplore = new int [w];

        node.generateNextMoves();

        for (int i = 0; i < 4; i ++)
            heuristics [i] = node.getChild(i).getHeuristic();

        // To Be Continued

        level++;
    }

    // -----------------PRINT-------------------


    public void print (Puzzle node){
        if (node == null)
            return;
        System.out.print(node.toString());
        System.out.print( "Heuristic: " + node.getHeuristic() + "\n-------------------------\n");
        for (int i=0; i < 4; i ++) {
            print(node.getChild(i));
        }
    }

    // -----------------MAIN-------------------

    public static void main( String[] args)
    {
        PuzzleTree tree = new PuzzleTree();
        tree.generateTree();
        tree.print(tree.root);

    }

}
