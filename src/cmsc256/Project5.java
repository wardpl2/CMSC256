package cmsc256;

import bridges.base.BinTreeElement;
import bridges.connect.Bridges;

import java.util.Stack;

/**
 *  CMSC 256
 *  Project 5
 *  Ward, Preston
 *
 *  Project 5 takes a math equation, puts it into a traversal tree, reads that tree, and evaluates the math equation
 */
public class Project5 {

    public static bridges.base.BinTreeElement<String> buildParseTree(String expression) {
        if (expression == null || expression.length() == 0) {
            throw new IllegalArgumentException("Expression cannot be null.");
        }
        BinTreeElement<String> parseTree = new BinTreeElement<>("root","");
        BinTreeElement<String> current = parseTree;
        Stack<BinTreeElement<String>> treeElements = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token: tokens) {
            switch (token) {
                case "(": //do something
                    current.setLeft(new BinTreeElement<>("temp", ""));
                    treeElements.push(current);
                    current = current.getLeft();
                    break;
                case "+":
                case "-":
                case "/":
                case "*":
                case "%": //do something
                    current.setLabel(token);
                    current.setRight(new BinTreeElement<>("temp", ""));
                    treeElements.push(current);
                    current = current.getRight();
                    break;
                case ")": //do something
                    if (!treeElements.isEmpty()) {
                        current = treeElements.pop();
                    }
                    break;
                default:
                    try {
                        double number = Double.parseDouble(token);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("token must be a number");
                    }
                    current.setLabel(token);
                    if (!treeElements.isEmpty()) {
                        current = treeElements.pop();
                    }
                    break;
            }
        }
        return parseTree;
    }


    public static double evaluate(bridges.base.BinTreeElement<String> tree) {
        double left, right;
        if (tree == null) {
            return Double.NaN;
        }
        if (tree.getLeft() == null && tree.getRight() == null) {
            return Double.parseDouble(tree.getLabel());
        }
        left = evaluate(tree.getLeft());
        right = evaluate(tree.getRight());

        if (tree.getLabel().equals("+")) {
            return left + right;
        }
        else if (tree.getLabel().equals("-")) {
            return left - right;
        }
        else if (tree.getLabel().equals("*")) {
            return left * right;
        }
        else if (tree.getLabel().equals("/")) {
            if (right == 0) {
                throw new ArithmeticException("cannot devide by 0");
            }
            return left / right;
        }

        return Double.NaN;
    }


    public static String getEquation(bridges.base.BinTreeElement<String> tree) {
        String returnString = "";
        if (tree.getLeft() == null && tree.getRight() == null) {
            return tree.getLabel();
        }
        returnString += "( ";
        returnString += getEquation(tree.getLeft());
        returnString += " " + tree.getLabel() + " ";
        returnString += getEquation(tree.getRight());
        returnString += " )";

        return returnString;
    }

    public static void main(String[] args){
        String ex1 = "( ( 7 + 3 ) * ( 5 - 2 ) )";
        BinTreeElement<String> parseTree1 = buildParseTree(ex1);
        double answer1 = evaluate(parseTree1);
        System.out.println(answer1);
        System.out.println(getEquation(parseTree1));

        String ex2 = "( ( 10 + 5 ) * 3 )";
        BinTreeElement<String>  parseTree2 = buildParseTree(ex2);
        double answer2 = evaluate(parseTree2);
        System.out.println(answer2);
        System.out.println(getEquation(parseTree2));

        String ex3 = "( ( ( ( ( 2 * 12 ) / 6 ) + 3 ) - 17 ) + ( 2 * 0 ) )";
        BinTreeElement<String>  parseTree3 = buildParseTree(ex3);
        double answer3 = evaluate(parseTree3);
        System.out.println(answer3);
        System.out.println(getEquation(parseTree3));

        String ex4 = "( 3 + ( 4 * 5 ) )";
        BinTreeElement<String>  parseTree4 = buildParseTree(ex4);
        double answer4 = evaluate(parseTree4);
        System.out.println(answer4);
        System.out.println(getEquation(parseTree4));

        /* Initialize a Bridges connection */
        Bridges bridges = new Bridges(5,"wardpl2","133617755398");

        /* Set an assignment title */
        bridges.setTitle("Arithmetic Parse Tree Project - Debra Duke");
        bridges.setDescription("CMSC 256, Spring 2022");

        try {
            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree1);
            /* Visualize the Array */
            bridges.visualize();

            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree2);
            /* Visualize the Array */
            bridges.visualize();

            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree3);
            /* Visualize the Array */
            bridges.visualize();

            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree4);
            /* Visualize the Array */
            bridges.visualize();
        }
        catch(Exception ex){
            System.out.println("Error connecting to Bridges server.");
        }
    }
}

//move to the new node
//treeElements.push(current)
//current = current.goLeft()

//return to the parent
//current = treeElements.pop()

