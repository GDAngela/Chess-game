package test;

import Classes.Empress;
import Classes.Pairs;
import junit.framework.TestCase;

import java.util.Vector;

/**
 * Created by jingjinghuang on 9/14/16.
 */
public class EmpressTest extends TestCase {
    Empress empress=new Empress(1);
    public void testCanMoveTo() throws Exception {
        boolean notL=empress.canMoveTo(0,0,3,2);
        boolean valid1=empress.canMoveTo(3,3,4,5);
        boolean valid2=empress.canMoveTo(3,3,4,1);
        boolean valid3=empress.canMoveTo(3,3,2,5);
        boolean valid4=empress.canMoveTo(3,3,2,1);
        boolean valid5=empress.canMoveTo(3,3,5,4);
        boolean valid6=empress.canMoveTo(3,3,5,2);
        boolean valid7=empress.canMoveTo(3,3,1,4);
        boolean valid8=empress.canMoveTo(3,3,1,2);
        boolean staySame=empress.canMoveTo(3,3,3,3);

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

        // same x different y
        boolean sXDY=empress.canMoveTo(4,7,4,3);
        // different x different y
        boolean dXDY=empress.canMoveTo(4,3,6,5);
        assertEquals(true,sXDY);
        assertEquals(false,dXDY);
    }

    public void testGenerateValidPath() throws Exception {
        Vector<Pairs> validlist1=empress.generateValidPath(4,3,4,7);
        Vector<Pairs> expectedValidlist1=new Vector(0);
        Pairs pair1=new Pairs(4,4);
        Pairs pair2=new Pairs(4,5);
        Pairs pair3=new Pairs(4,6);

        expectedValidlist1.add(pair1);
        expectedValidlist1.add(pair2);
        expectedValidlist1.add(pair3);
        assertEquals(true,compare(validlist1,expectedValidlist1));


        Vector<Pairs> validlist2=empress.generateValidPath(4,5,4,3);
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