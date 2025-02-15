//Code by Jin Hao Fan
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphic implements ActionListener {
    //Global variables
    private int rows;
    private int columns;
    private int[][] gameBoard;
    private JButton[][] board;
    //Colors
    private final Color white = new Color(255, 255, 255);
    private final Color red = new Color(200, 30, 0);
    private final Color blue = new Color(0, 0, 220);
    private final Color brown = new Color(150, 75, 0);
    private final Color yellow = new Color(200, 200, 0);
    private final Color grey = new Color(128, 128, 128);

    public Graphic(int rows, int columns, int[][] gameBoard) {
        this.rows = rows;
        this.columns = columns;
        this.board = new JButton[rows][columns];
        this.gameBoard = gameBoard;
        //JFrame setup, first time doing this idk what they do
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000,1000);
        //Sets the grid layout of the frame to the number of indicated rows and columns
        frame.setLayout(new GridLayout(rows,columns));
        frame.setTitle("Game");

        //initializes the JButtons which will be the grids for the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                board[i][j] = new JButton("");
                board[i][j].setUI(new BasicButtonUI());
                if(gameBoard[i][j] == 0) {
                    board[i][j].setBackground(white);
                    board[i][j].addActionListener(this);
                }else if(gameBoard[i][j] == 1){
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                }else if(gameBoard[i][j] == 2) {
                    board[i][j].setBackground(blue);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blue));
                }else if(gameBoard[i][j] == 3) {
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else{
                    board[i][j].setBackground(white);
                }

            }
        }

        //adds the buttons to the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                frame.add(board[i][j]);
            }
        }

        frame.setVisible(true);
    }

    //Effects of what button presses do
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].getModel().isRollover()) {
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                    board[i][j].removeActionListener(this);
                }
            }
        }
    }
}