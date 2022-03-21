package cmsc256;

import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;

import java.util.ArrayList;

/**
 * CMSC 256
 * Project 3
 * Ward, Preston
 *
 * Project 3 creates a linked list of Song objects from the Bridges API to be manipulated
 */
public class SongList implements cmsc256.List<bridges.data_src_dependent.Song> {

    private SLelement<Song> head;      // Pointer to list header
    private SLelement<Song> tail;      // Pointer to last element
    private SLelement<Song> curr;      // Access to current element
    private int listSize;      // Size of list

    // Constructors
    public SongList() {
        clear();
        fillList();
    }

    //helper method
    private void fillList() {
        Bridges bridges = new Bridges(3,"wardpl2","133617755398");
        DataSource ds = bridges.getDataSource();
        ArrayList<Song> songList = new ArrayList<>();

        try {
            songList = ds.getSongData();
        } catch (Exception e) {
            System.out.println("Unable to connect to Bridges.");
        }

        for (Song S : songList) {
            append(S);
        }
    }

    // Remove all elements
    public void clear() {
        curr = tail = new SLelement<Song>(); // Create trailer
        head = new SLelement<Song>(tail);        // Create header
        listSize = 0;
    }

    // Insert "it" at current position
    public boolean insert(Song it) {
        curr.setNext(new SLelement<Song>());
        curr.setValue(it);
        if (tail == curr) {
            tail = curr.getNext();  // New tail
        }
        listSize++;
        return true;
    }

    // Append "it" to list
    public boolean append(Song it) {
        tail.setNext(new SLelement<Song>());
        tail.setValue(it);
        tail = tail.getNext();
        listSize++;
        return true;
    }

    // Remove and return current element
    public Song remove () {
        if (curr == tail) {// Nothing to remove
            return null;
        }
        Song it = curr.getValue();                  // Remember value
        curr.setValue(curr.getNext().getValue()); // Pull forward the next element
        if (curr.getNext() == tail) {
            tail = curr;   // Removed last, move tail
        }
        curr.setNext(curr.getNext().getNext());       // Point around unneeded link
        listSize--;                             // Decrement element count
        return it;                              // Return value
    }

    public void moveToStart() {
        curr = head.getNext(); // Set curr at list start
    }
    public void moveToEnd() {
        curr = tail; // Set curr at list end
    }

    // Move curr one step left; no change if now at front
    public void prev() {
        if (head.getNext() == curr) {
            return; // No previous element
        }
        SLelement<Song> temp = head;
        // March down list until we find the previous element
        while (temp.getNext() != curr) {
            temp = temp.getNext();
        }
        curr = temp;
    }

    // Move curr one step right; no change if now at end
    public void next() { if (curr != tail) { curr = curr.getNext(); } }

    public int length() { return listSize; } // Return list length


    // Return the position of the current element
    public int currPos() {
        SLelement<Song> temp = head.getNext();
        int i;
        for (i=0; curr != temp; i++) {
            temp = temp.getNext();
        }
        return i;
    }

    // Move down list to "pos" position
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) {
            return false;
        }
        curr = head.getNext();
        for(int i=0; i<pos; i++) { curr = curr.getNext(); }
        return true;
    }

    // Return true if current position is at end of the list
    public boolean isAtEnd() { return curr == tail; }

    // Return current element value. Note that null gets returned if curr is at the tail
    public Song getValue() {
        if (curr == tail) { // No current element
            return null;
        }
        return curr.getValue();
    }

    //Tell if the list is empty or not
    public boolean isEmpty() {
        return listSize == 0;
    }


    public String getSongsByArtist(String artist) {
        return null;
    }


    public static void main(String[] args) {
        SongList list = new SongList();
        System.out.println(list.getValue().getArtist());
    }
}
