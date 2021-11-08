package COCHRAN_A2;

/*
* The Node class is inherently an object class which holds data
* Each node holds a cube
* the previous Move made on the cube
* a int of how many moves have been made since the start node
* a Parent Node
* 
*/
public class Node {

    Cube cubeState;
    String previousMove;
    int g;
    Node parent;

    // Here we have the default constructor for the start Node
    public Node(Cube cState, int gN) {
        this.cubeState = cState;
        this.g = gN;

        this.parent = null;
        this.previousMove = " ";

    }

    // Here we have the constructor for the Node, which takes the arguments and stores them inside its parameters.
    public Node(Cube cState, String pMove, int gN, Node p) {
        this.cubeState = cState;
        this.previousMove = pMove;
        this.g = gN;
        this.parent=p;
    }

}
