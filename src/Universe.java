import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Use this template to create simple animations in FX. Change the name of the
 * class and put your own name as author below. Change the size of the canvas
 * and the window title where marked and add your drawing code in the animation
 * method where shown.
 *
 * @author Harshadkumar Patel, 000852665
 */
public class Universe extends Application {

    /**
     * Sets up the stage and starts the main thread. Your drawing code should
     * NOT go here.
     *
     * @param stage The first stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Universe"); // This code line is use to print window title
        Canvas canvas = new Canvas(600, 600); // This code line is set the canvas window size
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // This code set the background color of the window
        gc.setFill(Color.rgb(36, 36, 36));
        gc.fillRect(0, 0, 600, 600);

        // This code set the paint color
        gc.setStroke(Color.WHITE);

        // This code starts a "thread" which will run your animation
        Thread t = new Thread(() -> animate(gc));
        t.start();
    }

    /**
     * Animation thread. This is where you put your animation code.
     * <p>
     * Note: Although most of what you will do will probably work, there is a
     * possibility of the application appearing to freeze after a while because
     * the drawing is not happening in a thread safe way. If this is happening,
     * create a private draw() method with parameters for x, y, etc. to do the
     * actual drawing work, then call it like this...
     * <p>
     * Platform.runLater(() -> {
     * draw(x,y...);
     * });
     *
     * @param gc The drawing surface
     */
    public void animate(GraphicsContext gc) {
        // YOUR CODE HERE!

        // This code generate a random score and life
        Random random = new Random();

        // This code Print score
        gc.strokeText("Score: " + ((random.nextInt(2) + 1) * 12345 + random.nextInt(12345)), 50, 40);
        String life = "*";

        // This code use for generate random life
        for (int count = 0; count < random.nextInt(5); count++) {
            life = life + " *";
        }

        // This code print life
        gc.strokeText("Life: " + life, 50, 60);

        // This code is print copyright information on the bottom of the window
        gc.strokeText("@Harshadkumar Patel, 2022", 200, 550);

        // This function print the random star in the window
        drawSmallStar(gc);

        // This function print the random Planet in the window
        drawSmallPlanet(gc);

        // This code take input from the user about number of asteroid they want to print
        System.out.println("How many Asteroids do you want to draw");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("");
            System.out.println("Asteroid : " + (i + 1));

            // This function use to take input from the user for its potion and size of the asteroids and print on the window
            drawAsteroids(gc);
        }

        // This function use to take input from the user for its potion and size of the Spaceship and print on the window
        drawSpaceship(gc);

        // This function use to take input from the user for its potion and size of the Star and print on the window
        drawStar(gc);

        // This function use to take input from the user for its potion and size of the Player spaceship and print on the window
        drawPlayersSpaceship(gc);

    }

    /**
     * This function is use for print the Asteroids
     * This function ask the user to enter the coordinates of x and y, and size of the Asteroid
     */
    public void drawAsteroids(GraphicsContext gc) {

        // This code take input from the user for Asteroids
        ArrayList<Integer> values = getInput("Asteroids", gc);
        int r = values.get(0);
        int x = values.get(1);
        int y = values.get(2);
        int c = values.get(3);

        // This code set the selected color for the Asteroids
        gc.setStroke(getColor(c));

        // This code set the position and size of the Asteroids and print on the window
        gc.strokeLine(x + (r * 2), y + (r * 3), x + (r * 3), y + (r * 2));
        gc.strokeLine(x + (r * 3), y + (r * 2), x + (r * 4), y + (r * 3));
        gc.strokeLine(x + (r * 4), y + (r * 3), x + (r * 5), y + (r * 2));
        gc.strokeLine(x + (r * 5), y + (r * 2), x + (r * 6), y + (r * 3));
        gc.strokeLine(x + (r * 6), y + (r * 3), x + (r * 5), y + (r * 4));
        gc.strokeLine(x + (r * 5), y + (r * 4), x + (r * 6), y + (r * 5));
        gc.strokeLine(x + (r * 6), y + (r * 5), x + (r * 5), y + (r * 6));
        gc.strokeLine(x + (r * 5), y + (r * 6), x + (r * 3), y + (r * 6));
        gc.strokeLine(x + (r * 3), y + (r * 6), x + (r * 2), y + (r * 5));
        gc.strokeLine(x + (r * 2), y + (r * 5), x + (r * 2), y + (r * 3));
    }

    // This function return selected color from the user
    Color getColor(int value) {
        if (value == 1) {
            return Color.RED;
        } else if (value == 2) {
            return Color.BLUE;
        } else if (value == 3) {
            return Color.GREEN;
        } else {
            return Color.WHITE;
        }
    }

    // This function take input from the user for the Asteroids, Spaceship,Star, Player spaceship
    ArrayList<Integer> getInput(String stringValue, GraphicsContext gc) {
        System.out.println("");
        System.out.println("");
        System.out.println("------>>> " + stringValue);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Enter the values for the " + stringValue + " coordinates");
        System.out.println("======================================================================");
        Scanner sc = new Scanner(System.in);

        // Input the size of the object
        int r = 0;  // r stand for size

        // Input correct value until receive the correct value
        do {
            System.out.print("Enter the size of " + stringValue + " between 1 to 10 : ");
            r = sc.nextInt();
        } while (r < 1 || r > 10);

        // Input the position x of the object
        int x = 0; // x stand for position x coordinate
        // Input correct value until receive the correct value
        do {
            System.out.print("Enter the value of x coordinate between " + Math.round(r / 2) + " to " + gc.getCanvas().getWidth() + " : ");
            x = sc.nextInt();
        } while (x < Math.round(r / 2) || x > gc.getCanvas().getWidth() - 100);

        // Input the position y of the object
        int y = 0; // y stand for position y coordinate
        // Input correct value until receive the correct value
        do {
            System.out.print("Enter the value of x coordinate between " + 1 + " to " + Math.round(gc.getCanvas().getHeight() - Math.round(r / 2)) + " : ");
            y = sc.nextInt();
        } while (y < 1 || y > gc.getCanvas().getHeight() - 100);

        // Input the color of the object
        int c = 0; // c stand for color
        // Input correct value until receive the correct value
        do {
            System.out.print("Enter Color value, e.g. 1=red, 2=blue, 3=green : ");
            c = sc.nextInt();
        } while (c < 1 || c > 3);

        // This array return size, position x, position y and color value in a sequence
        ArrayList<Integer> values = new ArrayList<>();
        values.add(r);
        values.add(x);
        values.add(y);
        values.add(c);
        return values;
    }

    /**
     * This function is use for print the Spaceship
     * This function ask the user to enter the coordinates of x and y, and size of the Spaceship
     */
    public void drawSpaceship(GraphicsContext gc) {
        // This code take input from the user for Spaceship
        ArrayList<Integer> values = getInput("Spaceship", gc);
        int r = values.get(0);
        int x = values.get(1);
        int y = values.get(2);
        int c = values.get(3);

        // This code set the selected color for the Spaceship
        gc.setStroke(getColor(c));

        // This code set the position and size of the Spaceship and print on the window
        gc.strokeLine(x + (r * 3), y + (r * 2), x + (r * 4), y + (r * 2));
        gc.strokeLine(x + (r * 4), y + (r * 2), x + (r * 5), y + (r * 3));
        gc.strokeLine(x + (r * 5), y + (r * 3), x + (r * 5), y + (r * 4));
        gc.strokeLine(x + (r * 5), y + (r * 4), x + (r * 4), y + (r * 5));
        gc.strokeLine(x + (r * 4), y + (r * 5), x + (r * 3), y + (r * 5));
        gc.strokeLine(x + (r * 3), y + (r * 5), x + (r * 2), y + (r * 4));
        gc.strokeLine(x + (r * 2), y + (r * 4), x + (r * 2), y + (r * 3));
        gc.strokeLine(x + (r * 2), y + (r * 3), x + (r * 3), y + (r * 2));
        gc.strokeLine(x + (r * 2), y + (r * 3), x + (r * 5), y + (r * 3));
        gc.strokeLine(x + (r * 2), y + (r * 3.5), x + (r * 5), y + (r * 3.5));
        gc.strokeLine(x + (r * 2), y + (r * 4), x + (r * 5), y + (r * 4));
    }

    /**
     * This function is use for print the Star
     * This function ask the user to enter the coordinates of x and y, and size of the Star
     */
    public void drawStar(GraphicsContext gc) {
        // This code take input from the user for Star
        ArrayList<Integer> values = getInput("Star", gc);
        int r = values.get(0);
        int x = values.get(1);
        int y = values.get(2);
        int c = values.get(3);

        // This code set the selected color for the Star
        gc.setStroke(getColor(c));

        // This code set the position and size of the Star and print on the window
        gc.strokeLine(x + (r * 2.5), y + (r * 1), x + (r * 3), y + (r * 2));
        gc.strokeLine(x + (r * 3), y + (r * 2), x + (r * 4), y + (r * 2.5));
        gc.strokeLine(x + (r * 4), y + (r * 2.5), x + (r * 3), y + (r * 3));
        gc.strokeLine(x + (r * 3), y + (r * 3), x + (r * 2.5), y + (r * 4));
        gc.strokeLine(x + (r * 2.5), y + (r * 4), x + (r * 2), y + (r * 3));
        gc.strokeLine(x + (r * 2), y + (r * 3), x + (r * 1), y + (r * 2.5));
        gc.strokeLine(x + (r * 1), y + (r * 2.5), x + (r * 2), y + (r * 2));
        gc.strokeLine(x + (r * 2), y + (r * 2), x + (r * 2.5), y + (r * 1));
    }

    /**
     * This function is use for print the PlayersSpaceship
     * This function ask the user to enter the coordinates of x and y, and size of the PlayersSpaceship
     */
    public void drawPlayersSpaceship(GraphicsContext gc) {
        // This code take input from the user for PlayersSpaceship
        ArrayList<Integer> values = getInput("PlayersSpaceship", gc);
        int r = values.get(0);
        int x = values.get(1);
        int y = values.get(2);
        int c = values.get(3);

        // This code set the selected color for the PlayersSpaceship
        gc.setStroke(getColor(c));

        // This code set the position and size of the PlayersSpaceship and print on the window
        gc.strokeLine(x + (r * 3), y + (r * 3), x + (r * 4), y + (r * 4));
        gc.strokeLine(x + (r * 4), y + (r * 4), x + (r * 5), y + (r * 1));
        gc.strokeLine(x + (r * 5), y + (r * 1), x + (r * 6), y + (r * 4));
        gc.strokeLine(x + (r * 6), y + (r * 4), x + (r * 7), y + (r * 3));
        gc.strokeLine(x + (r * 7), y + (r * 3), x + (r * 8), y + (r * 4));
        gc.strokeLine(x + (r * 8), y + (r * 4), x + (r * 5), y + (r * 12));
        gc.strokeLine(x + (r * 5), y + (r * 12), x + (r * 2), y + (r * 4));
        gc.strokeLine(x + (r * 2), y + (r * 4), x + (r * 3), y + (r * 3));

    }

    /**
     * This function is use for print the random Small Star
     */
    public void drawSmallStar(GraphicsContext gc) {
        Random random = new Random();
        int x = 0;
        int y = 0;
        int r = 0;
        // Print random 50 star on the window
        for (int i = 0; i < 50; i++) {
            // Calculate random position of x, y and its size
            x = random.nextInt(550);
            y = random.nextInt(550);
            r = random.nextInt(5);
            //This code is generate random colors for the small star
            gc.setStroke(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            // Print the star on the window
            gc.strokeLine(x + (r * 2.5), y + (r * 1), x + (r * 3), y + (r * 2));
            gc.strokeLine(x + (r * 3), y + (r * 2), x + (r * 4), y + (r * 2.5));
            gc.strokeLine(x + (r * 4), y + (r * 2.5), x + (r * 3), y + (r * 3));
            gc.strokeLine(x + (r * 3), y + (r * 3), x + (r * 2.5), y + (r * 4));
            gc.strokeLine(x + (r * 2.5), y + (r * 4), x + (r * 2), y + (r * 3));
            gc.strokeLine(x + (r * 2), y + (r * 3), x + (r * 1), y + (r * 2.5));
            gc.strokeLine(x + (r * 1), y + (r * 2.5), x + (r * 2), y + (r * 2));
            gc.strokeLine(x + (r * 2), y + (r * 2), x + (r * 2.5), y + (r * 1));
        }
    }

    /**
     * This function is use for print the random Planet Star
     */
    public void drawSmallPlanet(GraphicsContext gc) {
        Random random = new Random();
        int x = 0;
        int y = 0;
        int r = 0;
        // Print random 25 Planet on the window
        for (int i = 0; i < 25; i++) {
            // Calculate random position of x, y and its size
            x = random.nextInt(550);
            y = random.nextInt(550);
            r = random.nextInt(10);
            //This code is generate random colors for the small star
            gc.setFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            // Print the Planet on the window
            gc.fillOval(x, y, r, r);
        }
    }

    /**
     * Use this method instead of Thread.sleep(). It handles the possible
     * exception by catching it, because re-throwing it is not an option in this
     * case.
     *
     * @param duration Pause time in milliseconds.
     */
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Exits the app completely when the window is closed. This is necessary to
     * kill the animation thread.
     */
    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * Launches the app
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
