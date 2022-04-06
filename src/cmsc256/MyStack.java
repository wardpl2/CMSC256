package cmsc256;

import bridges.base.SLelement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.EmptyStackException;

/**
 * CMSC 256
 * Project 4
 * Ward, Preston
 *
 * Project 4 reads an HTML file and checks if the tags are balanced or not
 */
public class MyStack<E> implements StackInterface<E> {
    private SLelement<E> top;
    private int size;

    public MyStack() {
        clear();
    }

    @Override
    public void push(E newEntry) {
        if (newEntry == null) {
            throw new IllegalArgumentException("New entry cannot be null.");
        }
        top = new SLelement<E>(newEntry,top);
        size++;
    }

    @Override
    public E pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        E poppedElement = top.getValue();
        top = top.getNext();
        size--;
        return poppedElement;
    }

    @Override
    public E peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.getValue();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }



    public static boolean isBalanced(File webpage) throws FileNotFoundException {

        MyStack<String> myStack = new MyStack<>();

        Scanner read = new Scanner(webpage);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> justTags = new ArrayList<>();

        while (read.hasNextLine()) {
            list.add(read.nextLine().trim());
        }

        for (String S : list) {
            //fancy way of splitting the strings to grab everything inside the brackets, including the brackets
            String[] temp = S.split("(?=<)|(?<=>)"); //https://stackoverflow.com/questions/48999125/splitting-a-string-based-on-angle-brackets
            for (String s : temp) {
                if (s.contains("<")) {
                    justTags.add(s);
                }
            }
        }


        System.out.println(justTags);

        String top = "";
        for (String s : justTags) {
            if (!(s.contains("/"))) {
                myStack.push(s);
            } else {
                if (!myStack.isEmpty()) {
                    top = myStack.pop();
                }
                String temp = s.replace("/","");
                if (!top.equals(temp)) {
                    return false;
                }
            }
        }

        if (!myStack.isEmpty()) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        try{
            System.out.println(isBalanced(new File("2badeasypage.html")));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
