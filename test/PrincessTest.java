package test;

import Classes.Pairs;
import Classes.Princess;
import junit.framework.TestCase;

import java.util.Vector;

/**
 * Created by jingjinghuang on 9/14/16.
 */
public class PrincessTest extends TestCase {
    Princess princess=new Princess(1);
    public void testCanMoveTo() throws Exception {
        boolean notL=princess.canMoveTo(0,0,3,2);
        boolean valid1=princess.canMoveTo(3,3,4,5);
        boolean valid2=princess.canMoveTo(3,3,4,1);
        boolean valid3=princess.canMoveTo(3,3,2,5);
        boolean valid4=princess.canMoveTo(3,3,2,1);
        boolean valid5=princess.canMoveTo(3,3,5,4);
        boolean valid6=princess.canMoveTo(3,3,5,2);
        boolean valid7=princess.canMoveTo(3,3,1,4);
        boolean valid8=princess.canMoveTo(3,3,1,2);
        boolean staySame=princess.canMoveTo(3,3,3,3);

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

        boolean notDiagonal=princess.canMoveTo(4,3,2,2);
        boolean valid9=princess.canMoveTo(4,3,6,5);
        boolean valid10=princess.canMoveTo(4,3,2,5);
        assertEquals(true,valid9);
        assertEquals(true,valid10);
        assertEquals(true,notDiagonal);
    }

    public void testGenerateValidPath() throws Exception {
        Vector<Pairs> validlist1=princess.generateValidPath(4,3,6,5);
        Vector<Pairs> expectedValidlist1=new Vector(0);
        Pairs pair1=new Pairs(5,4);
        expectedValidlist1.add(pair1);
        assertEquals(true,compare(validlist1,expectedValidlist1));

        Vector<Pairs> validlist2=princess.generateValidPath(4,3,2,5);
        Vector<Pairs> expectedValidlist2=new Vector(0);
        Pairs pair2=new Pairs(3,4);
        expectedValidlist2.add(pair2);
        assertEquals(true,compare(validlist2,expectedValidlist2));

        Vector<Pairs> validlist3=princess.generateValidPath(4,3,5,4);
        Vector<Pairs> expectedValidlist3=new Vector(0);
        assertEquals(true,compare(validlist3,expectedValidlist3));

        Vector<Pairs> validlist4=princess.generateValidPath(4,3,2,1);
        Vector<Pairs> expectedValidlist4=new Vector(0);
        Pairs pair4=new Pairs(3,2);
        expectedValidlist4.add(pair4);
        assertEquals(true,compare(validlist4,expectedValidlist4));
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