package Classes;

import Classes.Pieces;

import java.util.Vector;

/**
 * Created by jingjinghuang on 9/6/16.
 */
public class Knight extends Pieces {
    /**
     * Constructor
     *
     * @param color
     */
    public Knight(int color) {
        super(color);
        if(color == 1){
            this.name="BH";
        }else{
            this.name="WH";
        }
    }

    /**This method determine if the pieces can move from current position to destination without concerning putting
     * the king in check. (Board will check) This method assume that the coordinates of current position and destination
     * are not off bound.
     *
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    @Override
    public boolean canMoveTo(int currentX,int currentY,int destX,int destY){
        // X value differs by 2 and Y value differs by 1
        boolean x2Y1=(Math.abs(currentX-destX) == 2) && (Math.abs(currentY - destY) == 1);

        // X value differs by 1 and Y value differs by 2
        boolean x1Y2=(Math.abs(currentX-destX) == 1) && (Math.abs(currentY - destY) == 2);

        boolean notCurrentEqualsDestination = !((currentX==destX) && (currentY==destY));

        return notCurrentEqualsDestination && (x2Y1 || x1Y2);
    }

    /**
     *  This function return the coordinates of every cube which is part of the path of Knight. This method
     * assume that there is a valid move of Knight from current to destination.If the vector returned is empty,
     * then it means that there is no other piece get in the way of a valid path.
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    @Override
    public Vector<Pairs> generateValidPath(int currentX, int currentY, int destX, int destY){
        Vector<Pairs> list=new Vector(0);
        return list;
    }
}
