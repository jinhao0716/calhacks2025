import java.util.Random;

public class Board
{
    /**
     * creates an empty playing board (filled with 0's)
     *
     * @param rows number of rows in the board
     * @param columns number of columns in the board
     * @return the generated board
     */
    public static int[][] generateBoard(int rows, int columns)
    {
        return new int[rows][columns];
    }

    /**
     * gives the number of columns in the board
     *
     * @param board the board to be measured
     * @return the number of columns in board
     */
    public static int boardLength(int[][] board)
    {
        return board[0].length;
    }

    /**
     * gives the number of rows in the board
     *
     * @param board the board to be measured
     * @return the number of rows in board
     */
    public static int boardHeight(int[][] board)
    {
        return board.length;
    }

    /**
     * creates the start and end islands, also adds the two players to it
     *
     * @param board the playing board to add these islands to
     * @param xBorder the distance between the islands and the horizontal edges of the map
     * @param yBorder the distance between the islands and the vertical edges of the map
     * @param islandSize size of the island
     */
    public static void generateGoalIslands(int[][] board, int xBorder, int yBorder, int islandSize)
    {
        //create instance of random class for number generation
        Random rand = new Random();

        //create random coordinates for the starting island, use island's top left corner to position
        int p2goalX = xBorder; //start island x pos is not randomized
        int p2goalY = yBorder + rand.nextInt(boardHeight(board) - (2*yBorder + islandSize));


        //create random coordinates for the starting island, use island's top left corner to position
        int p1goalX = boardLength(board) - xBorder - islandSize; //end island x pos is not randomized
        int p1goalY = yBorder + rand.nextInt(boardHeight(board) - (2*yBorder + islandSize - 1));

        //add in islands of appropriate size
        for (int x = 0; x < islandSize; x++)
        {
            for (int y = 0; y < islandSize; y++)
            {
                board[p2goalY + y][p2goalX + x] = 4;
                board[p1goalY + y][p1goalX + x] = 5;
            }
        }

        //now add in the players
        board[p2goalY][p2goalX] += 10;
        board[p1goalY + islandSize - 1][p1goalX + islandSize - 1] += 20;
    }

    /**
     * creates the small middle islands between the start and end islands
     *
     * @param board the playing board to add these islands to
     * @param xBorder the distance between the START AND END islands and the horizontal edges of the map
     * @param islandSize size of the START AND END island
     * @param buffer the minimum distance possible between the mid islands and border islands
     * @param islandNumber the number of islands to generate +/- 50%, rounded down
     */
    public static void generateMidIslands(int[][] board, int xBorder, int islandSize, int buffer, int islandNumber)
    {
        //create instance of random class for number generation
        Random rand = new Random();

        //the number of islands to make
        int islandNum = islandNumber + (rand.nextInt(5) - 2) * (islandNumber / 4); //islandNumber +/- (0-2 * islandNumber / 4)

        //for each island to add...
        for (int n = 0; n < islandNum; n++)
        {
            //create random island stats
            int length = rand.nextInt(3) + 1;
            int height = rand.nextInt(3) + 1;


            //create random coordinates for the starting island, use island's top left corner to position
            int xPos = (xBorder + islandSize + buffer) + rand.nextInt(boardLength(board) - (2*(islandSize + xBorder + buffer) + length - 1));
            int yPos = rand.nextInt(boardHeight(board) - height);

            //go column by column...
            for (int x = 0; x < length; x++)
            {
                //and fill in each plot of island
                for (int y = 0; y < height; y++)
                {
                    board[yPos + y][xPos + x] = generateBonuses(rand);
                }
            }
        }
    }

    /**
     * generates either a hazard, a power up, or nothing at all
     *
     * @param rand Random object for probability
     * @return 3 if nothing, -2 if power up, and -3 if hazard
     */
    public static int generateBonuses(Random rand)
    {
        /////////////////// PROBABILITY CHART ////////////////////////////
        /// 50% nothing spawns
        /// 40% hazard spawns
        /// 9% power up spawns
        /// 1% SUPER power up spawns
        /////////////////// EFFECTS //////////////////////////////////////
        /// nothing: nothing
        /// hazard: uncrossable, acts as a wall
        /// power up: decreases total step count

        //use a random number to decide what spawns
        int roll  = 1 + rand.nextInt(100);

        //now create the appropriate tile
        if (roll <= 50) //if nothing spawns...
        {
            return 3;
        }else if (roll <= 90) //if hazard spawns...
        {
            return -3;
        }else if (roll <= 99) //if power up spawns...
        {
            return -2;
        }else //if SUPER power up spawns...
        {
            return -100;
        }
    }

}
