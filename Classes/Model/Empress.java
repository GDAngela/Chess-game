package Classes;
import Classes.Pieces;
import java.util.Vector;

/**
 * Created by jingjinghuang on 9/13/16.
 */
public class Empress extends Pieces {

    /**
     * Constructor Empress=Knight+Rook
     *
     * @param color
     */
    public Empress(int color) {
        super(color);
        if(color == 1){
            this.name="BE";
        }else{
            this.name="WE";
        }
    }

    /**
     * This method determine if the pieces can move from current position to destination without concerning putting
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
    public boolean canMoveTo(int currentX, int currentY, int destX, int destY){
        Knight knight=new Knight(1);
        Rook rook=new Rook(1);
        return knight.canMoveTo(currentX,currentY,destX,destY) || rook.canMoveTo(currentX,currentY,destX,destY);
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
        Knight knight=new Knight(1);
        Rook rook=new Rook(1);
        Vector<Pairs> ret=new Vector(0);
        if(knight.canMoveTo(currentX,currentY,destX,destY)) {
            ret.addAll(knight.generateValidPath(currentX, currentY, destX, destY));
        }
        if(rook.canMoveTo(currentX,currentY,destX,destY)){
            ret.addAll(rook.generateValidPath(currentX, currentY, destX, destY));
        }
        return ret;
    }
}
