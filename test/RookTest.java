package test;

import junit.framework.TestCase;
import Classes.*;
import java.util.Vector;

/**
 * Created by jingjinghuang on 9/8/16.
 */
public class RookTest extends TestCase {
    Rook rook=new Rook(1);
    public void testCanMoveTo() throws Exception {
        // same x different y
        boolean sXDY=rook.canMoveTo(4,7,4,3);
        // different x different y
        boolean dXDY=rook.canMoveTo(4,3,6,5);
        boolean staySame=rook.canMoveTo(4,3,4,3);
        assertEquals(true,sXDY);
        assertEquals(false,dXDY);
        assertEquals(false,staySame);

    }

    public void testGenerateValidPath() throws Exception {
        Vector<Pairs> validlist1=rook.generateValidPath(4,3,4,7);
        Vector<Pairs> expectedValidlist1=new Vector(0);
        Pairs pair1=new Pairs(4,4);
        Pairs pair2=new Pairs(4,5);
        Pairs pair3=new Pairs(4,6);

        expectedValidlist1.add(pair1);
        expectedValidlist1.add(pair2);
        expectedValidlist1.add(pair3);
        assertEquals(true,compare(validlist1,expectedValidlist1));


        Vector<Pairs> validlist2=rook.generateValidPath(4,5,4,3);
        Vector<Pairs> expectedValidlist2=new Vector(0);
        Pairs pair4=new Pairs(4,4);

        expectedValidlist2.add(pair4);
        assertEquals(true,compare(validlist2,expectedValidlist2));

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