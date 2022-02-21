package cmsc256;

/**
 *  CMSC 256
 *  Project 2
 *  Ward, Preston
 *  2/18/2022
 *
 *  RamString implements an interface and manipulates a String
 */
public class RamString implements WackyStringInterface {

    private String ramString;

    public RamString() {
        ramString = "Rodney, the Ram";
    }
    public RamString(String string) {
        setWackyString(string);
    }

    @Override
    public void setWackyString(String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException("String cannot be null.");
        } else {
            ramString = string;
        }
    }

    @Override
    public String getWackyString() {
        return ramString;
    }

    @Override
    public String getEveryFifthCharacter() {
        String returnString = "";
        if (ramString.length() < 5) {
            return "";
        } else {
            for (int i = 4; i < ramString.length(); i += 5) {
                returnString += ramString.charAt(i);
            }
        }
        return returnString;
    }

    @Override
    public String getEvenCharacters() {
        String returnString = "";
        for (int i = 1; i < ramString.length(); i += 2) {
            returnString += ramString.charAt(i);
        }
        return returnString;
    }

    @Override
    public String getOddCharacters() {
        String returnString = "";
        for (int i = 0; i < ramString.length(); i += 2) {
            returnString += ramString.charAt(i);
        }
        return returnString;
    }

    @Override
    public int countDoubleDigits() {
        int returnInt = 0;

        for (int i = 0; i < getWackyString().length(); i++) {
            if ((i - 1) > -1 && (i + 2) < getWackyString().length()) {
                if (getWackyString().charAt(i) == getWackyString().charAt(i + 1) && getWackyString().charAt(i) != getWackyString().charAt(i + 2) && getWackyString().charAt(i) != getWackyString().charAt(i - 1)) {
                    returnInt++;
                }
            }
        }

        return returnInt;
    }

    @Override
    public int countVowels() {
        int returnInt = 0;
        for (int i = 0; i < ramString.length(); i++) {
            if (ramString.charAt(i) == 'a' || ramString.charAt(i) == 'A' || ramString.charAt(i) == 'e' || ramString.charAt(i) == 'E' || ramString.charAt(i) == 'i' || ramString.charAt(i) == 'I' || ramString.charAt(i) == 'o' || ramString.charAt(i) == 'O' || ramString.charAt(i) == 'u' || ramString.charAt(i) == 'U' || ramString.charAt(i) == 'y' || ramString.charAt(i) == 'Y') {
                returnInt++;
            }
        }
        return returnInt;
    }

    @Override
    public String reformatName() throws UnsupportedOperationException {
        String returnString = "";
        if (!ramString.contains(" ") && ramString.length() > 0) {
            throw new UnsupportedOperationException("The first and last name have to be included.");
        } else {
            for (int i = 0; i < ramString.length(); i++) {
                if (ramString.charAt(i) == ' ') {
                    String firstName = ramString.substring(0,i);
                    String lastName = ramString.substring(i + 1);
                    if (firstName.contains(" ")) {
                        return "";
                    } else {
                        returnString += lastName + ", " + firstName;
                    }
                    break;
                }
            }
        }
        return returnString;
    }

    @Override
    public void ramifyString() {
        for (int i = 0; i < ramString.length(); i++) {
            char current = ramString.charAt(i);
            if (current == '0') {
                int zeroes = 0;
                int next = 1;
                while (i + next < ramString.length()) {
                    if (ramString.charAt(i + next) == '0') {
                        zeroes++;
                        next++;
                    } else {
                        break;
                    }
                }
                if (zeroes == 0) {
                    ramString = ramString.substring(0,i) + "Go VCU" + ramString.substring(i + 1);
                }
                else if (zeroes == 1) {
                    ramString = ramString.substring(0,i) + "CS@VCU" + ramString.substring(i + 2);
                } else {
                    i += zeroes;
                }
            }
        }
//        System.out.println(wackyString);
    }

    @Override
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws IllegalArgumentException {
        String returnString = "";
        if (startPosition > endPosition && startPosition > -1 && endPosition < ramString.length()) {
            throw new IllegalArgumentException("Start position cannot be greater than end position.");
        }
        else if (startPosition < 1 || endPosition > ramString.length() + 1) {
            throw new StringIndexOutOfBoundsException("startPosition and endPosition have to be within the length of the string.");
        } else {
            returnString += ramString.substring(0,startPosition - 1);
            String substring = ramString.substring(startPosition - 1,endPosition);
            String theRest = ramString.substring(endPosition);
            for (int i = 0; i < substring.length(); i++) {
                String character = substring.substring(i,i + 1);
                if ("1".equals(character)) {
                    returnString += "I";
                } else if ("2".equals(character)) {
                    returnString += "II";
                } else if ("3".equals(character)) {
                    returnString += "III";
                } else if ("4".equals(character)) {
                    returnString += "IV";
                } else if ("5".equals(character)) {
                    returnString += "V";
                } else if ("6".equals(character)) {
                    returnString += "VI";
                } else if ("7".equals(character)) {
                    returnString += "VII";
                } else if ("8".equals(character)) {
                    returnString += "VIII";
                } else if ("9".equals(character)) {
                    returnString += "IX";
                } else {
                    returnString += character;
                }
            }
            returnString += theRest;
        }
        ramString = returnString;
    }

}
