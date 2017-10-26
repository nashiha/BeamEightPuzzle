import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Shiha on 10/25/2017.
 */
public class PuzzleGUI extends JPanel{
    public Puzzle[] puzzleList = new Puzzle[10];
    public ArrayList<Puzzle> stepsToGoal = new ArrayList<Puzzle>();
    public int curr = 0;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton nextButton;
    public JPanel gamePanel;

    public PuzzleGUI() {
        nextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                updateMatrix();
                curr = curr + 1;
            }
        });
    }
    public void updateMatrix()
    {
        if(curr != getStepsToGoal().size()) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp.add(getStepsToGoal().get(curr).getPuzzleArr()[i][j]);
                }
            }
            for (int i = 0; i < 9; i++) {
                if (temp.get(i) != -1) {
                    (getButtons().get(i)).setText("" + temp.get(i));
                } else {
                    getButtons().get(i).setText(" ");
                }
            }

        }

//        int[][] puzzleList;
//        ArrayList<Integer> temp = new ArrayList<Integer>();
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    temp.add((this.puzzleList[2]).getPuzzleArr()[i][j]);
//                }
//            }
//            for( int i = 0; i < 9; i++)
//            {
//                if( temp.get(i) != -1) {
//                    (getButtons().get(i)).setText("" + temp.get(i));
//                }
//                else
//                {
//                    getButtons().get(i).setText(" ");
//                }
//            }
    }
    public void setStepsToGoal( ArrayList<Puzzle> stepsToGoal)
    {
        this.stepsToGoal = stepsToGoal;
    }
    public ArrayList<Puzzle> getStepsToGoal()
    {
        return this.stepsToGoal;
    }
    public ArrayList<JButton> getButtons()
    {
        ArrayList<JButton> buttonList= new ArrayList<JButton>();
        buttonList.add( a1Button);
        buttonList.add( a2Button);
        buttonList.add( a3Button);
        buttonList.add( a4Button);
        buttonList.add( a5Button);
        buttonList.add( a6Button);
        buttonList.add( a7Button);
        buttonList.add( a8Button);
        buttonList.add( a9Button);
        return buttonList;
    }
    public JButton getNextButton()
    {
        return this.nextButton;
    }
    public Puzzle[] getPuzzleList()
    {
        return this.puzzleList;
    }
    public void setPuzzleList( Puzzle[] puzzleList)
    {
        this.puzzleList = puzzleList;
    }
    public static void main(String[] args)
    {
        Puzzle goalPuzzle;
        goalPuzzle = new Puzzle();
        goalPuzzle.makeGoal();
        Puzzle[] puzzleList = new Puzzle[10];
        ArrayList<Puzzle> stepstoGoal2 = new ArrayList<Puzzle>();
        System.out.println("Goal state:\n" + goalPuzzle.toString());
        for (int i = 0; i < 10; i++) {
            puzzleList[i] = new Puzzle();
            if( i == 2)
            {
                //stepstoGoal2 = puzzleList[i].puzzleGenerator();
            }
            else
            {
                puzzleList[i].puzzleGenerator();
            }
            System.out.println("Puzzle " + (i + 1) + ":\n" + puzzleList[i].toString());
        }
        JFrame frame;
        frame = new JFrame( "8 Puzzle Game");
        PuzzleGUI game = new PuzzleGUI();
        game.setPuzzleList( puzzleList);
        game.setStepsToGoal( stepstoGoal2);
        frame.setContentPane(game.gamePanel);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible( true);
    }
}
