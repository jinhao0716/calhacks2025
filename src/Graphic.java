//Code by Jin Hao Fan
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphic implements ActionListener {
    //Global variables
    private int rows;
    private int columns;
    private int[][] gameBoard;
    private JButton[][] board;
    private JFrame frame = new JFrame();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel turn = new JLabel("Red's Turn.", SwingConstants.CENTER);
    private JLabel steps = new JLabel("Steps: " + Game.stepsTaken, SwingConstants.CENTER);
    //Colors
    private final Color white = new Color(255, 255, 255);
    private final Color red = new Color(170, 30, 0);
    private final Color redGoal = new Color(255, 30, 0);
    private final Color blue = new Color(0, 0, 170);
    private final Color blueGoal = new Color(0, 0, 255);
    private final Color yellow = new Color(200, 200, 0);
    private final Color grey = new Color(128, 128, 128);
    private final Color green = new Color(0, 128, 0);
    private final Color black = new Color(0, 0, 0);

    public Graphic(int rows, int columns, int[][] gameBoard) {
        this.rows = rows;
        this.columns = columns;
        this.board = new JButton[rows][columns];
        this.gameBoard = gameBoard;
        //JFrame setup, first time doing this idk what they do
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000,1000);
        //Sets the grid layout of the frame to the number of indicated rows and columns
        frame.setLayout(new BorderLayout());
        frame.setTitle("Game");

        panel1.setLayout(new GridLayout(rows, columns));

        panel2.setLayout(new GridLayout(1, 5));
        panel2.setPreferredSize(new Dimension(2000,50));
        turn.setFont(new Font("Arial", Font.PLAIN, 20));
        steps.setFont(new Font("Arial", Font.PLAIN, 20));
        panel2.add(new JLabel(""));
        panel2.add(new JLabel(""));
        panel2.add(turn);
        panel2.add(new JLabel(""));
        panel2.add(steps);

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
                }else if(gameBoard[i][j] == 4) {
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(gameBoard[i][j] == 5) {
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else if(gameBoard[i][j] == -2){
                    board[i][j].setBackground(green);
                    board[i][j].setBorder(BorderFactory.createLineBorder(green));
                }else if(gameBoard[i][j] == -3){
                    board[i][j].setBackground(black);
                    board[i][j].setBorder(BorderFactory.createLineBorder(black));
                }else if(gameBoard[i][j] == -100){
                    board[i][j].setBackground(yellow);
                    board[i][j].setBorder(BorderFactory.createLineBorder(yellow));
                }else{
                    board[i][j].setBackground(white);
                }
            }
        }
        //adds the buttons to the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                panel1.add(board[i][j]);
            }
        }
        frame.add(panel2, BorderLayout.NORTH);
        frame.add(panel1);
        frame.setVisible(true);
    }

    public void updateBoard() {
        //initializes the JButtons which will be the grids for the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(gameBoard[i][j] == 0) {
                    board[i][j].setBackground(white);
                }else if(gameBoard[i][j] == 1){
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                }else if(gameBoard[i][j] == 2) {
                    board[i][j].setBackground(blue);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blue));
                }else if(gameBoard[i][j] == 3) {
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else if(gameBoard[i][j] == 4) {
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(gameBoard[i][j] == 5) {
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else if(gameBoard[i][j] == -2){
                    board[i][j].setBackground(green);
                    board[i][j].setBorder(BorderFactory.createLineBorder(green));
                }else if(gameBoard[i][j] == -3){
                    board[i][j].setBackground(black);
                    board[i][j].setBorder(BorderFactory.createLineBorder(black));
                }else if(gameBoard[i][j] == -100){
                    board[i][j].setBackground(yellow);
                    board[i][j].setBorder(BorderFactory.createLineBorder(yellow));
                }else{
                    board[i][j].setBackground(white);
                }

            }
        }

    }

    //Effects of what button presses do
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].getModel().isRollover()) {
                    if(Game.turn == 0){
                        gameBoard[i][j] = 1;
                        updateBoard();
                        board[i][j].removeActionListener(this);
                        Game.turn = 1;
                        turn.setText("Blue's Turn.");
                        Game.stepsTaken++;
                        steps.setText("Steps: " + Game.stepsTaken);
                    }else if(Game.turn == 1){
                        gameBoard[i][j] = 2;
                        updateBoard();
                        board[i][j].removeActionListener(this);
                        Game.turn = 0;
                        turn.setText("Red's Turn.");
                        Game.stepsTaken++;
                        steps.setText("Steps: " + Game.stepsTaken);
                    }
                }
            }
        }
    }
}