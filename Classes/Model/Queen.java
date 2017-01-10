package Classes;

import Classes.Pieces;

import java.util.Vector;

/**
 * Created by jingjinghuang on 9/6/16.
 */
public class Queen extends Pieces {
    /**
     * Constructor
     *
     * @param color
     */
    public Queen(int color) {
        super(color);
        if(color == 1){
            this.name="BQ";
        }else{
            this.name="WQ";
        }
    }

    /**
     *
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

        boolean positiveDiag = (destY == destX+b1);

        boolean negativeDiag = (destY == -destX+b2);

        boolean sameX = (currentX == destX);

        boolean sameY = (currentY == destY);

        boolean notCurrentEqualsDestination =!((currentX==destX) && (currentY==destY));

        return notCurrentEqualsDestination && (positiveDiag || negativeDiag || sameX || sameY);

    }

    /**
     *  This function return the coordinates of every cube which is part of the path of Queen. This method
     * assume that there is a valid move of Queen from current to destination.If the vector returned is empty,
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
        int numberOfY=Math.abs(destY-currentY);
        int numberOfX=Math.abs(destX-currentX);

        if(currentX==destX){
            for(int iteratorY=1; iteratorY< numberOfY;iteratorY++){
                Pairs pair;
                if(currentY < destY) {
                    pair = new Pairs(currentX, currentY + iteratorY);
                }else {
                    pair = new Pairs(currentX, currentY - iteratorY);
                }
                list.add(pair);
            }
        } else if(currentY==destY){
            for(int iteratorX=1; iteratorX< numberOfX;iteratorX++){
                Pairs pair;
                if(currentX < destX) {
                    pair = new Pairs(currentX + iteratorX, currentY);
                }else{
                    pair = new Pairs(currentX - iteratorX, currentY);
                }
                list.add(pair);
            }
        } else if(currentX < destX && currentY < destY) {
            for (int iterator = 1; iterator < numberOfX; iterator++) {
                Pairs pair = new Pairs(currentX + iterator, currentY + iterator);
                list.add(pair);
            }
        }else if(currentX < destX && currentY > destY) {
            for (int iterator = 1; iterator < numberOfX; iterator++) {
                Pairs pair = new Pairs(currentX + iterator, currentY - iterator);
                list.add(pair);
            }
        }else if(currentX > destX && currentY < destY) {
            for (int iterator = 1; iterator < numberOfX; iterator++) {
                Pairs pair = new Pairs(currentX - iterator, currentY + iterator);
                list.add(pair);
            }
        }else{
            for (int iterator = 1; iterator < numberOfX; iterator++) {
                Pairs pair = new Pairs(currentX - iterator, currentY - iterator);
                list.add(pair);
            }
        }

        return list;
    }
}
