package test;

import Classes.Pairs;
import junit.framework.TestCase;

/**
 * Created by jingjinghuang on 9/7/16.
 */
public class PairsTest extends TestCase {
    public void testGetLeft() throws Exception {
        Pairs pair=new Pairs(1,2);
        assertEquals(1,pair.getLeft());

    }

    public void testGetRight() throws Exception {
        Pairs pair=new Pairs(1,2);
        assertEquals(2,pair.getRight());
    }

    public void testPrintPairs() throws Exception {
        Pairs pair1=new Pairs(1,2);
        Pairs pair2=new Pairs(0,0);
        Pairs pair3=new Pairs(-1,9);

    }

}