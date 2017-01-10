package test;

import Classes.Bishop;
import Classes.Pairs;
import junit.framework.TestCase;

import java.util.Vector;
/**
 * Created by jingjinghuang on 9/8/16.
 */
public class BishopTest extends TestCase {
    Bishop bishop=new Bishop(0);
    public void testCanMoveTo() throws Exception {
        boolean notDiagonal=bishop.canMoveTo(4,3,2,2);
        boolean valid=bishop.canMoveTo(4,3,6,5);
        boolean valid2=bishop.canMoveTo(4,3,2,5);
        boolean staySame=bishop.canMoveTo(4,3,4,3);
        assertEquals(true,valid);
        assertEquals(true,valid2);
        assertEquals(false,notDiagonal);
        assertEquals(false,staySame);

    }

    public void testGenerateValidPath() throws Exception {
        Vector<Pairs> validlist1=bishop.generateValidPath(4,3,6,5);
        Vector<Pairs> expectedValidlist1=new Vector(0);
        Pairs pair1=new Pairs(5,4);
        expectedValidlist1.add(pair1);
        assertEquals(true,compare(validlist1,expectedValidlist1));

        Vector<Pairs> validlist2=bishop.generateValidPath(4,3,2,5);
        Vector<Pairs> expectedValidlist2=new Vector(0);
        Pairs pair2=new Pairs(3,4);
        expectedValidlist2.add(pair2);
        assertEquals(true,compare(validlist2,expectedValidlist2));

        Vector<Pairs> validlist3=bishop.generateValidPath(4,3,5,4);
        Vector<Pairs> expectedValidlist3=new Vector(0);
        assertEquals(true,compare(validlist3,expectedValidlist3));

        Vector<Pairs> validlist4=bishop.generateValidPath(4,3,2,1);
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