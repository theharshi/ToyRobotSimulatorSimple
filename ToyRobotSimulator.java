import java.util.Scanner;

public class ToyRobotSimulator {
    private static final int TABLE_SIZE = 5;
    private int x;
    private int y;
    private String facing;

    public ToyRobotSimulator() {
        // Initialize the robot at an invalid position
        x = -1;
        y = -1;
        facing = "";
    }

    public void place(int x, int y, String facing) {
        if (isValidPosition(x, y)) {
            this.x = x;
            this.y = y;
            this.facing = facing;
        }
    }

    public void move() {
        if (facing.equals("NORTH") && isValidPosition(x, y + 1)) {
            y++;
        } else if (facing.equals("SOUTH") && isValidPosition(x, y - 1)) {
            y--;
        } else if (facing.equals("EAST") && isValidPosition(x + 1, y)) {
            x++;
        } else if (facing.equals("WEST") && isValidPosition(x - 1, y)) {
            x--;
        }
    }

    public void left() {
        switch (facing) {
            case "NORTH":
                facing = "WEST";
                break;
            case "SOUTH":
                facing = "EAST";
                break;
            case "EAST":
                facing = "NORTH";
                break;
            case "WEST":
                facing = "SOUTH";
                break;
        }
    }

    public void right() {
        switch (facing) {
            case "NORTH":
                facing = "EAST";
                break;
            case "SOUTH":
                facing = "WEST";
                break;
            case "EAST":
                facing = "SOUTH";
                break;
            case "WEST":
                facing = "NORTH";
                break;
        }
    }

    public void report() {
        System.out.println("Position: (" + x + ", " + y + "), Facing: " + facing);
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < TABLE_SIZE && y >= 0 && y < TABLE_SIZE;
    }

    public static void main(String[] args) {
        ToyRobotSimulator robot = new ToyRobotSimulator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command (PLACE X,Y,F / MOVE / LEFT / RIGHT / REPORT): ");
            String command = scanner.nextLine().trim().toUpperCase();

            if (command.startsWith("PLACE")) {
                String[] parts = command.substring(6).split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                String facing = parts[2];
                robot.place(x, y, facing);
            } else if (command.equals("MOVE")) {
                robot.move();
            } else if (command.equals("LEFT")) {
                robot.left();
            } else if (command.equals("RIGHT")) {
                robot.right();
            } else if (command.equals("REPORT")) {
                robot.report();
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
    }
}
