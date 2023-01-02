package cn.edu.whut.sept.zuul;

import static org.junit.Assert.*;
import java.util.Set;
import java.util.HashMap;

public class RoomTest {
    Room room1=new Room("in this Room1",4);
    Room room2=new Room("in the Room2",3);
    @org.junit.Test
    public void getShortDescription() {
        assertEquals("in this Room1",room1.getShortDescription());
    }

    @org.junit.Test
    public void getLongDescription() {
        room1.setExit("west",room2);
        assertEquals("You are in this Room1."+"\n" + "Exits: west",room1.getLongDescription());
    }

    @org.junit.Test
    public void getExit() {
        room1.setExit("west",room2);
        assertEquals(room2,room1.getExit("west"));
    }
}
