package Classes;
import java.util.Vector;
/**
 * Created by jingjinghuang on 9/6/16.
 */
public class Board {
    private int length;
    private int width;
    private Pieces[][] storage;
    private Pairs whiteKingPosition;
    private Pairs blackKingPosition;


    /**
     * construct a empty board for testing
     */
    public Board(){
        this.length=8;
        this.width=8;
        this.storage=new Pieces[length][width];
    }

    /**
     * put all pieces on it's start position
      */
    private void initialize(){
        Rook rookB=new Rook(1);
        Rook rookW=new Rook(0);
        setPiece(0,0,rookW);
        setPiece(7,0,rookW);
        setPiece(0,7,rookB);
        setPiece(7,7,rookB);

        Knight knightB=new Knight(1);
        Knight knightW=new Knight(0);
        setPiece(1,0,knightW);
        setPiece(6,0,knightW);
        setPiece(1,7,knightB);
        setPiece(6,7,knightB);

        Bishop bishopB=new Bishop(1);
        Bishop bishopW=new Bishop(0);
        setPiece(2,0,bishopW);
        setPiece(5,0,bishopW);
        setPiece(2,7,bishopB);
        setPiece(5,7,bishopB);

        King kingB=new King(1);
        King kingW=new King(0);
        Queen queenB=new Queen(1);
        Queen queenW=new Queen(0);
        setPiece(3,0,kingW);
        setPiece(4,0,queenW);
        setPiece(3,7,kingB);
        setPiece(4,7,queenB);

        for(int widthIterator=0; widthIterator<width; widthIterator++){
            Pawn pawnB=new Pawn(1);
            Pawn pawnW=new Pawn(0);
            setPiece(widthIterator,1,pawnW);
            setPiece(widthIterator,6,pawnB);
        }

    }

    /**
     * Constructor of board, default is 8*8
     */
    public Board(int length, int width) {
        this.length=length;
        this.width=width;
        this.storage=new Pieces[length][width];
        this.whiteKingPosition=new Pairs(3,0);
        this.blackKingPosition=new Pairs(3,7);
        this.initialize();

    }

    /**
     * get BlackKingPosition
     * @return
     */
    public Pairs getBlackKingPosition(){
        return blackKingPosition;
    }


    /**
     * get WhiteKingPosition
     * @return
     */
    public Pairs getWhiteKingPosition(){
        return whiteKingPosition;
    }


    /**
     * get the length of board
     * @return
     */
    public int getLength() {

        return length;
    }

    /**
     * get the width of board
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * get peices at given location.
     * @param coordinatesY
     * @param coordinatesX
     * @return
     */
    public Pieces getPiece(int coordinatesX,int coordinatesY){
        if(isNotOffBoard(getLength(),getWidth(),coordinatesX,coordinatesY)) {
            return this.storage[coordinatesY][coordinatesX];
        }else{
            System.out.println("Can't get piece invalid coordinates");
            return null;
        }
    }

    public void setPiece(int coordinatesX,int coordinatesY,Pieces piece){
        //check if off bound
        if(isNotOffBoard(length,width,coordinatesX,coordinatesY)) {
            storage[coordinatesY][coordinatesX] = piece;
            if (piece != null && piece instanceof King) {
                //update king's position
                Pairs kingPosition = new Pairs(coordinatesX, coordinatesY);
                if (piece.getColor() == 1) {
                    blackKingPosition = kingPosition;
                } else {
                    whiteKingPosition = kingPosition;
                }
            }
        }
    }

    /**
     *
      * @param coordinatesX
     * @param coordinatesY
     */
    public void remove(int coordinatesX, int coordinatesY){
        if(isNotOffBoard(getLength(),getWidth(),coordinatesX,coordinatesY)) {
            this.storage[coordinatesY][coordinatesX] = null;

        } else{
            System.out.println("Can't remove invalid coordinates");
        }
    }


    /**
     *
     * @param coordinatesX
     * @param coordinatesY
     * @return
     */
    public boolean isEmpty(int coordinatesX,int coordinatesY){
        return this.storage[coordinatesY][coordinatesX]==null;
    }


    /**
     * Determine if the given coordinate is off bound
     * @param length
     * @param width
     * @param coordinatesX
     * @param coordinatesY
     * @return
     */
    private boolean isNotOffBoard(int length, int width, int coordinatesX, int coordinatesY){
        return (coordinatesX<width)&&(coordinatesX>=0)&&(coordinatesY<length)&&(coordinatesY>=0);
    }


    /**
     * This method determines if there is no block in the path from current to destination. It assume
     * there is a valid path( only called if there is a valid move from current to destination)
     *
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    private boolean noBlock(int currentX, int currentY, int destX, int destY){
        Pieces piece=getPiece(currentX,currentY);
        Vector<Pairs> list=piece.generateValidPath(currentX,currentY,destX,destY);
        if(!list.isEmpty()){
            int yValue;
            int xValue;
            for(int iterator=0; iterator< list.size(); iterator++){
                xValue=list.get(iterator).getLeft();
                yValue=list.get(iterator).getRight();
                if(storage[yValue][xValue] != null){
                    //System.out.println(xValue+""+yValue+"is not null current peice is "+piece.getName());
                    return false;
                }
            }
        }
        return true;
    }



    /**Perform move from current to destination. Return true if move succeed. False indicates the move is invalid
     *(putting its king in check or the move doesn't follow the rules or offbound or same color).
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     */
    public boolean move(int currentX, int currentY,int destX,int destY){
        if(canMoveWithoutCheckOwnKing(currentX,currentY,destX,destY)){
            Pieces piece=getPiece(currentX,currentY);
            remove(currentX,currentY);
            setPiece(destX,destY,piece);
            return true;
        }
        return false;
    }

    /**
     * Determine if move from current to destination will put king in check. But nothing has changed on board
     * after call this function.
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    private boolean canMoveWithoutCheckOwnKing(int currentX, int currentY,int destX,int destY){
        boolean retVal=true;

        if(canMoveTo(currentX, currentY, destX, destY)){
            Pieces currentPiece=getPiece(currentX,currentY);
            Pieces removedPiece=getPiece(destX,destY);
            int color=currentPiece.getColor();
            remove(currentX,currentY);
            setPiece(destX,destY,currentPiece);
            //check if this move will put friendly king in check
            if(isChecked(color)){
                //System.out.println(color+"put king in check");
                retVal=false;
            }
            /*
            else{
                System.out.println("move from"+currentX+currentY+" to "+destX+destY+" didn't out king in check");
            }
*/
            // put it back to original spot
            setPiece(currentX,currentY,currentPiece);
            //put removed piece back
            setPiece(destX,destY,removedPiece);
            return retVal;

        }


        return false;
    }

    /**
     * Determine if a piece can move from current position to destination without blocks in the way
     * @param currentX
     * @param currentY
     * @param destX
     * @param destY
     * @return
     */
    private boolean canMoveTo(int currentX, int currentY,int destX,int destY){
        if (this.isNotOffBoard(getLength(), getWidth(), destX, destY)) {
            Pieces piece = getPiece(currentX, currentY);
            if (piece != null){
                if (isEmpty(destX, destY) || (getPiece(currentX, currentY).getColor() != getPiece(destX, destY).getColor())) {
                    if (piece.canMoveTo(currentX, currentY, destX, destY)) {
                        if (piece instanceof Pawn) {

                            if (isEmpty(destX, destY) && currentX == destX && this.noBlock(currentX, currentY, destX, destY)) {
                               // System.out.println("pawn can move from "+currentX+" "+currentY+"to "+destX+" "+destY);
                                return true;
                            } else if (!isEmpty(destX, destY) && currentX != destX) {
                               // System.out.println("pawn can move from "+currentX+" "+currentY+"to "+destX+" "+destY);
                                return true;
                            } else {
                                return false;
                            }

                        }
                        if (this.noBlock(currentX, currentY, destX, destY)) {
                            return true;
                        }
                        /*
                        else{
                            System.out.println(piece.getName()+"move from "+currentX+""+currentY+"to"+destX+""+destY+"has block");
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
                            printBoard();

                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");

                        }
                        */
                    }
                    /*else{
                        System.out.println(piece.getName()+"move from "+currentX+""+currentY+"to"+destX+""+destY+"don;t follow rule");
                    }
                    */
                }
            }
        }

        return false;
    }


    /**
     *  Find position of king color =1 black king, color =0 white king
     * @param color
     * @return
     */
    public int getKingX(int color){
        if(color ==1){
            return blackKingPosition.getLeft();
        }else{
            return whiteKingPosition.getLeft();
        }
    }

    /**
     *  Find position of king color =1 black king, color =0 white king
     * @param color
     * @return
     */
    public int getKingY(int color){
        if(color ==1){
            return blackKingPosition.getRight();
        }else{
            return whiteKingPosition.getRight();
        }
    }

    /**
     * Check if king in check
     * @param color
     * @return
     */
    public boolean isChecked(int color){
        int kingX=getKingX(color);
        int kingY=getKingY(color);
        //System.out.println("king position is "+kingX+" "+kingY+"and is in check"+canReached(kingX,kingY));
        return canReached(kingX,kingY);
    }


    /**
     * Find list of coordinates of opponents that can reach current position
     *
     *
     * @param currentX
     * @param currentY
     * @return
     */
    private Vector<Pairs> reachedBy(int currentX, int currentY,int color){
        Vector<Pairs> list=new Vector(0);
        for(int y=0; y<getLength();y++){
            for(int x=0;x<getWidth();x++){
                //get all existing piece with different color check if them can move to current
                if(!isEmpty(x,y)) {
                    if ((getPiece(x, y).getColor() == color) && canMoveTo(x, y, currentX, currentY)) {

                        list.add(new Pairs(x, y));
                    }
                }
            }
        }
        return list;
    }

    /**
     * Determine if current position can be reached by other pieces on board
     * @param currentX
     * @param currentY
     * @return
     */
    private boolean canReached(int currentX, int currentY){
        Pieces piece=getPiece(currentX,currentY);
        for(int y=0; y<getLength();y++){
            for(int x=0;x<getWidth();x++){
               // get all existing piece with different color check if them can move to current
                if(!isEmpty(x,y)) {
                    if (getPiece(x, y).getColor() != piece.getColor() && canMoveTo(x, y, currentX, currentY)) {
                        //System.out.println("");
                        return true;
                    }
                    /*else{
                        if(getPiece(x, y).getColor() != piece.getColor()) {
                            System.out.println(x+""+y+" can't move to "+currentX+""+currentY);
                        }
                    }
                    */
                }
            }
        }
        return false;
    }



    /**
     * Determine if the king can escape
     * @param color
     * @return
     */
    private boolean canEscape(int color){
        int kingX=getKingX(color);
        int kingY=getKingY(color);
        return canMoveWithoutCheckOwnKing(kingX,kingY,kingX+1,kingY) || canMoveWithoutCheckOwnKing(kingX,kingY,kingX-1,kingY) ||
                canMoveWithoutCheckOwnKing(kingX,kingY,kingX,kingY+1) || canMoveWithoutCheckOwnKing(kingX,kingY,kingX,kingY-1) ||
                canMoveWithoutCheckOwnKing(kingX,kingY,kingX+1,kingY+1) || canMoveWithoutCheckOwnKing(kingX,kingY,kingX+1,kingY-1) ||
                canMoveWithoutCheckOwnKing(kingX,kingY,kingX-1,kingY+1) || canMoveWithoutCheckOwnKing(kingX,kingY,kingX-1,kingY-1);

    }

    /**
     * determine if the threatening piece can be captured. color indicates color of friendly team
     * @param color
     * @return
     */
    private boolean canCapture(int color){
        int kingX=getKingX(color);
        int kingY=getKingY(color);
        Vector<Pairs> listOfThreatens = reachedBy(kingX, kingY,1-color);
        int size = listOfThreatens.size();
        for(int i=0; i<size;i++){
            int threatenX=listOfThreatens.get(i).getLeft();
            int threatenY=listOfThreatens.get(i).getRight();
            //System.out.println("x is " +threatenX+" y is "+ threatenY);
            //get all existing piece with different color check if them can move to the threaten
            Vector<Pairs> capturer=reachedBy(threatenX,threatenY,color);
            if(capturer.size()!=0){
                //System.out.println("has capturer");
                for (int j=0;j<capturer.size();j++) {
                    int x=capturer.get(j).getLeft();
                    int y=capturer.get(j).getRight();

                    if(canMoveWithoutCheckOwnKing(x,y,threatenX,threatenY)){
                       // System.out.println("capturer x is " +x+" y is "+ y);
                        return true;
                    }

                }
            }

        }
        return false;
    }

    private boolean canBlock(int color){
        int kingX=getKingX(color);
        int kingY=getKingY(color);
        Vector<Pairs> listOfThreatens = reachedBy(kingX, kingY,1-color);
       // printList(listOfThreatens);
        if(listOfThreatens.size()==1){
            int threatenX=listOfThreatens.get(0).getLeft();
            int threatenY=listOfThreatens.get(0).getRight();
            Vector<Pairs> list=getPiece(threatenX,threatenY).generateValidPath(threatenX,threatenY,kingX,kingY);
            //printList(list);
            int length=list.size();
            if(length>0){

                for(int i=0;i<length;i++){
                    int possibleX=list.get(i).getLeft();
                    int possibleY=list.get(i).getRight();
                    Vector<Pairs> capturer=reachedBy(possibleX,possibleY,color);
                    int numOfCapturer=capturer.size();
                    for(int j=0;j<numOfCapturer;j++) {
                        int capturerX=capturer.get(j).getLeft();
                        int capturerY=capturer.get(j).getRight();
                        if (canMoveWithoutCheckOwnKing(capturerX, capturerY, possibleX, possibleY)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(int color){
        if(isChecked(color)){
           // System.out.println("canEscape "+canEscape(color)+" canCapture "+ canCapture(color)  +
           //         "canBlock" + canBlock(color));
            return !(canEscape(color) || canCapture(color) || canBlock(color));
        }
        return false;
    }


    public boolean isStalemate(int color){
        //check if it's checkmate
        if(!isCheckmate(color)) {
            //check if existing peice can move legally
            for (int y = 0; y < getLength(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    Pieces piece = getPiece(x, y);
                    if (piece != null && (piece.getColor()==color)) {
                        for (int i = 0; i < getLength(); i++) {
                            for (int j = 0; j < getWidth(); j++) {
                                if (canMoveWithoutCheckOwnKing(x,y,i,j)){
                                   // System.out.println("can move from" +x +y+"to"+ i +j +"without checking ouwn king");
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }




    /**
     * Helper function to visualize the board
     */
    public void printBoard(){
        System.out.println("Printing board");
        for(int i=7;i>=0;i--){
            for(int j=0;j<8;j++){
                if(this.storage[i][j] == null){
                    System.out.print("NL");
                }else{
                    System.out.print(this.storage[i][j].getName());
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void printList(Vector<Pairs> list){
        int length=list.size();
        for(int i=0;i<length;i++){
            System.out.print(list.get(i).getLeft());
            System.out.println(list.get(i).getRight());
        }
    }
}


