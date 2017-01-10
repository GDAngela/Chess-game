package Classes;

import java.util.Vector;

/**
 * Created by jingjinghuang on 9/6/16.
 */
public abstract class Pieces {
    private int color;
    protected String name;

    /**
     * Constructor
     */
    public Pieces(int color){
        this.color=color;
    }

    /**
     *
     * @return
     */
    public int getColor(){
        return this.color;
    }

    /**
     *
     * @return
     */
    public String getName(){
        return this.name;
    }


    /** This method determine if the pieces can move from current position to destination without concerning putting
     * the king in check. (Board will check) This method assume that the coordinates of current position and destination
     * are not off bound.
     *
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */

    public abstract boolean canMoveTo(int currentX,int currentY,int destX,int destY) ;



    /**
     * This function return the coordinates of every cube which is part of the path,except the current cube.
     * and destination cube. Also this method assume that the piece move from current position to destination is
     * valid and the coordinates of current position and destination are not off bound.
      * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    public abstract Vector<Pairs> generateValidPath(int currentX,int currentY,int destX,int destY);



}
