package COCHRAN_A2;

import java.util.HashMap;
import java.util.ArrayList;

/*
* The Solver class is meant to be established such that the cube can be solved with Artificial Intellegence
* The Solver is inherently capable of computing a Manhattan Distance Metric
*/
public class Solver {


    int nodesExpanded;
    Cube startState;
    String [] solutionSequence;

    boolean isSolved;
    HashMap<Integer, String> solvedStateColors;
    HashMap<String, String> oppositeColors;

    ArrayList<Cube> startStates;
    ArrayList<String> solutions;
    ArrayList<Integer> nodesExpandedList;

    // The constructor instantiates necessary dictionaries and array lists and creates
    // a HashMap of
    // Opposite side colors which will be useful to compute the goal state
    public Solver() {
        this.oppositeColors = new HashMap<String, String>();
        this.oppositeColors.put("G", "B");
        this.oppositeColors.put("B", "G");
        this.oppositeColors.put("W", "Y");
        this.oppositeColors.put("Y", "W");
        this.oppositeColors.put("R", "O");
        this.oppositeColors.put("O", "R");

        startStates = new ArrayList<Cube>();
        solutions = new ArrayList<String>();
        nodesExpandedList = new ArrayList<Integer>();
    }

    /*
    * Given a cube, finds the optimal solution via IDA* with a Manhattan-based
    * heuristic.
    */
    public void IDAStar(Cube cube) {
        //System.out.println( "IDAStar call \n");
        setSolvedStateColors(cube);
        //given an unsolved cube
        isSolved = false;
        //set nodes expanded upon to 0
        nodesExpanded = 0;
        //create a startstate node
        Node current = new Node(cube, 0);
        //use IDA* algortihm to solve cube
        for (int maxDepth = 0; maxDepth <= 11; maxDepth++) {
            solve(current, maxDepth);
        }

        this.solvedStateColors.clear();
    }

    /*
    * For the given cube state, returns its Manhattan distance. The Manhattan
    * distance is the sum of each piece's distance from its eventual solved state
    * divided by four.
    */
    public double manhattanDistance(Cube cube) {
        double sum = 0.0;
        // for each cubelet (three connected stickers),
        // compute the number of Upper/Right/Front turns needed
        // to move the cubelet to the correct position.
        // We can do this by counting the amount of its stickers
        // that are already on the correct face (relative to the
        // fixed cubelet, DBL). Then, divide the sum by 4, as 4
        // cubelets are changed with every turn of the cube.
        sum += countStickers(cube, 3, 0, 1, 0, 4, 1); // UBL
        sum += countStickers(cube, 3, 1, 2, 1, 4, 0); // UBR
        sum += countStickers(cube, 3, 3, 0, 1, 2, 0); // UFR
        sum += countStickers(cube, 3, 2, 1, 1, 0, 0); // UFL
        sum += countStickers(cube, 1, 3, 0, 2, 5, 0); // DFL
        sum += countStickers(cube, 0, 3, 2, 2, 5, 1); // DFR
        sum += countStickers(cube, 2, 0, 4, 2, 5, 3); // DBR
        return sum / 4;
    };

    private int countStickers(Cube cube, int a, int b, int c, int d, int e, int f) {
        /* A private method to be used only by manhattanDistance(). Its parameters are a cube
       and three integers representing the locations of three stickers of one cubelet on
       the cube's matrix of stickers. It returns the Manhattan distance of that cubelet
       by counting the number of stickers that are on the correct face. */

        if (cube.matrix[a][b] != solvedStateColors.get(a) && cube.matrix[c][d] != solvedStateColors.get(c)
                && cube.matrix[e][f] != solvedStateColors.get(e)) {
            return 2;
        } else if (cube.matrix[a][b] != solvedStateColors.get(a) || cube.matrix[c][d] != solvedStateColors.get(c)
                || cube.matrix[e][f] != solvedStateColors.get(e)) {
            return 1;
        }
        return 0;
    }

    private void setSolvedStateColors(Cube cube) {
        /*
         * Given a cube state, set the Solver's solvedStateColors HashMap based upon the
         * DBL piece. This HashMap will be used for computing the Manhattan distance, so
         * the algorithm knows what every face's color will be once the cube is solved.
         */
        solvedStateColors = new HashMap<Integer, String>();
        /* compute based on DBL piece */

        solvedStateColors.put(1, cube.matrix[1][2]); // Left
        solvedStateColors.put(4, cube.matrix[4][3]); // Back
        solvedStateColors.put(5, cube.matrix[5][2]); // Down

        solvedStateColors.put(2, oppositeColors.get(solvedStateColors.get(1))); // Right
        solvedStateColors.put(0, oppositeColors.get(solvedStateColors.get(4))); // Front
        solvedStateColors.put(3, oppositeColors.get(solvedStateColors.get(5))); // Up
    }

    private double evaluate(Node node){
        /* Returns the evaluation function f(n) of a node via A*. Simply the node's depth, g(n), 
           plus its heuristic, h(n).*/
        double g = node.g;
        double h = manhattanDistance(node.cubeState);
        return g+h;
    }



    private void solve(Node node, int maxDepth){
        /* A recursive function that takes a node and a depth threshold.
           If it is not solved, it creates six child nodes, each based
           upon a meaningful turn, and recurses upon each node if its
           evaluation function returns a value less than or equal to
           the depth threshold. */

        //increment nodes expanded upon
        nodesExpanded++;
        //as long as the cube sttribute solve is false
        if (!isSolved){
            //if current node state is equal to goalstate
            if(node.cubeState.isSolved()){
                // System.out.println("Supposedly solved cube:");
                // node.cubeState.display();
                // System.out.println( "found node with goal state");
                //set isSolved attribute to true to stop this recursive function
                this.isSolved=true;
                //call the ifSolved(node) function, which takes the ndoe with a goal cubeState 
                this.ifSolved(node);          
            }

            ArrayList<Node> possibleNodes = new ArrayList<Node>();

            if (node.previousMove.charAt(0) != 'R'){

                Cube tempCubeState0 = node.cubeState.clone();
                tempCubeState0.right(1);
                Node temp0 = new Node(tempCubeState0, "R" , node.g+1, node);


                Cube tempCubeState2 = node.cubeState.clone();
                tempCubeState2.right(2);
                Node temp1 = new Node(tempCubeState2, "R2" , node.g+1, node);


                Cube tempCubeState3 = node.cubeState.clone();
                tempCubeState3.right(3);
                Node temp2 = new Node(tempCubeState3, "R'" , node.g+1, node);

                possibleNodes.add(temp0);
                possibleNodes.add(temp1);
                possibleNodes.add(temp2);

            }
            if (node.previousMove.charAt(0) != 'U'){

                Cube tempCubeState0 = node.cubeState.clone();
                tempCubeState0.upper(1);
                Node temp0 = new Node(tempCubeState0, "U" , node.g+1, node);


                Cube tempCubeState2 = node.cubeState.clone();
                tempCubeState2.upper(2);
                Node temp1 = new Node(tempCubeState2, "U2" , node.g+1, node);


                Cube tempCubeState3 = node.cubeState.clone();
                tempCubeState3.upper(3);
                Node temp2 = new Node(tempCubeState3, "U'" , node.g+1, node);


                possibleNodes.add(temp0);
                possibleNodes.add(temp1);
                possibleNodes.add(temp2);
            }
            if (node.previousMove.charAt(0) != 'F'){

                Cube tempCubeState0 = node.cubeState.clone();
                tempCubeState0.front(1);
                Node temp0 = new Node(tempCubeState0, "F" , node.g+1, node);


                Cube tempCubeState2 = node.cubeState.clone();
                tempCubeState2.front(2);
                Node temp1 = new Node(tempCubeState2, "F2" , node.g+1, node);


                Cube tempCubeState3 = node.cubeState.clone();
                tempCubeState3.front(3);
                Node temp2 = new Node(tempCubeState3, "F'" , node.g+1, node);


                possibleNodes.add(temp0);
                possibleNodes.add(temp1);
                possibleNodes.add(temp2);
            }


            for (int i=0; i<possibleNodes.size();i++){
                if(this.evaluate(possibleNodes.get(i)) <= maxDepth){
                    this.solve(possibleNodes.get(i), maxDepth);
                }
            }
    
        }
    }

    private void ifSolved(Node current){
        /* To be called by solve() when a solved state is reached.
           Records information regarding the solution sequence,
           start state, and number of nodes expanded. */

        //System.out.println( "if solved call ");
        //initialize a new startState
        this.startState = new Cube();
        //initialize a new solution squence
        this.solutionSequence = new String [current.g];
        Node temp = current;
        for(int i=current.g; i>0; i--){
            this.solutionSequence[i-1]=temp.previousMove;
            temp = temp.parent;
        }
        this.startState = temp.cubeState;
        String solutionString = "";
        for (int i = 0; i < solutionSequence.length; i++) {
            solutionString += " " + solutionSequence[i];
        }

        startStates.add(startState);
        nodesExpandedList.add(nodesExpanded);
        solutions.add(solutionString.trim());


        // System.out.println( "Nodes Expanded: "+this.nodesExpanded + "\n");
        // System.out.println( "Start State: "+ this.startState.matrix + "\n");
        // System.out.println( "Solution Sequence: ");
        // for(int i=0; i<this.solutionSequence.length;i++){
        //     System.out.println(" "+this.solutionSequence[i] + " ");
        // }
        // System.out.println("\n");
    }
}
