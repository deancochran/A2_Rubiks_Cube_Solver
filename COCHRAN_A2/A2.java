package COCHRAN_A2;

/* Created by Dean Cochran and Noah Feld */

public class A2{
    public static void main(String[] args) {

        /*
        * For each depth k (number of random turns), you will generate 10 randomized cubes and solve each. For each
        * problem, record the number of nodes expanded and the CPU time (or wall time) that it took the solver to find
        * a solution. (The search space of course grows much larger as � increases.) 
    */


    //store the number of nodes expanded upon
    //store the solution sequence
    //store the start state

    Solver ai = new Solver();
        int maxCubes = 10;
        int k = 11;
        
        

        long [] durations = new long [maxCubes];
        int [] nodeExpands = new int [maxCubes];
        String [][] solutions = new String [maxCubes][];
        Cube [] startStates = new Cube [maxCubes];
        // int [] kvals = new int [maxCubes];
        // int [] cubeinstances = new int [maxCubes];

        long [] AvgDurations = new long [k+1];
        long [] avgExpandedNodes = new long [k+1];

        for(int maxDepth=0; maxDepth<=k; maxDepth++){
            //System.out.println( "New Max Limit");
            for(int c=0; c<maxCubes; c++){
                Cube cube = new Cube();
                //radomize cube
                cube.randomize(maxDepth);
                long startTime = System.currentTimeMillis();
                ai.IDAStar(cube); 
                long endTime = System.currentTimeMillis();


                durations[c] = (endTime - startTime);
                nodeExpands[c] = ai.nodesExpanded;
                solutions[c] = ai.solutionSequence;
                startStates[c] = ai.startState;
                // kvals[maxDepth] = maxDepth;
                // cubeinstances[c] = c;
            }

            //compute the mean of durations
            long durationSum = 0;
            for(int el=0; el < durations.length; el++){
                durationSum += durations[el];
            }
            long avgDur = durationSum/durations.length;
            AvgDurations[maxDepth] = avgDur;

            //compute the mean of nodes expanded
            long ExpandedNodeSum = 0;
            for(int el=0; el < nodeExpands.length; el++){
                ExpandedNodeSum += nodeExpands[el];
            }
            long avgExpandedNode = ExpandedNodeSum/durations.length;
            avgExpandedNodes[maxDepth] = avgExpandedNode;
        }

        System.out.println( "\n");
        System.out.println("Solution: ");
        for(int i=0; i<AvgDurations.length;i++){
            System.out.println( "Max Depth: " + i);
            System.out.println( "Duration (ms): " + AvgDurations[i] );
            System.out.println( "Nodes Expanded: " + avgExpandedNodes[i] );
            System.out.println( "________________________________");
        }

        // for (int i = 0; i < k * maxCubes; i++) {
        //     ai.startStates.get(i).display();
        //     System.out.println(ai.solutions.get(i));
        // }






















        // Cube cube = new Cube();
        // boolean playing = true;

        // System.out.print("\n");
        // System.out.print("Welcome to our 2x2 Rubik's Cube Solver \n");
        // System.out.print("\n");
        // System.out.print("Here you will be able to control a virtual 2x2 Cube using simple commands \n");
        // System.out.print("List of possible commands: \n");
        // System.out.print("F , F2, F' \n");
        // System.out.print("L , L2, L' \n");
        // System.out.print("R , R2, R' \n");
        // System.out.print("U , U2, U' \n");
        // System.out.print("B , B2, B' \n");
        // System.out.print("D , D2, D' \n");
        // System.out.print("SHOW , RANDOMIZE , TUTORIAL , QUIT \n");
        // System.out.print("\n");

        // while (playing) {
        //     System.out.print("Turn Command: ");
        //     String ans = System.console().readLine();
        //     System.out.print("\n");
        //     if (ans.equals("F") | ans.equals("F2") | ans.equals("F'")) {
        //         if (ans.equals("F")) {
        //             cube.front(1);
        //         } else if (ans.equals("F2")) {
        //             cube.front(2);
        //         } else {
        //             cube.front(3);
        //         }
        //     } else if (ans.equals("L") | ans.equals("L2") | ans.equals("L'")) {
        //         if (ans.equals("L")) {
        //             cube.left(1);
        //         } else if (ans.equals("L2")) {
        //             cube.left(2);
        //         } else {
        //             cube.left(3);
        //         }
        //     } else if (ans.equals("R") | ans.equals("R2") | ans.equals("R'")) {
        //         if (ans.equals("R")) {
        //             cube.right(1);
        //         } else if (ans.equals("R2")) {
        //             cube.right(2);
        //         } else {
        //             cube.right(3);
        //         }
        //     } else if (ans.equals("U") | ans.equals("U2") | ans.equals("U'")) {
        //         if (ans.equals("U")) {
        //             cube.upper(1);
        //         } else if (ans.equals("U2")) {
        //             cube.upper(2);
        //         } else {
        //             cube.upper(3);
        //         }
        //     } else if (ans.equals("B") | ans.equals("B2") | ans.equals("B'")) {
        //         if (ans.equals("B")) {
        //             cube.back(1);
        //         } else if (ans.equals("B2")) {
        //             cube.back(2);
        //         } else {
        //             cube.back(3);
        //         }
        //     } else if (ans.equals("D") | ans.equals("D2") | ans.equals("D'")) {
        //         if (ans.equals("D")) {
        //             cube.down(1);
        //         } else if (ans.equals("D2")) {
        //             cube.down(2);
        //         } else {
        //             cube.down(3);
        //         }
        //     } else if (ans.equalsIgnoreCase("QUIT")) {
        //         playing = false;
        //     } else if (ans.equalsIgnoreCase("SHOW")) {
        //         cube.display();
        //     } else if (ans.equalsIgnoreCase("RANDOMIZE")) {
        //         System.out.print("How any random turns: ");
        //         String numStr = System.console().readLine();
        //         int rand = Integer.parseInt(numStr);
        //         cube.randomize(rand);
        //     } else if (ans.equalsIgnoreCase("TUTORIAL")) {
        //         cube.tutorial();
        //         System.out.print("\n");
        //         System.out.print("\n");
        //         System.out.print("OK You are ready to take on your first cube. \n");
        //         System.out.print("Check out your cube \n");

        //         cube.display();
        //         System.out.print("Now Solve! or RANDOMIZE if your fleeling ~extreme~ \n");
        //         System.out.print("\n");
        //         System.out.print("\n");

        //     } else {
        //         System.out.print("Re-Enter Command \n");
        //         System.out.print("List of possible commands: \n");
        //         System.out.print("F , F2, F' \n");
        //         System.out.print("L , L2, L' \n");
        //         System.out.print("R , R2, R' \n");
        //         System.out.print("U , U2, U' \n");
        //         System.out.print("B , B2, B' \n");
        //         System.out.print("D , D2, D' \n");
        //         System.out.print("SHOW , RANDOMIZE , TUTORIAL , QUIT \n");
        //         System.out.print("\n");
        //     }
        // }
        // System.out.print("GOOD-BYE THANKS FOR PLAYING \n");
    }
}
