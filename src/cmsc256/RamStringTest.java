package cmsc256;

import org.junit.Test;

import static org.junit.Assert.*;

public class RamStringTest {

    @Test
    public void getEveryFifthCharacter() {
        RamString defaultString = new RamString("Rodney, the Ram");
        assertEquals("ehm", defaultString.getEveryFifthCharacter());
    }
    @Test
    public void getEveryFifthCharacter2() {
        RamString randomString = new RamString("This Is A Test String");
        assertEquals("   n", randomString.getEveryFifthCharacter());
    }
    @Test
    public void getEveryFifthCharacter3() {
        RamString spacesString = new RamString("                 "); //17 spaces
        assertEquals("   ", spacesString.getEveryFifthCharacter());
    }
    @Test
    public void getEveryFifthCharacter4() {
        RamString emptyString = new RamString("");
        assertEquals("", emptyString.getEveryFifthCharacter());
    }
    @Test
    public void getEveryFifthCharacter5() {
        RamString shortString = new RamString("Test");
        assertEquals("", shortString.getEveryFifthCharacter());
    }


    @Test
    public void getEvenCharacters() {
        RamString regularString = new RamString("Test String");
        assertEquals("etSrn", regularString.getEvenCharacters());
    }
    @Test
    public void getEvenCharacters2() {
        RamString lotsOfSpaces = new RamString("This is a string with a lot of spaces");
        assertEquals("hsi  tigwt  o fsae", lotsOfSpaces.getEvenCharacters());
    }
    @Test
    public void getEvenCharacters3() {
        RamString onlySpaces = new RamString("             "); //13 spaces
        assertEquals("      ", onlySpaces.getEvenCharacters());
    }
    @Test
    public void getEvenCharacters4() {
        RamString emptyString = new RamString("");
        assertEquals("", emptyString.getEvenCharacters());
    }
    @Test
    public void getEvenCharacters5() {
        RamString lessThanTwo = new RamString("a");
        assertEquals("", lessThanTwo.getEvenCharacters());
    }


    @Test
    public void getOddCharacters() {
        RamString regularString = new RamString("Test String");
        assertEquals("Ts tig", regularString.getOddCharacters());
    }
    @Test
    public void getOddCharacters2() {
        RamString lotsOfSpaces = new RamString("This is a string with a lot of spaces");
        assertEquals("Ti sasrn ihalto pcs", lotsOfSpaces.getOddCharacters());
    }
    @Test
    public void getOddCharacters3() {
        RamString onlySpaces = new RamString("             ");//13 spaces
        assertEquals("       ", onlySpaces.getOddCharacters());
    }
    @Test
    public void getOddCharacters4() {
        RamString emptyString = new RamString("");
        assertEquals("", emptyString.getOddCharacters());
    }
    @Test
    public void getOddCharacters5() {
        RamString lessThanTwo = new RamString("a");
        assertEquals("a", lessThanTwo.getOddCharacters());
    }


    @Test
    public void countDoubleDigits() {
        RamString twoNumbers = new RamString("11 and 12");
        assertEquals(1, twoNumbers.countDoubleDigits());
    }
    @Test
    public void countDoubleDigits2() {
        RamString justNumbers = new RamString("111233456778990");
        assertEquals(3, justNumbers.countDoubleDigits());
    }
    @Test
    public void countDoubleDigits3() {
        RamString sameInARow = new RamString("0000000"); //7 zeros
        assertEquals(0, sameInARow.countDoubleDigits());
    }
    @Test
    public void countDoubleDigits4() {
        RamString spaceSeparatedPairs = new RamString("00 00 00 00");
        assertEquals(4, spaceSeparatedPairs.countDoubleDigits());
    }


    @Test
    public void countVowels() {
        RamString regularString = new RamString("Test String");
        assertEquals(2, regularString.countVowels());
    }
    @Test
    public void countVowels2() {
        RamString noVowels = new RamString("Brr");
        assertEquals(0, noVowels.countVowels());

    }
    @Test
    public void countVowels3() {
        RamString lotsOfVowels = new RamString("This is a string with a lot of vowels");
        assertEquals(10, lotsOfVowels.countVowels());
    }
    @Test
    public void countVowels4() {
        RamString emptyString = new RamString("");
        assertEquals(0, emptyString.countVowels());
    }


    @Test
    public void reformatName() {
        RamString regularString = new RamString("Test String");
        assertEquals("String, Test", regularString.reformatName());
    }
    @Test(expected = UnsupportedOperationException.class)
    public void reformatName2() {
        RamString singleWord = new RamString("Preston");
        assertEquals("", singleWord.reformatName());
    }
    @Test
    public void reformatName3() {
        RamString moreThanTwo = new RamString("Name with more than two words");
        assertEquals("with more than two words, Name", moreThanTwo.reformatName());
    }
    @Test
    public void reformatName4() {
        RamString emptyString = new RamString("   ");
        assertEquals("", emptyString.reformatName());
    }


    @Test
    public void ramifyString() {
        RamString expectedString = new RamString("0 and 00 and 000");
        expectedString.ramifyString();
        assertEquals("Go VCU and CS@VCU and 000", expectedString.getWackyString());
    }
    @Test
    public void ramifyString2() {
        RamString onlyZeros = new RamString("00000");
        onlyZeros.ramifyString();
        assertEquals("00000", onlyZeros.getWackyString());
    }
    @Test
    public void ramifyString3() {
        RamString spaceSeparated = new RamString("0 00 0");
        spaceSeparated.ramifyString();
        assertEquals("Go VCU CS@VCU Go VCU", spaceSeparated.getWackyString());
    }


    @Test
    public void convertDigitsToRomanNumeralsInSubstring() {
        RamString normalString = new RamString("I am 19 years old");
        normalString.convertDigitsToRomanNumeralsInSubstring(1,17);
        assertEquals("I am IIX years old", normalString.getWackyString());
    }
    @Test
    public void convertDigitsToRomanNumeralsInSubstring2() {
        RamString restrictedString = new RamString("I am 19 years old");
        restrictedString.convertDigitsToRomanNumeralsInSubstring(7,17);
        assertEquals("I am 1IX years old", restrictedString.getWackyString());
    }
    @Test
    public void convertDigitsToRomanNumeralsInSubstring3() {
        RamString numberString = new RamString("1234567890");
        numberString.convertDigitsToRomanNumeralsInSubstring(1,10);
        assertEquals("IIIIIIIVVVIVIIVIIIIX0", numberString.getWackyString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void convertDigitsToRomanNumeralsInSubstring4() {
        RamString numberString = new RamString("1234567890");
        numberString.convertDigitsToRomanNumeralsInSubstring(7,5);
        assertEquals("IIIIIIIVVVIVIIVIIIIX0", numberString.getWackyString());
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void convertDigitsToRomanNumeralsInSubstring5() {
        RamString numberString = new RamString("1234567890");
        numberString.convertDigitsToRomanNumeralsInSubstring(1,15);
        assertEquals("IIIIIIIVVVIVIIVIIIIX0", numberString.getWackyString());
    }
    @Test
    public void convertDigitsToRomanNumeralsInSubstring6() {
        RamString numberString = new RamString("1234567890");
        numberString.convertDigitsToRomanNumeralsInSubstring(3,3);
        assertEquals("12III4567890", numberString.getWackyString());
    }


}