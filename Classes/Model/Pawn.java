package Classes;

import Classes.Pieces;

import java.util.Vector;

/**
 * Created by jingjinghuang on 9/6/16.
 */
public class Pawn extends Pieces {
    /**
     * Constructor
     *
     * @param color
     */
    public Pawn(int color) {
        super(color);
        if(color == 1){
            this.name="BP";
        }else{
            this.name="WP";
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
        //System.out.println("Inside pawn");
        boolean move1;
        boolean move2;
        boolean diagonal;
        if(this.getColor() ==1 ){
           // System.out.println("Black pawn");
           move1 =((currentX==destX) && (currentY-1 == destY));
           move2 =((currentY==6) && (currentY-2 == destY)&& (currentX==destX));
           diagonal= (currentX-1 == destX && currentY - 1 == destY) || (currentX+1 == destX && currentY -1 == destY);
        }else{
          //  System.out.println("White pawn");
           move1=((currentX==destX) && (currentY+1 == destY));
           move2=((currentY==1) && (currentY+2 == destY))&& (currentX==destX);
           diagonal= (currentX-1 == destX && currentY + 1 == destY) || (currentX+1 == destX && currentY +1 == destY);
        }

        boolean notCurrentEqualsDestination = !((currentX==destX) && (currentY==destY));
       // System.out.println("pawn can move to"+(notCurrentEqualsDestination && (move1 || move2 || diagonal)));
        return notCurrentEqualsDestination && (move1 || move2 || diagonal);
    }

    /**
     *  This function return the coordinates of every cube which is part of the path of Pawn. This method
     * assume that there is a valid move of Pawn from current to destination.If the vector returned is empty,
     * then it means that there is no other piece get in the way of a valid path.
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    @Override
    public Vector<Pairs> generateValidPath(int currentX, int currentY, int destX, int destY){
        Vector list=new Vector(0);
        for (int iterator = 1; iterator < Math.abs(currentY-destY); iterator++) {
            Pairs pair;
            if(this.getColor() ==1) {
                pair = new Pairs(currentX, currentY - iterator);
            }else{
                pair = new Pairs(currentX, currentY + iterator);
            }
            list.add(pair);
        }
        return list;
    }
}
