package COCHRAN_A2;

import java.util.HashMap;
import java.util.Random;


/*
 * This class creates a 2x2 Rubik's cube with predetermined sides and colors.
 * The cube is capable of turning every side for any predetermined amount of
 * times
 */
public class Cube {

  // public attributes
  // This is the string array that stores all of the sticker strings.
  // Each element in the matrix represents a string color
  String[][] matrix;

  // This is a dictionary of colors which each face index represents a color
  // string
  HashMap<Integer, String> color_dict;
  // This is a dictionary of colors which each face index represents a face string
  HashMap<Integer, String> face_dict;

  // private attributes

  // These "turn dictionarys" which store the locations of the 8 different
  // stickers which would be turned given a specified faceof the cube.
  private HashMap<String, Integer> frontturn_dict;
  private HashMap<String, Integer> leftturn_dict;
  private HashMap<String, Integer> rightturn_dict;
  private HashMap<String, Integer> upperturn_dict;
  private HashMap<String, Integer> downturn_dict;
  private HashMap<String, Integer> backturn_dict;

  /*
   * The cube stores this initial state information input from a color/face
   * HaspMap into a String arrary matrix The cube also initalizes
   * "turn dictionarys" which store the locations of the 8 different stickers
   * which would be turned given a specified faceof the cube. Once the cube is
   * initialized the start state of the cube is displayed.
   */
  public Cube() {
    //System.out.print("Hello World! Welcome to the 2x2 Rubik's Cube Solver \n");
    //System.out.print("Initlaizing your cube....... \n");

    // Initializes String matrix to hold every stickers color
    matrix = new String[6][4];

    // Initializes dictionary of colors which each face index represents a color
    // string
    color_dict = new HashMap<Integer, String>();
    color_dict.put(0, "B");
    color_dict.put(1, "R");
    color_dict.put(2, "O");
    color_dict.put(3, "W");
    color_dict.put(4, "G");
    color_dict.put(5, "Y");
    // Initializes dictionary of faces which each face index represents a face
    // string
    face_dict = new HashMap<Integer, String>();
    face_dict.put(0, "Front");
    face_dict.put(1, "Left");
    face_dict.put(2, "Right");
    face_dict.put(3, "Upper");
    face_dict.put(4, "Back");
    face_dict.put(5, "Down");

    // Initializes each of the the matrix elements to hold their specified start
    // state sticker color
    for (int face = 0; face < matrix.length; face++) {
      for (int square = 0; square < matrix[0].length; square++) {
        String color = color_dict.get(face);
        matrix[face][square] = color;
      }
    }

    /*
     * Orientation of cube at start...
     * 
     * 3 0W 1W 2W 3W
     * 
     * 1 0R 1R 0 0B 1B 2 0O 1O 4 0G 1G 2R 3R 2B 3B 2O 3O 2G 3G
     * 
     * 5 0Y 1Y 2Y 3Y
     * 
     */

    // Initializes a turn dictionary of side stickers that would be affect if the
    // front(0) face was turned
    frontturn_dict = new HashMap<String, Integer>();
    frontturn_dict.put("Left", 1);
    frontturn_dict.put("L1", 3);
    frontturn_dict.put("L2", 1);

    frontturn_dict.put("Right", 2);
    frontturn_dict.put("R1", 0);
    frontturn_dict.put("R2", 2);

    frontturn_dict.put("Upper", 3);
    frontturn_dict.put("U1", 2);
    frontturn_dict.put("U2", 3);

    frontturn_dict.put("Down", 5);
    frontturn_dict.put("D1", 1);
    frontturn_dict.put("D2", 0);

    // Initializes a turn dictionary of side stickers that would be affect if the
    // Left(1) face was turned
    leftturn_dict = new HashMap<String, Integer>();
    leftturn_dict.put("Left", 4);
    leftturn_dict.put("L1", 3);
    leftturn_dict.put("L2", 1);

    leftturn_dict.put("Right", 0);
    leftturn_dict.put("R1", 0);
    leftturn_dict.put("R2", 2);

    leftturn_dict.put("Upper", 3);
    leftturn_dict.put("U1", 0);
    leftturn_dict.put("U2", 2);

    leftturn_dict.put("Down", 5);
    leftturn_dict.put("D1", 0);
    leftturn_dict.put("D2", 2);

    // Initializes a turn dictionary of side stickers that would be affect if the
    // Right(2) face was turned
    rightturn_dict = new HashMap<String, Integer>();
    rightturn_dict.put("Left", 0);
    rightturn_dict.put("L1", 3);
    rightturn_dict.put("L2", 1);

    rightturn_dict.put("Right", 4);
    rightturn_dict.put("R1", 0);
    rightturn_dict.put("R2", 2);

    rightturn_dict.put("Upper", 3);
    rightturn_dict.put("U1", 3);
    rightturn_dict.put("U2", 1);

    rightturn_dict.put("Down", 5);
    rightturn_dict.put("D1", 3);
    rightturn_dict.put("D2", 1);

    // Initializes a turn dictionary of side stickers that would be affect if the
    // Upper(3) face was turned
    upperturn_dict = new HashMap<String, Integer>();
    upperturn_dict.put("Left", 1);
    upperturn_dict.put("L1", 1);
    upperturn_dict.put("L2", 0);

    upperturn_dict.put("Right", 2);
    upperturn_dict.put("R1", 1);
    upperturn_dict.put("R2", 0);

    upperturn_dict.put("Upper", 4);
    upperturn_dict.put("U1", 1);
    upperturn_dict.put("U2", 0);

    upperturn_dict.put("Down", 0);
    upperturn_dict.put("D1", 1);
    upperturn_dict.put("D2", 0);

    // Initializes a turn dictionary of side stickers that would be affect if the
    // Back(4) face was turned
    backturn_dict = new HashMap<String, Integer>();
    backturn_dict.put("Left", 2);
    backturn_dict.put("L1", 3);
    backturn_dict.put("L2", 1);

    backturn_dict.put("Right", 1);
    backturn_dict.put("R1", 0);
    backturn_dict.put("R2", 2);

    backturn_dict.put("Upper", 3);
    backturn_dict.put("U1", 1);
    backturn_dict.put("U2", 0);

    backturn_dict.put("Down", 5);
    backturn_dict.put("D1", 2);
    backturn_dict.put("D2", 3);

    // Initializes a turn dictionary of side stickers that would be affect if the
    // Down(5) face was turned
    downturn_dict = new HashMap<String, Integer>();
    downturn_dict.put("Left", 1);
    downturn_dict.put("L1", 2);
    downturn_dict.put("L2", 3);

    downturn_dict.put("Right", 2);
    downturn_dict.put("R1", 2);
    downturn_dict.put("R2", 3);

    downturn_dict.put("Upper", 0);
    downturn_dict.put("U1", 2);
    downturn_dict.put("U2", 3);

    downturn_dict.put("Down", 4);
    downturn_dict.put("D1", 2);
    downturn_dict.put("D2", 3);

    // Call to display all matrix elements to terminal
    //display();
  }

  // A method to turn the cube's front(0) face utilizes the frontturn_dict for a
  // specificed num_turn times
  public void front(int num_turns) {
    int face = 0;
    //System.out.print("Turning the " + face_dict.get(face) + " Face " + num_turns + " Times \n");
    turn(face, frontturn_dict, num_turns);

  }

  // A method to turn the cube's left(1) face utilizes the leftturn_dict for a
  // specificed num_turn times
  public void left(int num_turns) {
    int face = 1;
    //System.out.print("Turning the " + face_dict.get(face) + " Face " + num_turns + " Times \n");
    turn(face, leftturn_dict, num_turns);
  }

  // A method to turn the cube's right(2) face utilizes the rightturn_dict for a
  // specificed num_turn times
  public void right(int num_turns) {
    int face = 2;
    //System.out.print("Turning the " + face_dict.get(face) + " Face " + num_turns + " Times \n");
    turn(face, rightturn_dict, num_turns);
  }

  // A method to turn the cube's upper(3) face utilizes the upperturn_dict for a
  // specificed num_turn times
  public void upper(int num_turns) {
    int face = 3;
    //System.out.print("Turning the " + face_dict.get(face) + " Face " + num_turns + " Times \n");
    turn(face, upperturn_dict, num_turns);
  }

  // A method to turn the cube's back(4) face utilizes the backturn_dict for a
  // specificed num_turn times
  public void back(int num_turns) {
    int face = 4;
    //System.out.print("Turning the " + face_dict.get(face) + " Face " + num_turns + " Times \n");
    turn(face, backturn_dict, num_turns);
  }

  // A method to turn the cube's down(5) face utilizes the downturn_dict for a
  // specificed num_turn times
  public void down(int num_turns) {
    int face = 5;
    //System.out.print("Turning the " + face_dict.get(face) + " Face " + num_turns + " Times \n");
    turn(face, downturn_dict, num_turns);
  }

  // A method to randomize the cubes elements by using the cubes turn methods
  // until scrambled
  public void randomize(int num_turns) {
    //System.out.println( "New Randomizer sequence" + "\n");
    Random rand = new Random();
    //System.out.print("Printing out Randomizer sequence \n");
    for (int i = 0; i < num_turns; i++) {
      int turn_val = rand.nextInt(6);
      int which_way = rand.nextInt(3) + 1; // number of turns on selected face
      // randomly select cube.turn methods until for loop ends
      if (turn_val == 0) {
        this.front(which_way);
        //System.out.println( "front " +  which_way + "\n");
      } else if (turn_val == 1) {
        this.left(which_way);
        //System.out.println( "left " +  which_way + "\n");
      } else if (turn_val == 2) {
        this.right(which_way);
        //System.out.println( "right " +  which_way + "\n");
      } else if (turn_val == 3) {
        this.upper(which_way);
        //System.out.println( "upper " +  which_way + "\n");
      } else if (turn_val == 4) {
        this.back(which_way);
        //System.out.println( "back " +  which_way + "\n");
      } else if (turn_val == 5) {
        this.down(which_way);
        //System.out.println( "down " +  which_way + "\n");
      }
    }
    // if (num_turns >= 20) {
    //   System.out.print("Randomizer finish, cube is scrabbled like eggs \n");
    // } else if (num_turns < 5) {
    //   System.out.print("Randomizer finish, cube is barely scrabbled \n");
    // } else {
    //   System.out.print("Randomizer finish, cube is scrabbled \n");
    // }

    //display();
  }

  // A method to display the matrix to the terminal in a way the user will
  // understand
  public void display() {
    // for every face of the matrix
    for (int i = 0; i < matrix.length; i++) {
      // print the side that will be displayed
      System.out.println("\n");
      System.out.print(face_dict.get(i));
      System.out.print("\n");

      // for every element in the face
      for (int j = 0; j < matrix[0].length; j++) {
        // display the coordinate location
        System.out.print("(" + i + "," + j + ")");
        // display the color of the element
        System.out.print(matrix[i][j] + " ");
        // if the first two elements in the face have been displayed, start call a line
        // separator
        if (j == 1) {
          // This allows the user to visually compare a physicalrubik's cube to the
          // terminal GUI cube
          System.out.print("\n");
        }
      }
      System.out.print("\n");
    }
  }

  // A method that will verify that for every element in a given face is the same
  // color, for all the faces of the ciube
  public boolean isSolved() {

    boolean flag = true;
    // for every face of the matrix
    for (int i = 0; i < matrix.length; i++) {
      // for every element in the face
      for (int j = 0; j < matrix[0].length; j++) {
        if (!matrix[i][j].equals(matrix[i][0])) {
          flag = false;
        }
      }
    }
    // if (flag) {
    //   System.out.print("\n");
    // } else {
    //   System.out.print("CONGRATS YOU HAVE SOLVED THE RUBIKS CUBE \n");
    // }

    return flag;
  }

  // A method that will return a DEEP COPY of the cube
  public Cube clone() {
    // create a new deep copy of THIS current cube state

    Cube copy = new Cube();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 4; j++) {
        // The sticker variable should allow
        // us to avoid copying by reference.
        //String sticker = cube.matrix[i][j];
        String sticker = this.matrix[i][j];
        copy.matrix[i][j] = sticker;
      }
    }

    return copy;
  }

  // A method that will turn the specificed face and the correlating sides of the
  // face for a specificed amount of times.
  // Does not handle negative turns (inverse/counter clockwise)
  public void turn(int face, HashMap<String, Integer> dict, int num_turns) {

    // now implement turning for negative num_turn values

    // for the amount of specified num_turns turns
    for (int i = 0; i < num_turns; i++) {

      /*
       * Rotate the elements matrix position clockwise for every element ON THE FACE
       * of the cube being turned. Imagine the front face being turned after the cube
       * has been scrambled...
       * 
       * Before Turn: After Turn:
       * 
       * Front(0) Front(0) 0R 1B 0W 1R 2W 3O --> 2O 3B
       * 
       */
      String temp0 = matrix[face][0].toString();
      matrix[face][0] = matrix[face][2].toString();
      matrix[face][2] = matrix[face][3].toString();
      matrix[face][3] = matrix[face][1].toString();
      matrix[face][1] = temp0;

      /*
       * 
       * Rotate the elements matrix position clockwise for every element ON THE SIDES
       * OF THE FACE of the cube being turned.
       * 
       * Orientation of cube at start...
       * 
       * Upper(3) 0W 1W 2W 3W
       * 
       * Left(1) 0R 1R Front(0) 0B 1B Right(2) 0O 1O Back(4) 0G 1G 2R 3R 2B 3B 2O 3O
       * 2G 3G
       * 
       * Down(5) 0Y 1Y 2Y 3Y
       * 
       * 
       * Orientation of cube after... turn(0, frontturn_dict, 1) -> turns front face
       * of cube one time
       * 
       * Upper(3) 0W 1W 2R 3R
       * 
       * Left(1) 0R 1Y Front(0) 0B 1B Right(2) 0W 1O Back(4) 0G 1G 2R 3Y 2B 3B 2W 3O
       * 2G 3G
       * 
       * Down(5) 0O 1O 2Y 3Y
       * 
       */
      String temp1 = matrix[dict.get("Upper")][dict.get("U1")].toString();
      String temp2 = matrix[dict.get("Upper")][dict.get("U2")].toString();

      matrix[dict.get("Upper")][dict.get("U1")] = matrix[dict.get("Left")][dict.get("L1")].toString();
      matrix[dict.get("Upper")][dict.get("U2")] = matrix[dict.get("Left")][dict.get("L2")].toString();

      matrix[dict.get("Left")][dict.get("L1")] = matrix[dict.get("Down")][dict.get("D1")].toString();
      matrix[dict.get("Left")][dict.get("L2")] = matrix[dict.get("Down")][dict.get("D2")].toString();

      matrix[dict.get("Down")][dict.get("D1")] = matrix[dict.get("Right")][dict.get("R1")].toString();
      matrix[dict.get("Down")][dict.get("D2")] = matrix[dict.get("Right")][dict.get("R2")].toString();

      matrix[dict.get("Right")][dict.get("R1")] = temp1;
      matrix[dict.get("Right")][dict.get("R2")] = temp2;

      isSolved();
    }
  }

  public void tutorial() {
    Cube copy = this.clone();
    System.out.println("New To Cubing?????... \n");
    System.out.println("Welcome to a digital 2x2 Cube Interface... \n");
    System.out.println("This interface is all about solving the Cube.");
    System.out.println("For Example... \n");
    System.out.println("Here is a solved 2x2 Cube... (This is what you want your output to look like)  \n");
    copy.display();

    System.out.println(
        "You can use any of the provided to commands to turn the sides, randomize, or even display your cube. \n");

    System.out.println("The Output for Command: F \n");
    copy.front(1);
    copy.display();
    System.out.println("The Output for Command: F' \n");
    copy.front(3);
    copy.display();
    System.out.println("The Output for Command: RANDOMIZE \n");
    System.out.println("How many random turns: 20 \n");
    copy.randomize(20);

  }
}
