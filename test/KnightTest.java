package test;

import Classes.Knight;
import junit.framework.TestCase;
import Classes.*;
import java.util.Vector;

/**
 * Created by jingjinghuang on 9/8/16.
 */
public class KnightTest extends TestCase {
    Knight knight=new Knight(0);
    public void testCanMoveTo() throws Exception {
        boolean notL=knight.canMoveTo(0,0,3,2);
        boolean valid1=knight.canMoveTo(3,3,4,5);
        boolean valid2=knight.canMoveTo(3,3,4,1);
        boolean valid3=knight.canMoveTo(3,3,2,5);
        boolean valid4=knight.canMoveTo(3,3,2,1);
        boolean valid5=knight.canMoveTo(3,3,5,4);
        boolean valid6=knight.canMoveTo(3,3,5,2);
        boolean valid7=knight.canMoveTo(3,3,1,4);
        boolean valid8=knight.canMoveTo(3,3,1,2);
        boolean staySame=knight.canMoveTo(3,3,3,3);

        assertEquals(true,valid1);
        assertEquals(true,valid2);
        assertEquals(true,valid3);
        assertEquals(true,valid4);
        assertEquals(true,valid5);
        assertEquals(true,valid6);
        assertEquals(true,valid7);
        assertEquals(true,valid8);
        assertEquals(false,notL);
        assertEquals(false,staySame);

    }

    public void testGenerateValidPath() throws Exception {
        Vector<Pairs> list=knight.generateValidPath(3,3,1,2);
        assertEquals(true,list.isEmpty());
    }

}