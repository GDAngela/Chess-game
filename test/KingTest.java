package test;

import junit.framework.TestCase;
import Classes.*;

import java.util.Vector;

/**
 * Created by jingjinghuang on 9/7/16.
 */
public class KingTest extends TestCase {
    King king=new King(0);
    public void testCanMoveTo() throws Exception {
        boolean skipTwo=king.canMoveTo(0,0,0,2);
        boolean staySame=king.canMoveTo(1,1,1,1);
        boolean valid=king.canMoveTo(2,2,3,3);
        assertEquals(false,skipTwo);
        assertEquals(false,staySame);
        assertEquals(true,valid);

    }

    public void testGenerateValidPath() throws Exception {
        Vector<Pairs> list=king.generateValidPath(0,0,0,1);
        assertEquals(true,list.isEmpty());
    }

}