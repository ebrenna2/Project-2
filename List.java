import java.util.NoSuchElementException;

// List class ADT. Generalize by using "Object" for the element type.
public interface List { // List class ADT
    // Remove all contents from the list, so it is once again empty
    public void clear();

    // Insert "it" at the current location
    // The client must ensure that the list's capacity is not exceeded
    public boolean insert(Object it);

    // Append "it" at the end of the list
    // The client must ensure that the list's capacity is not exceeded
    public boolean append(Object it);

    // Set the current position to the start of the list
    public void moveToStart();

    // Move the current position one step right, no change if already at end
    public void next();

    // Return the number of elements in the list
    public int length();

    // Return the position of the current element
    public int currPos();

    // Return the current element
    public Object getValue() throws NoSuchElementException;


}