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
    private int score = 1000;
    private String endText = "";
    private JButton[][] board;
    private JFrame frame = new JFrame();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel turn = new JLabel("Red's Turn.", SwingConstants.CENTER);
    private JLabel steps = new JLabel("Steps: " + Game.stepsTaken, SwingConstants.CENTER);
    private JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
    private JLabel endScreen = new JLabel(endText, SwingConstants.CENTER);
    //Colors
    private final Color white = new Color(255, 255, 255);
    private final Color red = new Color(170, 30, 0);
    private final Color redGoal = new Color(255, 30, 0);
    private final Color blue = new Color(0, 0, 170);
    private final Color blueGoal = new Color(0, 0, 255);
    private final Color yellow = new Color(250, 250, 0);
    private final Color grey = new Color(128, 128, 128);
    private final Color green = new Color(0, 128, 0);
    private final Color black = new Color(0, 0, 0);

    public Graphic(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new JButton[rows][columns];
        //JFrame setup, first time doing this idk what they do
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000,1000);
        //Sets the grid layout of the frame to the number of indicated rows and columns
        frame.setLayout(new BorderLayout());
        frame.setTitle("BridgeGame");

        panel1.setLayout(new GridLayout(rows, columns));

        panel2.setLayout(new GridLayout(1, 5));
        panel2.setPreferredSize(new Dimension(2000,50));
        turn.setFont(new Font("Arial", Font.PLAIN, 20));
        steps.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel2.add(scoreLabel);
        panel2.add(new JLabel(""));
        panel2.add(turn);
        panel2.add(new JLabel(""));
        panel2.add(steps);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new JButton("");
                board[i][j].setUI(new BasicButtonUI());
                if(Game.gameboard[i][j] == 0) {
                    board[i][j].setBackground(white);
                    board[i][j].addActionListener(this);
                }else if(Game.gameboard[i][j] == 1){
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                }else if(Game.gameboard[i][j] == 2) {
                    board[i][j].setBackground(blue);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blue));
                }else if(Game.gameboard[i][j] == 3) {
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else if(Game.gameboard[i][j] == 4) {
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(Game.gameboard[i][j] == 5) {
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else if(Game.gameboard[i][j] == -2){
                    board[i][j].setBackground(green);
                    board[i][j].setBorder(BorderFactory.createLineBorder(green));
                }else if(Game.gameboard[i][j] == -3){
                    board[i][j].setBackground(black);
                    board[i][j].setBorder(BorderFactory.createLineBorder(black));
                }else if(Game.gameboard[i][j] == -100){
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
                if(Game.gameboard[i][j] == 0) {
                    board[i][j].setBackground(white);
                }else if(Game.gameboard[i][j] == 1){
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                }else if(Game.gameboard[i][j] == 2) {
                    board[i][j].setBackground(blue);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blue));
                }else if(Game.gameboard[i][j] == 3) {
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else if(Game.gameboard[i][j] == 4) {
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(Game.gameboard[i][j] == 5) {
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else if(Game.gameboard[i][j] == -2){
                    board[i][j].setBackground(green);
                    board[i][j].setBorder(BorderFactory.createLineBorder(green));
                }else if(Game.gameboard[i][j] == -3){
                    board[i][j].setBackground(black);
                    board[i][j].setBorder(BorderFactory.createLineBorder(black));
                }else if(Game.gameboard[i][j] == -100){
                    board[i][j].setBackground(yellow);
                    board[i][j].setBorder(BorderFactory.createLineBorder(yellow));
                }else{
                    board[i][j].setBackground(white);
                }

            }
        }

    }

    public void calculateScore() {
        score = 1000 - Game.stepsTaken * 34;
        if(score > 1000){
            score = 1000;
        }
        if (score < 0){
            score = 0;
        }
    }

    public boolean checkAdjacent(int row, int col, int turn) {
        boolean check = false;

        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;
        if ((row - 1) < 0){
            up = false;
        }
        if ((row + 1) > (Game.gameboard.length - 1)){
            down = false;
        }
        if ((col - 1) < 0){
            left = false;
        }
        if ((col + 1) > (Game.gameboard[0].length - 1)){
            right = false;
        }

        if(turn == 0){
            if (up){
                if((Game.gameboard[row - 1][col]) == 1 || (Game.gameboard[row - 1][col]) == 3 || (Game.gameboard[row - 1][col]) == 5){
                    check = true;
                }
            }
            if (down){
                if((Game.gameboard[row + 1][col]) == 1 || (Game.gameboard[row + 1][col]) == 3 || (Game.gameboard[row + 1][col]) == 5){
                    check = true;
                }
            }
            if (left){
                if((Game.gameboard[row][col - 1]) == 1 || (Game.gameboard[row][col - 1]) == 3 || (Game.gameboard[row][col - 1]) == 5){
                    check = true;
                }
            }
            if (right){
                if((Game.gameboard[row][col + 1]) == 1 || (Game.gameboard[row][col + 1]) == 3 || (Game.gameboard[row][col + 1]) == 5){
                    check = true;
                }
            }
        }

        if(turn == 1){
            if (up){
                if((Game.gameboard[row - 1][col]) == 2 || (Game.gameboard[row - 1][col]) == 3 || (Game.gameboard[row - 1][col]) == 4){
                    check = true;
                }
            }
            if (down){
                if((Game.gameboard[row + 1][col]) == 2 || (Game.gameboard[row + 1][col]) == 3 || (Game.gameboard[row + 1][col]) == 4){
                    check = true;
                }
            }
            if (left){
                if((Game.gameboard[row][col - 1]) == 2 || (Game.gameboard[row][col - 1]) == 3 || (Game.gameboard[row][col - 1]) == 4){
                    check = true;
                }
            }
            if (right){
                if((Game.gameboard[row][col + 1]) == 2 || (Game.gameboard[row][col + 1]) == 3 || (Game.gameboard[row][col + 1]) == 4){
                    check = true;
                }
            }
        }
        return check;
    }

    public void gameOver() {
        if (score < 200){
            endText = "Your Final Score: \n" + score + "\nBetter luck next time!";
        }
        if (score < 400){
            endText = "Your Final Score: \n" + score + "\nNice Job!";
        }
        if (score < 650){
            endText = "Your Final Score: \n" + score + "\nPerfect Victory!";
        }
        frame.remove(panel1);
        frame.remove(panel2);
        frame.add(endScreen);
    }
    //Effects of what button presses do
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].getModel().isRollover()) {
                    if(Game.turn == 0){
                        if(checkAdjacent(i,j,0)){
                            Game.gameboard[i][j] = 1;
                            updateBoard();
                            board[i][j].removeActionListener(this);
                            Game.turn = 1;
                            turn.setText("Blue's Turn.");
                            Game.stepsTaken++;
                            steps.setText("Steps: " + Game.stepsTaken);
                            calculateScore();
                            scoreLabel.setText("Score: " + score);
                        }
                    }else if(Game.turn == 1){
                        if(checkAdjacent(i,j,1)){
                            Game.gameboard[i][j] = 2;
                            updateBoard();
                            board[i][j].removeActionListener(this);
                            Game.turn = 0;
                            turn.setText("Red's Turn.");
                            Game.stepsTaken++;
                            steps.setText("Steps: " + Game.stepsTaken);
                            calculateScore();
                            scoreLabel.setText("Score: " + score);
                        }
                    }
                }
            }
        }
    }
}