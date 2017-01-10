package Classes;

import Classes.Pieces;

import java.util.Vector;


/**
 * Created by jingjinghuang on 9/6/16.
 */
public class Bishop extends Pieces {
    /**
     * Constructor
     *
     * @param color
     */
    public Bishop(int color) {
        super(color);
        if(color == 1){
            this.name="BB";
        }else{
            this.name="WB";
        }
    }

    /**
     *This method determine if the pieces can move from current position to destination without concerning putting
     * the king in check. (Board will check) This method assume that the coordinates of current position and destination
     * are not off bound.
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    @Override
    public boolean canMoveTo(int currentX,int currentY,int destX,int destY){
        // y=x+b
        double b1=currentY-currentX;
        // y=-x+b
        double b2=currentY+currentX;

        boolean validPositive= (destY==destX+b1);

        boolean validNegative= (destY == -destX+b2);

        boolean notCurrentEqualsDestination =!((currentX==destX) && (currentY==destY));

        return notCurrentEqualsDestination && (validPositive || validNegative);
    }

    /**
     *  This function return the coordinates of every cube which is part of the path of Bishop. This method
     * assume that there is a valid move of Bishop from current to destination.If the vector returned is empty,
     * then it means that there is no other piece get in the way of a valid path
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    @Override
    public Vector<Pairs> generateValidPath(int currentX, int currentY, int destX, int destY){
        Vector<Pairs> list=new Vector(0);
        int numberOfStep=Math.abs(destY-currentY);
        if(currentX < destX && currentY < destY) {
            for (int iterator = 1; iterator < numberOfStep; iterator++) {
                Pairs pair = new Pairs(currentX + iterator, currentY + iterator);
                list.add(pair);
            }
        }else if(currentX < destX && currentY > destY) {
            for (int iterator = 1; iterator < numberOfStep; iterator++) {
                Pairs pair = new Pairs(currentX + iterator, currentY - iterator);
                list.add(pair);
            }
        }else if(currentX > destX && currentY < destY) {
            for (int iterator = 1; iterator < numberOfStep; iterator++) {
                Pairs pair = new Pairs(currentX - iterator, currentY + iterator);
                list.add(pair);
            }
        }else{
            for (int iterator = 1; iterator < numberOfStep; iterator++) {
                Pairs pair = new Pairs(currentX - iterator, currentY - iterator);
                list.add(pair);
            }
        }
        return list;
    }
}
