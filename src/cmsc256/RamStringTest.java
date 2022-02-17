package cmsc256;

import org.junit.Test;

import static org.junit.Assert.*;

public class RamStringTest {

    @Test
    public void getEveryFifthCharacter() {
        RamString defaultString = new RamString("Rodney, the Ram");
//        String everyFifth = defaultString.getEveryFifthCharacter();
        assertTrue("ehm".equals(defaultString.getEveryFifthCharacter()));
    }
    @Test
    public void getEveryFifthCharacter2() {
        RamString randomString = new RamString("This Is A Test String");
//        String everyFifth = randomString.getEveryFifthCharacter();
        assertTrue("   n".equals(randomString.getEveryFifthCharacter()));
    }
    @Test
    public void getEveryFifthCharacter3() {
        RamString spacesString = new RamString("                 "); //17 spaces
//        String everyFifth = spacesString.getEveryFifthCharacter();
        assertTrue("   ".equals(spacesString.getEveryFifthCharacter()));
    }
    @Test
    public void getEveryFifthCharacter4() {
        RamString emptyString = new RamString("");
//        String everyFifth = emptyString.getEveryFifthCharacter();
        assertTrue("".equals(emptyString.getEveryFifthCharacter()));
    }
    @Test
    public void getEveryFifthCharacter5() {
        RamString shortString = new RamString("Test");
//        String everyFifth = shortString.getEveryFifthCharacter();
        assertTrue("".equals(shortString.getEveryFifthCharacter()));
    }


    @Test
    public void getEvenCharacters() {
        RamString regularString = new RamString("Test String");
//        String evenString = regularString.getEvenCharacters();
        assertTrue("etSrn".equals(regularString.getEvenCharacters()));
    }
    @Test
    public void getEvenCharacters2() {
        RamString lotsOfSpaces = new RamString("This is a string with a lot of spaces");
//        String evenString = lotsOfSpaces.getEvenCharacters();
        assertTrue("hsi  tigwt  o fsae".equals(lotsOfSpaces.getEvenCharacters()));
    }
    @Test
    public void getEvenCharacters3() {
        RamString onlySpaces = new RamString("             "); //13 spaces
//        String evenString = onlySpaces.getEvenCharacters();
        assertTrue("      ".equals(onlySpaces.getEvenCharacters()));
    }
    @Test
    public void getEvenCharacters4() {
        RamString emptyString = new RamString("");
//        String evenString = emptyString.getEvenCharacters();
        assertTrue("".equals(emptyString.getEvenCharacters()));
    }
    @Test
    public void getEvenCharacters5() {
        RamString lessThanTwo = new RamString("a");
//        String evenString = lessThanTwo.getEvenCharacters();
        assertTrue("".equals(lessThanTwo.getEvenCharacters()));
    }


    @Test
    public void getOddCharacters() {
        RamString regularString = new RamString("Test String");
        assertTrue("Ts tig".equals(regularString.getOddCharacters()));
    }
    @Test
    public void getOddCharacters2() {
        RamString lotsOfSpaces = new RamString("This is a string with a lot of spaces");
        assertTrue("Ti sasrn ihalto pcs".equals(lotsOfSpaces.getOddCharacters()));
    }
    @Test
    public void getOddCharacters3() {
        RamString onlySpaces = new RamString("             ");//13 spaces
        assertTrue("       ".equals(onlySpaces.getOddCharacters()));
    }
    @Test
    public void getOddCharacters4() {
        RamString emptyString = new RamString("");
        assertTrue("".equals(emptyString.getOddCharacters()));
    }
    @Test
    public void getOddCharacters5() {
        RamString lessThanTwo = new RamString("a");
        assertTrue("a".equals(lessThanTwo.getOddCharacters()));
    }


    @Test
    public void countDoubleDigits() {
        RamString twoNumbers = new RamString("11 and 12");
//        int numDoubles = twoNumbers.countDoubleDigits();
        assertTrue(1 == twoNumbers.countDoubleDigits());
    }
    @Test
    public void countDoubleDigits2() {
        RamString justNumbers = new RamString("111233456778990");
//        int numDoubles = justNumbers.countDoubleDigits();
        assertTrue(3 == justNumbers.countDoubleDigits());
    }
    @Test
    public void countDoubleDigits3() {
        RamString sameInARow = new RamString("0000000"); //7 zeros
//        int numDoubles = sameInARow.countDoubleDigits();
        assertTrue(0 == sameInARow.countDoubleDigits());
    }
    @Test
    public void countDoubleDigits4() {
        RamString spaceSeparatedPairs = new RamString("00 00 00 00");
//        int numDoubles = spaceSeparatedPairs.countDoubleDigits();
        assertTrue(4 == spaceSeparatedPairs.countDoubleDigits());
    }


    @Test
    public void countVowels() {
        RamString regularString = new RamString("Test String");
        assertTrue(2 == regularString.countVowels());
    }
    @Test
    public void countVowels2() {
        RamString noVowels = new RamString("Brr");
        assertTrue(0 == noVowels.countVowels());

    }
    @Test
    public void countVowels3() {
        RamString lotsOfVowels = new RamString("This is a string with a lot of vowels");
        assertTrue(10 == lotsOfVowels.countVowels());
    }
    @Test
    public void countVowels4() {
        RamString emptyString = new RamString("");
        assertTrue(0 == emptyString.countVowels());
    }


    @Test
    public void reformatName() {
        RamString regularString = new RamString("Test String");
        assertTrue("String, Test".equals(regularString.reformatName()));
    }
    @Test(expected = UnsupportedOperationException.class)
    public void reformatName2() {
        RamString singleWord = new RamString("Preston");
        assertTrue("".equals(singleWord.reformatName()));
    }
    @Test
    public void reformatName3() {
        RamString moreThanTwo = new RamString("Name with more than two words");
        assertTrue("with more than two words, Name".equals(moreThanTwo.reformatName()));
    }
    @Test(expected = UnsupportedOperationException.class)
    public void reformatName4() {
        RamString emptyString = new RamString("");
        assertTrue("".equals(emptyString.reformatName()));
    }


    @Test
    public void ramifyString() {
        RamString expectedString = new RamString("0 and 00 and 000");
        expectedString.ramifyString();
        assertTrue("Go VCU and CS@VCU and 000".equals(expectedString));
    }
    @Test
    public void ramifyString2() {
        RamString onlyZeros = new RamString("00000");
        onlyZeros.ramifyString();
        assertTrue("00000".equals(onlyZeros));
    }
    @Test
    public void ramifyString3() {
        RamString spaceSeparated = new RamString("0 00 0");
        spaceSeparated.ramifyString();
        assertTrue("Go VCU CS@VCU Go VCU".equals(spaceSeparated));
    }


    @Test
    public void convertDigitsToRomanNumeralsInSubstring() {
        RamString normalString = new RamString("I am 19 years old");
        normalString.convertDigitsToRomanNumeralsInSubstring(1,17);
        assertTrue("I am IIX years old".equals(normalString));
    }
    @Test
    public void convertDigitsToRomanNumeralsInSubstring2() {
        RamString restrictedString = new RamString("I am 19 years old");
        restrictedString.convertDigitsToRomanNumeralsInSubstring(7,17);
        assertTrue("I am 1IX years old".equals(restrictedString));
    }
    @Test
    public void convertDigitsToRomanNumeralsInSubstring3() {
        RamString numberString = new RamString("1234567890");
        numberString.convertDigitsToRomanNumeralsInSubstring(1,10);
        assertTrue("IIIIIIIVVVIVIIVIIIIX0".equals(numberString));
    }


}