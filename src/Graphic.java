//Code by Jin Hao Fan
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphic implements ActionListener {
    /*
     Global Variables
     */
    private static int rows;
    private static int columns;
    private static int score = 1000;
    private static String endText = "";
    private static JButton[][] board;
    private static JFrame frame = new JFrame();
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JLabel turn = new JLabel("Red's Turn.", SwingConstants.CENTER);
    private static JLabel steps = new JLabel("Steps: " + Game.stepsTaken, SwingConstants.CENTER);
    private static JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
    private static JLabel endScreen = new JLabel(endText, SwingConstants.CENTER);
    /*
    Colors
     */
    private static final Color white = new Color(255, 255, 255);
    private static final Color red = new Color(170, 0, 0);
    private static final Color redGoal = new Color(255, 30, 50);
    private static final Color blue = new Color(0, 30, 170);
    private static final Color blueGoal = new Color(0, 0, 255);
    private static final Color yellow = new Color(250, 250, 0);
    private static final Color grey = new Color(128, 128, 128);
    private static final Color green = new Color(0, 128, 0);
    private static final Color black = new Color(0, 0, 0);
    private static final ImageIcon player1 = new ImageIcon("PlayerImages/player1.png");
    private static final ImageIcon player2 = new ImageIcon("PlayerImages/player2.png");

    public Graphic(int rows, int columns) {
        Graphic.rows = rows;
        Graphic.columns = columns;
        Graphic.board = new JButton[rows][columns];

        /*
        Initializes the JFrame, sets window size to 2000x1000
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000,1000);

        /*
        Sets the JFrame layout to BorderLayout and names the window
         */
        frame.setLayout(new BorderLayout());
        frame.setTitle("BridgeGame");

        /*
        panel1 holds the game board, creates a board with tiles based on dimensions set
        Uses GridLayout
         */
        panel1.setLayout(new GridLayout(rows, columns));

        /*
        panel2 shows score, who's turn it is, and steps taken
         */
        panel2.setLayout(new GridLayout(1, 5));
        panel2.setPreferredSize(new Dimension(2000,50));
        turn.setFont(new Font("Arial", Font.PLAIN, 20));
        steps.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        endScreen.setFont(new Font("Arial", Font.PLAIN, 40));
        panel2.add(scoreLabel);
        panel2.add(new JLabel(""));
        panel2.add(turn);
        panel2.add(new JLabel(""));
        panel2.add(steps);

        /*
        Initializes the game board taken from Game,java
         */
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
                }else if(Game.gameboard[i][j] == 11){
                    board[i][j].setIcon(player1);
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                }else if(Game.gameboard[i][j] == 13){
                    board[i][j].setIcon(player1);
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else if(Game.gameboard[i][j] == 14){
                    board[i][j].setIcon(player1);
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(Game.gameboard[i][j] == 15){
                    board[i][j].setIcon(player1);
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else if(Game.gameboard[i][j] == 22){
                    board[i][j].setIcon(player2);
                    board[i][j].setBackground(blue);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blue));
                }else if(Game.gameboard[i][j] == 23){
                    board[i][j].setIcon(player2);
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else if(Game.gameboard[i][j] == 24){
                    board[i][j].setIcon(player2);
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(Game.gameboard[i][j] == 25){
                    board[i][j].setIcon(player2);
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else{
                    board[i][j].setBackground(white);
                }
            }
        }

        /*
        adds the buttons to the board
         */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                panel1.add(board[i][j]);
            }
        }
        /*
        adds the panels to the frame
         */
        frame.add(panel2, BorderLayout.NORTH);
        frame.add(panel1);
        frame.setVisible(true);
    }

    /*
    Updates the visuals of the board, called when a tile is clicked
     */
    public static void updateBoard() {
        //initializes the JButtons which will be the grids for the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(Game.gameboard[i][j] == 0) {
                    board[i][j].setBackground(white);
                }else if(Game.gameboard[i][j] == 1){
                    board[i][j].setIcon(null);
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                }else if(Game.gameboard[i][j] == 2) {
                    board[i][j].setIcon(null);
                    board[i][j].setBackground(blue);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blue));
                }else if(Game.gameboard[i][j] == 3) {
                    board[i][j].setIcon(null);
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else if(Game.gameboard[i][j] == 4) {
                    board[i][j].setIcon(null);
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(Game.gameboard[i][j] == 5) {
                    board[i][j].setIcon(null);
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else if(Game.gameboard[i][j] == -2){
                    board[i][j].setIcon(null);
                    board[i][j].setBackground(green);
                    board[i][j].setBorder(BorderFactory.createLineBorder(green));
                }else if(Game.gameboard[i][j] == -3){
                    board[i][j].setIcon(null);
                    board[i][j].setBackground(black);
                    board[i][j].setBorder(BorderFactory.createLineBorder(black));
                }else if(Game.gameboard[i][j] == -100){
                    board[i][j].setIcon(null);
                    board[i][j].setBackground(yellow);
                    board[i][j].setBorder(BorderFactory.createLineBorder(yellow));
                }else if(Game.gameboard[i][j] == 11){
                    board[i][j].setIcon(player1);
                    board[i][j].setBackground(red);
                    board[i][j].setBorder(BorderFactory.createLineBorder(red));
                }else if(Game.gameboard[i][j] == 13){
                    board[i][j].setIcon(player1);
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else if(Game.gameboard[i][j] == 14){
                    board[i][j].setIcon(player1);
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(Game.gameboard[i][j] == 15){
                    board[i][j].setIcon(player1);
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else if(Game.gameboard[i][j] == 22){
                    board[i][j].setIcon(player2);
                    board[i][j].setBackground(blue);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blue));
                }else if(Game.gameboard[i][j] == 23){
                    board[i][j].setIcon(player2);
                    board[i][j].setBackground(grey);
                    board[i][j].setBorder(BorderFactory.createLineBorder(grey));
                }else if(Game.gameboard[i][j] == 24){
                    board[i][j].setIcon(player2);
                    board[i][j].setBackground(blueGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(blueGoal));
                }else if(Game.gameboard[i][j] == 25){
                    board[i][j].setIcon(player2);
                    board[i][j].setBackground(redGoal);
                    board[i][j].setBorder(BorderFactory.createLineBorder(redGoal));
                }else{
                    board[i][j].setBackground(white);
                }

            }
        }

    }

    /*
    Calculates the players' current score
     */
    public static void calculateScore() {
        score = 1000 - Game.stepsTaken * 34;
        if(score > 1000){
            score = 1000;
        }
        if (score < 0){
            score = 0;
        }
    }

    /*
    Checks if there is a valid adjacent tile for the tile to be placed
    Valid tiles are tiles of the same color, the other player's end island, or neutral tiles
     */
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

        if(turn == 1){
            if (up){
                if((Game.gameboard[row - 1][col]) == 1 || (Game.gameboard[row - 1][col]) == 3 || (Game.gameboard[row - 1][col]) == 4 || (Game.gameboard[row - 1][col]) == 14 || (Game.gameboard[row - 1][col]) == 24){
                    check = true;
                }
            }
            if (down){
                if((Game.gameboard[row + 1][col]) == 1 || (Game.gameboard[row + 1][col]) == 3 || (Game.gameboard[row + 1][col]) == 4 || (Game.gameboard[row + 1][col]) == 14 || (Game.gameboard[row + 1][col]) == 24){
                    check = true;
                }
            }
            if (left){
                if((Game.gameboard[row][col - 1]) == 1 || (Game.gameboard[row][col - 1]) == 3 || (Game.gameboard[row][col - 1]) == 4 || (Game.gameboard[row][col - 1]) == 14 || (Game.gameboard[row][col - 1]) == 24){
                    check = true;
                }
            }
            if (right){
                if((Game.gameboard[row][col + 1]) == 1 || (Game.gameboard[row][col + 1]) == 3 || (Game.gameboard[row][col + 1]) == 4 || (Game.gameboard[row][col + 1]) == 14 || (Game.gameboard[row][col + 1]) == 24){
                    check = true;
                }
            }
        }

        if(turn == 0){
            if (up){
                if((Game.gameboard[row - 1][col]) == 2 || (Game.gameboard[row - 1][col]) == 3 || (Game.gameboard[row - 1][col]) == 5 || (Game.gameboard[row - 1][col]) == 15 || (Game.gameboard[row - 1][col]) == 25){
                    check = true;
                }
            }
            if (down){
                if((Game.gameboard[row + 1][col]) == 2 || (Game.gameboard[row + 1][col]) == 3 || (Game.gameboard[row + 1][col]) == 5 || (Game.gameboard[row + 1][col]) == 15 || (Game.gameboard[row + 1][col]) == 25){
                    check = true;
                }
            }
            if (left){
                if((Game.gameboard[row][col - 1]) == 2 || (Game.gameboard[row][col - 1]) == 3 || (Game.gameboard[row][col - 1]) == 5 || (Game.gameboard[row][col - 1]) == 15 || (Game.gameboard[row][col - 1]) == 25){
                    check = true;
                }
            }
            if (right){
                if((Game.gameboard[row][col + 1]) == 2 || (Game.gameboard[row][col + 1]) == 3 || (Game.gameboard[row][col + 1]) == 5 || (Game.gameboard[row][col + 1]) == 15 || (Game.gameboard[row][col + 1]) == 25){
                    check = true;
                }
            }
        }
        return check;
    }

    /*
    Displays the end game screen
     */
    public static void gameOver() {
        String temp = "";
        if (score <= 200){
            temp = "Your Final Score: \n" + score + "\nBetter luck next time!";
            endText = "<html>" + temp.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
        }
        if (score < 400 && score > 200){
            temp = "Your Final Score: \n" + score + "\nNice Job!";
            endText = "<html>" + temp.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
        }
        if (score > 650){
            temp = "Your Final Score: \n" + score + "\nPerfect Victory!";
            endText = "<html>" + temp.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
        }
        endScreen.setText(endText);
        frame.remove(panel1);
        frame.remove(panel2);
        endScreen.setVerticalAlignment(SwingConstants.CENTER);
        panel3.add(endScreen);
        frame.add(panel3);
    }
    /*
    Makes changes based on what tile is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].getModel().isRollover()) {
                    if(Game.turn == 0){
                        if(checkAdjacent(i,j,0)){
                            Game.gameboard[i][j] = 2;
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
                            Game.gameboard[i][j] = 1;
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