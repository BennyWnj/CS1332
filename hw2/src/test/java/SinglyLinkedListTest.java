import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SinglyLinkedListTest {

    private  SinglyLinkedList<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() throws Exception {
        list = new SinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void addAtIndex() {
        assertEquals(0, list.size());
        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a
        list.addAtIndex(1,"test");

        LinkedListNode<String> curr = list.getHead();
        assertEquals("0a", curr.getData());
        curr = curr.getNext();
        assertEquals("test", curr.getData());
    }
}