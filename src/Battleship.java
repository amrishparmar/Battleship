import java.util.*;

public class Battleship {
    static Scanner scan;
    static Random random;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        random = new Random();

        int gridSize = 10;
        int[][] grid = new int[gridSize][gridSize];

        for (int[] row : grid) {
            Arrays.fill(row, 0);
        }

        System.out.println("*** Welcome to the Battleships game ***");
        System.out.println();
        System.out.println("Right now the sea is empty.");

        printBoard(grid);

        int numToDeploy = 5;

        deployPlayerShips(grid, numToDeploy);
        deployEnemyShip(grid, numToDeploy);

        printBoard(grid);

    }

    public static void printBoard(int[][] grid) {
        HashMap<Integer, String> visMap = new HashMap<>();
        visMap.put(-1, "X");
        visMap.put(0, " ");
        visMap.put(1, "@");
        visMap.put(2, "E");

        System.out.println();
        for (int i = -1; i <= grid.length + 1; ++i) {
            if (i < 0 || i >= grid.length)
                System.out.print("   ");
            else
                System.out.print(i);
        }
        System.out.println();
        for (int i = 0; i < grid.length; ++i) {
            System.out.print(i + " |");
            for (int j = 0; j < grid.length; ++j) {
                System.out.print(visMap.get(grid[i][j]));
            }
            System.out.println("| " + i);
        }
        for (int i = -1; i <= grid.length + 1; ++i) {
            if (i < 0 || i >= grid.length)
                System.out.print("   ");
            else
                System.out.print(i);
        }
        System.out.println();
    }

    public static void deployPlayerShips(int[][] grid, int numToDeploy) {
        for (int i = 0; i < numToDeploy; ++i) {
            while (true) {
                Coordinate coors = getShipCoords(grid.length, i + 1);
                if (!isPlayerShip(grid, coors)) {
                    grid[coors.x][coors.y] = 1;
                    break;
                }
                System.out.println("You already have a ship deployed at this position.");
            }
        }
    }

    public static void deployEnemyShip(int[][] grid, int numToDeploy) {
        for (int i = 0; i < numToDeploy; ++i) {
            while (true) {
                Coordinate coors = new Coordinate(random.nextInt(10), random.nextInt(10));
                if (!isPlayerShip(grid, coors) && !isEnemyShip(grid, coors)) {
                    grid[coors.x][coors.y] = 2;
                    break;
                }
            }
        }
    }

    public static Coordinate getShipCoords(int gridSize, int shipNo) {
        while (true) {
            System.out.print("Enter X coordinate for ship " + shipNo + " (enter a value between 0 and " + (gridSize - 1) + "): ");
            int x = getInteger();
            System.out.print("Enter Y coordinate for ship " + shipNo + "(enter a value between 0 and " + (gridSize - 1) + "): ");
            int y = getInteger();

            if (x < 0 || x > gridSize || y < 0 || y > gridSize) {
                System.out.println("You have entered a coordinate outside of the grid.");
                continue;
            }

            return new Coordinate(x, y);
        }
    }

    public static boolean isEnemyShip(int[][] grid, Coordinate coors) {
        return grid[coors.x][coors.y] == 2;
    }

    public static boolean isPlayerShip(int[][] grid, Coordinate coors) {
        return grid[coors.x][coors.y] == 1;
    }

    public static int getInteger() {
        int n;
        while (true) {
            try {
                n = scan.nextInt();
                break;
            }
            catch (InputMismatchException ime) {
                System.out.print("Invalid value. Try again: ");
                scan.nextLine();
            }
        }
        return n;
    }
}
