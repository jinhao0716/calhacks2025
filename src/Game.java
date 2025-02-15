import java.util.Scanner;

public class Game
{
    //stats
    private static final int ROWS = 10;
    private static final int COLUMNS = 20;
    private static final int XBORDER = 1;
    private static final int YBORDER = 1;
    private static final int ISLANDSIZE = 2;
    private static final int BUFFER = 2;
    private static final int ISLANDSNUMBER = 2;

    public static void game(String[] args)
    {
        //keeping track of player victories
        boolean p1Victory = false;
        boolean p2Victory = false;

        //create and fill the game board
        int[][] gameboard = Board.generateBoard(ROWS, COLUMNS);
        Board.generateStartEndIslands(gameboard, XBORDER, YBORDER, ISLANDSIZE);
        Board.generateMidIslands(gameboard, XBORDER, ISLANDSIZE, BUFFER, ISLANDSNUMBER);

        /////////graphic stuff///////



    }

    public static void play(int[][] board, int player)
    {
        //for holding the player's x and y coords.
        int playerX;
        int playerY;

        //first find where the player is on the board
        //go along each column...
        for (int x = 0; x < board[0].length; x ++)
        {
            //then along each row...
            for (int y = 0; y < board.length; y++)
            {
                //if the player is here...
                if (board[y][x] == player)
                {
                    //note down the position
                    playerX = x;
                    playerY = y;
                }
            }
        }

        //create scanner
        Scanner scan = new Scanner(System.in);

        //collect user input on which direction they want to go
        System.out.println("Player's " + player + " move: (W, A, S, or D)");
    }
}
