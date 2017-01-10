package test;

import junit.framework.TestCase;
import Classes.*;
import java.util.Vector;

/**
 * Created by jingjinghuang on 9/8/16.
 */
public class QueenTest extends TestCase {
    Queen queen=new Queen(1);
    public void testCanMoveTo() throws Exception {
        boolean lShape=queen.canMoveTo(4,3,2,2);
        boolean diagonal=queen.canMoveTo(4,3,6,5);
        boolean staySame=queen.canMoveTo(4,3,4,3);
        boolean horizontal=queen.canMoveTo(4,3,4,7);
        boolean vertical=queen.canMoveTo(4,3,7,3);
        assertEquals(true,diagonal);
        assertEquals(true,horizontal);
        assertEquals(true,vertical);
        assertEquals(false,lShape);
        assertEquals(false,staySame);

    }

    public void testGenerateValidPath() throws Exception {
        // upper right diagonal
        Vector<Pairs> validlist1=queen.generateValidPath(4,3,6,5);
        Vector<Pairs> expectedValidlist1=new Vector(0);
        Pairs pair1=new Pairs(5,4);
        expectedValidlist1.add(pair1);
        assertEquals(true,compare(validlist1,expectedValidlist1));

        //upper left diagonal
        Vector<Pairs> validlist2=queen.generateValidPath(4,3,2,5);
        Vector<Pairs> expectedValidlist2=new Vector(0);
        Pairs pair2=new Pairs(3,4);
        expectedValidlist2.add(pair2);
        assertEquals(true,compare(validlist2,expectedValidlist2));

        //empty list
        Vector<Pairs> validlist3=queen.generateValidPath(4,3,5,4);
        Vector<Pairs> expectedValidlist3=new Vector(0);
        assertEquals(true,compare(validlist3,expectedValidlist3));

        //lower left diagonal
        Vector<Pairs> validlist4=queen.generateValidPath(4,3,2,1);
        Vector<Pairs> expectedValidlist4=new Vector(0);
        Pairs pair4=new Pairs(3,2);
        expectedValidlist4.add(pair4);
        assertEquals(true,compare(validlist4,expectedValidlist4));

        // positive horizontal
        Vector<Pairs> validlist5=queen.generateValidPath(4,3,4,7);
        Vector<Pairs> expectedValidlist5=new Vector(0);
        Pairs pair5=new Pairs(4,4);
        Pairs pair6=new Pairs(4,5);
        Pairs pair7=new Pairs(4,6);

        expectedValidlist5.add(pair5);
        expectedValidlist5.add(pair6);
        expectedValidlist5.add(pair7);
        assertEquals(true,compare(validlist5,expectedValidlist5));

        // negative vertical
        Vector<Pairs> validlist6=queen.generateValidPath(4,5,4,3);
        Vector<Pairs> expectedValidlist6=new Vector(0);
        Pairs pair8=new Pairs(4,4);

        expectedValidlist6.add(pair8);
        assertEquals(true,compare(validlist6,expectedValidlist6));
    }

    public boolean compare(Vector<Pairs> list1,Vector<Pairs> list2){
        boolean ret=true;
        int length1=list1.size();
        int length2=list2.size();
        if(length1!=length2){
            return false;
        }

        boolean leftSame;
        boolean rightSame;

        for(int i=0;i<length1;i++){
            leftSame=list1.get(i).getLeft() == list2.get(i).getLeft();
            rightSame=list1.get(i).getRight() == list2.get(i).getRight();
            if((!leftSame) || (!rightSame) ){
                ret=false;
                break;
            }
        }
        return ret;
    }

}