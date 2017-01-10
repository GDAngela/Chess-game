package test;

import junit.framework.TestCase;
import Classes.*;
import java.util.Vector;

/**
 * Created by jingjinghuang on 9/8/16.
 */
public class PawnTest extends TestCase {
    Pawn pawnW=new Pawn(0);
    Pawn pawnB=new Pawn(1);
    public void testCanMoveTo() throws Exception {
        boolean moveForward1W=pawnW.canMoveTo(1,3,1,4);
        boolean moveForward1B=pawnB.canMoveTo(4,5,4,4);
        boolean moveForward2W=pawnW.canMoveTo(2,1,2,3);
        boolean moveForward2B=pawnB.canMoveTo(2,6,2,4);
        boolean diagonal1W=pawnW.canMoveTo(2,2,1,3);
        boolean diagonal1B=pawnB.canMoveTo(2,2,3,1);

        assertEquals(true,moveForward1B);
        assertEquals(true,moveForward1W);
        assertEquals(true,moveForward2W);
        assertEquals(true,moveForward2B);
        assertEquals(true,diagonal1W);
        assertEquals(true,diagonal1B);

    }

    public void testGenerateValidPath() throws Exception {
        Vector<Pairs> validlist1=pawnB.generateValidPath(2,3,2,1);
        Vector<Pairs> expectedValidlist1=new Vector(0);
        Pairs pair1=new Pairs(2,2);
        expectedValidlist1.add(pair1);
        assertEquals(true,compare(validlist1,expectedValidlist1));

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