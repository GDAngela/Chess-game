package Classes;

import Classes.Pieces;
import java.util.Vector;

/**
 * Created by jingjinghuang on 9/6/16.
 */
public class King extends Pieces {

    /**
     * Constructor
     *
     * @param color
     */
    public King(int color) {
        super(color);
        if(color == 1){
            this.name="BK";
        }else{
            this.name="WK";
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

        boolean notCurrentEqualsDestination = !((currentX==destX) && (currentY==destY));

        /** All the valid coordxinates for king are those with difference between currentX and destX and difference
         * currentX and destX less than and equal to 1
         */
        boolean offLessOrEqualOne=Math.abs(currentX - destX) <=1 && Math.abs(currentY - destY) <=1;

        return notCurrentEqualsDestination && offLessOrEqualOne;
    }

    /**
     *  This function return the coordinates of every cube which is part of the path of King. This method
     * assume that there is a valid move of King from current to destination. If the vector returned is empty,
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
