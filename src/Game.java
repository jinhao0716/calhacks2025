import java.util.Scanner;

public class Game
{
    ///////KEY///////
    /// 1 = player 1 land
    /// 2 = player 2 land
    /// 5 = player 1 goal
    /// 4 = player 2 goal
    /// 3 = neutral middle island land
    /// -3 = hazard
    /// -2 = power up
    /// -100 = SUPER power up
    /// ab (a = player number, b = tile number)

    //stats
    private static final int ROWS = 10;
    private static final int COLUMNS = 20;
    private static final int XBORDER = 1;
    private static final int YBORDER = 1;
    private static final int ISLANDSIZE = 2;
    private static final int BUFFER = 2;
    private static final int ISLANDSNUMBER = 6;
    private static final int POWERUPSTRENGTH = 2;
    private static final int SUPERPOWERUPSTRENGTH = -1000;

    public static int turn = 0;
    public static int stepsTaken = 0;

    public static void main(String[] args)
    {
        //keeping track of player victories
        boolean p1Victory = false;
        boolean p2Victory = false;

        //create and fill the game board
        int[][] gameboard = Board.generateBoard(ROWS, COLUMNS);
        Board.generateGoalIslands(gameboard, XBORDER, YBORDER, ISLANDSIZE);
        Board.generateMidIslands(gameboard, XBORDER, ISLANDSIZE, BUFFER, ISLANDSNUMBER);

        /////////graphic stuff///////
        new Graphic(ROWS, COLUMNS, gameboard);


    }

    /**
     * runs everything a player need to move
     *
     * @param board the board the player is moving on
     * @param player the player doing the moving
     */
    public static void play(int[][] board, int player)
    {
        //collect the player's position
        int[] curPos = findPlayer(board, player);

        //set the controls for the player
        String[] controls = new String[4];

        //if not player 1 and using arrow keys, controls array will be filled with null
        if (player == 1)
        {
            controls[0] = "I";
            controls[1] = "J";
            controls[2] = "K";
            controls[3] = "L";
        }else
        {
            controls[0] = "W";
            controls[1] = "A";
            controls[2] = "S";
            controls[3] = "D";
        }

        //keep track if they moved
        boolean moved = false;

        //keep pestering them to move until they can't
        do
        {
            //collect the position they want to move to...
            int[] newPos = collectMove(player, curPos, controls);
            moved = checkMove(board, curPos, newPos, player);
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
        return new int[]{playerX, playerY};
    }

    /**
     * determines the coordinates of a player's possible move
     *
     * @param player the player that is moving
     * @param curCoords the current coordinates of the player, in [x, y] form
     * @param controls the controls for the player that is moving (WASD or arrow keys)
     * @return an int array containing the new coords, [x, y]
     */
    public static int[] collectMove(int player, int[] curCoords, String[] controls)
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
                playerNewX = curCoords[0];
                playerNewY = curCoords[1] - 1;
                moved = true;
            }
            else if (move.equals(controls[2])) //if go down...
            {
                playerNewX = curCoords[0];
                playerNewY = curCoords[1] + 1;
                moved = true;
            }
            else if (move.equals(controls[1])) //if go left...
            {
                playerNewX = curCoords[0] - 1;
                playerNewY = curCoords[1];
                moved = true;
            }
            else if (move.equals(controls[3])) //if go right...
            {
                playerNewX = curCoords[0] + 1;
                playerNewY = curCoords[1];
                moved = true;
            }
            else //if the user enters invalid input...
            {
                moved = false;
            }
        }while (!moved);

        return new int[]{playerNewX, playerNewY};
    }

    /**
     * checks to see if a move is valid, and perform the move if so
     *
     * @param board the board on which moving is happening
     * @param curCoords the current position of the player
     * @param newCoords the postion the player is trying to move to
     * @param player the player doing the moving
     * @return
     */
    public static boolean checkMove(int[][] board, int[] curCoords, int[] newCoords, int player)
    {
        //if any of the moves would go outside of the map, then instantly invalid
        if (newCoords[0] < 0 || newCoords[1] < 1)
        {
            return false;
        }

        //check if it's the tile they're trying to move onto is the same color as them (or a void/hazard)...
        if (board[newCoords[1]][newCoords[0]] != player  && board[newCoords[1]][newCoords[0]] != 0 && board[newCoords[1]][newCoords[0]] != -3)
        {
            //if it's not, then check to see if there is already a player on there (make sure tile # isn't double digits)
            if (!(board[newCoords[1]][newCoords[0]] - 10 >= 0))
            {
                //then check for power ups...
                if (board[newCoords[1]][newCoords[0]] == -2)
                {
                    //if there are any pick it up and turn the tile back to normal
                    board[newCoords[1]][newCoords[0]] = 3;
                    stepsTaken -= POWERUPSTRENGTH;
                }else if (board[newCoords[1]][newCoords[0]] == -2) //then check for SUPER power ups...
                {
                    //if there are any pick it up and turn the tile back to normal
                    board[newCoords[1]][newCoords[0]] = 3;
                    stepsTaken -= SUPERPOWERUPSTRENGTH;
                }

                //then move the player and reset the tile they were previously standing on
                board[newCoords[1]][newCoords[0]] += (player * 10);
                board[curCoords[1]][curCoords[0]] -= (player * 10);

                return true;
            }
        }

        //if reaching this point, then means player has not been moved
        return false;
    }

    /**
     * checks to see if a player has reached their goal island
     *
     * @param board the board on which the player is standing on
     * @param curCoords the player's current coordinates
     * @param player the player to be checked
     * @return true if reached goal island, false if not
     */
    public static boolean goalReached(int[][] board, int[] curCoords, int player)
    {
        //check to see if the digits of the position (player + tile) sum up to 6...
        if ((board[curCoords[1]][curCoords[0]] - 10*player) + player == 6)
        {
            //if they do, then it means that the player has successfully reached their goal island
            return true;
        }

        //otherwise return false
        return false;
    }
}
