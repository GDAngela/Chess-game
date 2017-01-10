package Classes;

import Classes.Pieces;

import java.util.Vector;

/**
 * Created by jingjinghuang on 9/6/16.
 */
public class Rook extends Pieces {
    /**
     * Constructor
     *
     * @param color
     */
    public Rook(int color) {
        super(color);
        if(color == 1){
            this.name="BR";
        }else{
            this.name="WR";
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
        boolean sameX = (currentX == destX);

        boolean sameY = (currentY == destY);

        boolean notCurrentEqualsDestination =!((currentX==destX) && (currentY==destY));

        return notCurrentEqualsDestination && (sameX || sameY);
    }

    /**
     *  This function return the coordinates of every cube which is part of the path of Rook. This method
     * assume that there is a valid move of Rook from current to destination.If the vector returned is empty,
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
        if(currentX==destX){
            for(int iteratorY=1; iteratorY< Math.abs(destY-currentY);iteratorY++){
                Pairs pair;
                if(currentY < destY) {
                    pair = new Pairs(currentX, currentY + iteratorY);
                }else {
                    pair = new Pairs(currentX, currentY - iteratorY);
                }
                list.add(pair);
            }
        }
        if(currentY==destY){
            for(int iteratorX=1; iteratorX< Math.abs(destX-currentX);iteratorX++){
                Pairs pair;
                if(currentX < destX) {
                    pair = new Pairs(currentX + iteratorX, currentY);
                }else{
                    pair = new Pairs(currentX - iteratorX, currentY);
                }
                list.add(pair);
            }
        }

        //printList(list);
        return list;
    }


}
