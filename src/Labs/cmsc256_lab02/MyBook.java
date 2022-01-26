//package Labs.cmsc256_lab02;
package cmsc256;

public class MyBook implements Comparable<MyBook> {
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String ISBN10;
    private String ISBN13;

    //default and parameterized constructors
    public MyBook() {
        title = "None Given";
        authorFirstName = "None Given";
        authorLastName = "None Given";
        ISBN10 = "0000000000";
        ISBN13 = "0000000000000";
    }
    public MyBook(String title, String authorFirstName, String authorLastName, String ISBN10, String ISBN13) {
        setTitle(title);
        setAuthorFirstName(authorFirstName);
        setAuthorLastName(authorLastName);
        setISBN10(ISBN10);
        setISBN13(ISBN13);
    }

    //getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException();
        } else {
            this.title = title;
        }
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }
    public void setAuthorFirstName(String authorFirstName) {
        if (authorFirstName == null) {
            throw new IllegalArgumentException();
        } else {
            this.authorFirstName = authorFirstName;
        }
    }

    public String getAuthorLastName() {
        return authorLastName;
    }
    public void setAuthorLastName(String authorLastName) {
        if (authorLastName == null) {
            throw new IllegalArgumentException();
        } else {
            this.authorLastName = authorLastName;
        }
    }

    public String getISBN10() {
        return ISBN10;
    }
    public void setISBN10(String ISBN10) {
        if (ISBN10 == null) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < ISBN10.length(); i++) {
                if ((ISBN10.charAt(i) >= 'a' && ISBN10.charAt(i) <= 'z') || ISBN10.length() != 10) {
                    throw new IllegalArgumentException();
                } else {
                    this.ISBN10 = ISBN10;
                }
            }
        }
    }

    public String getISBN13() {
        return ISBN13;
    }
    public void setISBN13(String ISBN13) {
        if (ISBN13 == null) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < ISBN13.length(); i++) {
                if ((ISBN13.charAt(i) >= 'a' && ISBN13.charAt(i) <= 'z') || ISBN13.length() != 13) {
                    throw new IllegalArgumentException();
                } else {
                    this.ISBN13 = ISBN13;
                }
            }
        }
    }

    //toString method that returns a descriptive string
    public String toString() {
        return  "Title: " + title +
                "\nAuthor: " + authorFirstName + " " + authorLastName +
                "\nISBN10: " + ISBN10 +
                "\nISBN13: " + ISBN13;
    }

    public boolean equals(Object otherBook) {
        if (this == otherBook) {
            return true;
        }
        if (!(otherBook instanceof MyBook)) {
            return false;
        }
        MyBook other = (MyBook) otherBook;
        if (other.getAuthorFirstName().equals(this.authorFirstName) ||
                other.getAuthorLastName().equals(this.authorLastName) ||
                other.getTitle().equals(this.title) || other.getISBN10().equals(this.ISBN10) ||
                other.getISBN13().equals(this.ISBN13)) {
            return true;
        } else {
            return false;
        }
    }

    public int compareTo(MyBook other) {
        int result = (this.authorLastName).compareTo(other.getAuthorLastName());

        if (result == 0) {
            result = getAuthorFirstName().compareTo(other.getAuthorFirstName());
        }

        if (result == 0) {
            result = getTitle().compareTo(other.getTitle());
        }

        return result;
    }
}
