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
    public static int rows = 10;
    public static int columns = 10;
    public static JButton[][] board = new JButton[rows][columns];
    //Colors
    public static Color white = new Color(255, 255, 255);
    public static Color red = new Color(255, 0, 0);
    public static Color blue = new Color(0, 0, 255);

    public Graphic() {
        //JFrame setup, first time doing this idk what they do
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        //Sets the grid layout of the frame to the number of indicated rows and columns
        frame.setLayout(new GridLayout(rows,columns));
        frame.setTitle("Game");

        //initializes the JButtons which will be the grids for the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new JButton("");
                board[i][j].setBackground(white);
                board[i][j].setUI(new BasicButtonUI());
                board[i][j].addActionListener(this);
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

    public static void main(String[] args) {
        new Graphic();
    }

    //Effects of what button presses do
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].getModel().isRollover()) {
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                }
            }
        }
    }
}