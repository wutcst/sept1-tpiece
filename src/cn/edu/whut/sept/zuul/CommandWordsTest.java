package cn.edu.whut.sept.zuul;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandWordsTest {

    @Test
    public void isCommand() {
        CommandWords commandwords=new CommandWords();
        assertEquals(true,commandwords.isCommand("back"));
    }
}

