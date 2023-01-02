package cn.edu.whut.sept.zuul;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTest {

    @Test
    public void getCommandWord() {
        Command command=new Command("firstword","secondword");
        assertEquals("firstword",command.getCommandWord());
    }

    @Test
    public void getSecondWord() {
        Command command=new Command("firstword","secondword");
        assertEquals("secondword",command.getSecondWord());
    }

    @Test
    public void isUnknown() {
        Command command=new Command("firstword","secondword");
        assertEquals(false,command.isUnknown());
    }

    @Test
    public void hasSecondWord() {
        Command command=new Command("firstword","secondword");
        assertEquals(true,command.hasSecondWord());
    }
}

