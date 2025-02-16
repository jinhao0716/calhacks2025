import java.util.Scanner;

public class Game
{
    ///////KEY///////
    /// 1 = player 1 land
    /// 2 = player 2 land
    /// 3 = neutral middle island land
    /// ab (a = player number, b = tile number)

    //stats
    private static final int ROWS = 10;
    private static final int COLUMNS = 20;
    private static final int XBORDER = 1;
    private static final int YBORDER = 1;
    private static final int ISLANDSIZE = 2;
    private static final int BUFFER = 2;
    private static final int ISLANDSNUMBER = 2;

    public static void main(String[] args)
    {
        //keeping track of player victories
        boolean p1Victory = false;
        boolean p2Victory = false;

        //create and fill the game board
        int[][] gameboard = Board.generateBoard(ROWS, COLUMNS);
        Board.generateStartEndIslands(gameboard, XBORDER, YBORDER, ISLANDSIZE);
        Board.generateMidIslands(gameboard, XBORDER, ISLANDSIZE, BUFFER, ISLANDSNUMBER);

        /////////graphic stuff///////
        new Graphic(ROWS, COLUMNS, gameboard);


    }

    public static void play(int[][] board, int player)
    {
        //collect the player's position
        int[] curPos = findPlayer(board, player);

        //set the controls for the player
        String[] controls = new String[4];

        //if not player 1 and using arrow keys, controls array will be filled with null
        if (player == 1)
        {
            controls[0] = "W";
            controls[1] = "A";
            controls[2] = "S";
            controls[3] = "D";
        }else
        {
            controls[0] = "I";
            controls[1] = "J";
            controls[2] = "K";
            controls[3] = "L";
        }

        //keep track if they moved
        boolean moved = false;

        //keep pestering them to move until they can't
        do
        {
            //collect the position they want to move to...
            int[] newPos = collectMove(player, curPos, controls);
            moved = checkMove(board, newPos, player);
        }while(!moved);
    }

    /**
     * searches for the coordinates of a player on the board
     *
     * @param board the board to be searched
     * @param player the player to be found
     * @return an int array containing the new coords, [x, y]
     */
    public static int[] findPlayer(int[][] board, int player)
    {
        //for holding the player's x and y coords.
        int playerX = -1;
        int playerY = -1;

        //first find where the player is on the board
        //go along each column...
        for (int x = 0; x < board[0].length; x ++)
        {
            //then along each row...
            for (int y = 0; y < board.length; y++)
            {
                //if the player is here...
                if (board[y][x] - 10*player > 0)
                {
                    //note down the position
                    playerX = x;
                    playerY = y;
                }
            }
        }

        //store the coords in an array and return it
        int[] coords = {playerX, playerY};
        return coords;
    }

    /**
     * determines the coordinates of a player's possible move
     *
     * @param player the player that is moving
     * @param coords the current coordinates of the player, in [x, y] form
     * @param controls the controls for the player that is moving (WASD or arrow keys)
     * @return an int array containing the new coords, [x, y]
     */
    public static int[] collectMove(int player, int[] coords, String[] controls)
    {
        int playerNewX = -1;
        int playerNewY = -1;

        //create scanner
        Scanner scan = new Scanner(System.in);

        String keys = "";

        //convert the player's control from array into a string
        for (int i = 0; i < controls.length; i++)
        {
            keys = keys + controls[i];
        }

        boolean moved = true;

        //in case user enters invalid input, will keep looping
        do
        {
            //collect user input
            System.out.println("Player's " + player + " move: " + "(" + keys + ")");
            String move = scan.next().toUpperCase();

            //then see where they want to move
            if (move.equals(controls[0])) //if go up...
            {
                playerNewX = coords[0];
                playerNewY = coords[1] - 1;
                moved = true;
            }
            else if (move.equals(controls[2])) //if go down...
            {
                playerNewX = coords[0] - 1;
                playerNewY = coords[1];
                moved = true;
            }
            else if (move.equals(controls[1])) //if go left...
            {
                playerNewX = coords[0];
                playerNewY = coords[1] + 1;
                moved = true;
            }
            else if (move.equals(controls[3])) //if go right...
            {
                playerNewX = coords[0] + 1;
                playerNewY = coords[1];
                moved = true;
            }
            else //if the user enters invalid input...
            {
                moved = false;
            }
        }while (!moved);

        int[] newCoords = {playerNewX, playerNewY};
        return  newCoords;
    }

    /**
     * checks to see if a move is valid, and perform the move if so
     *
     * @param board
     * @param coords
     * @param player
     * @return
     */
    public static boolean checkMove(int[][] board, int[] coords, int player)
    {
        //if any of the moves would go outside of the map, then instantly invalid
        if (coords[0] < 0 || coords[1] < 1)
        {
            return false;
        }

        //check if it's the tile they're trying to move onto is the same color as them (or a void)...
        if (board[coords[1]][coords[0]] != player || board[coords[1]][coords[0]] != 0)
        {
            //if it's not, then check to see if there is already a player on there (make sure tile # isn't double digits)
            if (board[coords[1]][coords[0]] - 10 >= 0)
            {
                //then move the player
                board[coords[1]][coords[0]] += (player * 10);
            }
        }

        //if reaching this point, then means player has been moved
        return true;
    }
}
