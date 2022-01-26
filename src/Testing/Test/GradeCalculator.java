package Testing.Test;

import java.util.Scanner;

public class GradeCalculator {
    /**
     *  Converts score to letter grade.
     *  @param score percentage score (0-100)
     *  @return  letter grade (e.g. A, B, C, ...)
     */
    public static String getGrade( double score )  {
        if ( score >= 90.0 ) return "A";
        if ( score >= 80.0 ) return "B";
        if ( score >= 70.0 ) return "C";
        if ( score >= 60.0 ) return "D";
        return "F";
    }

    /**
     * Converts letter grade to quality points.
     * @param  grade letter grade (A-F)
     * @return quality points (e.g. 4.0, 3.0, ...)
     */
    public static double getPoints(String grade) {
        if (grade.equalsIgnoreCase("A")) return 4.0;
        if (grade.equalsIgnoreCase("B")) return 3.0;
        if (grade.equalsIgnoreCase("C")) return 2.0;
        if (grade.equalsIgnoreCase("D")) return 1.0;
        else return 0.0;
    }

    public static void getPoints_A() {
        System.out.println("getPoints A: " + (4.0 == getPoints("A")) );
    }
    public static void getPoints_B() {
        System.out.println("getPoints B: " + (3.0 == getPoints("B")) );
    }

    public static void getPoints_all() {
        getPoints_A();
        getPoints_B();
    }

    public static void main(String[] args){
        System.out.println("Enter a score to see the letter grade:");
        Scanner in = new Scanner(System.in);
        double score = in.nextDouble();
        System.out.println("The grade is " + getGrade(score));
        in.close();
    }
}
