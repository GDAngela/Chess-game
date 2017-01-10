package test;

import junit.framework.TestCase;
import Classes.*;
/**
 * Created by jingjinghuang on 9/12/16.
 */
public class BoardTest extends TestCase {
    public void testMoveKingMoveToEmptyValid() throws Exception{
        Board board=new Board();
        King BK=new King(1);
        board.setPiece(3,4,BK);
        //check if king's position is updating properly;
        Pairs BKPosition=new Pairs(3,4);
        assertEquals(true,BKPosition.compair(board.getBlackKingPosition()));
        assertEquals(true,board.move(3,4,3,3));
        BKPosition=new Pairs(3,3);
        //check if king's position is updating properly;
        assertEquals(true,BKPosition.compair(board.getBlackKingPosition()));
    }

    public void testMoveKingToIEmptyInvalid() throws Exception{
        Board board=new Board();
        King BK=new King(1);
        board.setPiece(3,7,BK);
        //check if king's position is updating properly;
        Pairs BKPosition=new Pairs(3,7);
        assertEquals(true,BKPosition.compair(board.getBlackKingPosition()));
        //off board
        assertEquals(false,board.move(3,7,3,8));
        //move don't follow rule
        assertEquals(false,board.move(3,7,3,5));

        //check if king's position is updating properly;
        assertEquals(true,BKPosition.compair(board.getBlackKingPosition()));
    }

    public void testMoveKingCanCapture() throws Exception{
        Board board=new Board();
        King BK=new King(1);
        board.setPiece(3,4,BK);
        Pairs BKPosition=new Pairs(3,4);
        //check id king's position is updating properly;
        assertEquals(true,BKPosition.compair(board.getBlackKingPosition()));
        Pawn WP=new Pawn(0);
        board.setPiece(4,4,WP);

        //check if king can move
        assertEquals(true,board.move(3,4,4,4));
        //check if king's position is updating properly;
        BKPosition=new Pairs(4,4);
        assertEquals(true,BKPosition.compair(board.getBlackKingPosition()));
    }

    public void testMoveKingToOwnKind() throws Exception{
        Board board=new Board();
        King BK=new King(1);
        board.setPiece(4,4,BK);
        //check if king's position is updating properly;
        Pairs BKPosition=new Pairs(4,4);
        assertEquals(true,BKPosition.compair(board.getBlackKingPosition()));

        Pawn BP=new Pawn(1);
        board.setPiece(5,4,BP);
        assertEquals(false,board.move(4,4,5,4));
        assertEquals(true,BKPosition.compair(board.getBlackKingPosition()));

    }

    public void testMoveKingPutKingIncheck() throws Exception{
        Board board=new Board();
        King BK=new King(1);
        board.setPiece(4,4,BK);
        Pawn WP=new Pawn(0);
        board.setPiece(4,3,WP);
        assertEquals(false,board.move(4,4,5,4));


    }

    public void testMoveRookMoveToEmptyValid() throws Exception{
        Board board=new Board();
        Rook BR=new Rook(1);
        King BK=new King(1);
        board.setPiece(7,7,BR);
        board.setPiece(3,7,BK);
        assertEquals(true,board.move(7,7,7,3));

    }

    public void testMoveRookMoveToEmptyInvalid() throws Exception{
        Board board=new Board();
        Rook BR=new Rook(1);
        King BK=new King(1);
        board.setPiece(7,7,BR);
        board.setPiece(3,7,BK);
        assertEquals(false,board.move(7,7,6,6));
    }

    public void testMoveRookHasBlock() throws Exception{
        Board board=new Board();
        Rook BR=new Rook(1);
        King BK=new King(1);
        Pawn WP=new Pawn(0);
        Bishop WB=new Bishop(0);
        board.setPiece(7,7,BR);
        board.setPiece(3,7,BK);
        board.setPiece(7,4,WP);
        board.setPiece(7,2,WB);
        //board.printBoard();
        assertEquals(false,board.move(7,7,7,2));
        //board.printBoard();
    }

    public void testMoveRookCapture() throws Exception{
        Board board=new Board();
        Rook BR=new Rook(1);
        King BK=new King(1);
        Bishop WB=new Bishop(0);
        board.setPiece(7,7,BR);
        board.setPiece(3,7,BK);
        board.setPiece(7,2,WB);
        assertEquals(true,board.move(7,7,7,2));

    }

    public void testMoveRookMoveToSameColor() throws Exception{
        Board board=new Board();
        Rook BR=new Rook(1);
        King BK=new King(1);
        Bishop BB=new Bishop(1);
        board.setPiece(7,7,BR);
        board.setPiece(3,7,BK);
        board.setPiece(5,7,BB);
        assertEquals(false,board.move(7,7,5,7));
    }

    public void testMoveRookPutKingInCheck() throws Exception{
        Board board=new Board();
        Rook BR=new Rook(1);
        King BK=new King(1);
        Rook WR=new Rook(0);
        board.setPiece(5,7,BR);
        board.setPiece(3,7,BK);
        board.setPiece(7,7,WR);

        assertEquals(false,board.move(5,7,5,6));
    }

    public void testMoveBishopEmptyValid() throws Exception{
        Board board=new Board();
        Bishop WB=new Bishop(0);
        King WK=new King(0);
        board.setPiece(2,0,WB);
        board.setPiece(3,0,WK);
        assertEquals(true,board.move(2,0,5,3));

    }

    public void testMoveBishopEmptyInValid() throws Exception{
        Board board=new Board();
        Bishop WB=new Bishop(0);
        King WK=new King(0);
        board.setPiece(2,0,WB);
        board.setPiece(3,0,WK);
        assertEquals(false,board.move(2,0,4,3));
        assertEquals(false,board.move(2,0,9,3));
    }

    public void testMoveBishopCapture() throws Exception {
        Board board=new Board();
        Bishop WB=new Bishop(0);
        King WK=new King(0);
        Pawn BP=new Pawn(1);
        board.setPiece(2,0,WB);
        board.setPiece(3,0,WK);
        board.setPiece(4,2,BP);
        assertEquals(true,board.move(2,0,4,2));

    }

    public void testMoveBishopCaptureSameColor() throws Exception {
        Board board=new Board();
        Bishop WB=new Bishop(0);
        King WK=new King(0);
        Pawn WP=new Pawn(0);
        board.setPiece(2,0,WB);
        board.setPiece(3,0,WK);
        board.setPiece(4,2,WP);
        assertEquals(false,board.move(2,0,4,2));

    }

    public void testMoveBishopPutKingInCheck() throws Exception{
        Board board=new Board();
        Bishop WB=new Bishop(0);
        King WK=new King(0);
        Rook BR=new Rook(1);
        board.setPiece(2,0,WB);
        board.setPiece(3,0,WK);
        board.setPiece(1,0,BR);
        assertEquals(false,board.move(2,0,3,1));


    }

    public void testMoveKnightEmptyValid() throws Exception {
        Board board=new Board();
        Knight BH=new Knight(1);
        King BK=new King(1);
        board.setPiece(1,7,BH);
        board.setPiece(3,7,BK);
        assertEquals(true,board.move(1,7,3,6));
        assertEquals(true,board.move(3,6,4,4));
    }

    public void testMoveKnightEmptyInValid() throws Exception{
        Board board=new Board();
        Knight BH=new Knight(1);
        King BK=new King(1);
        board.setPiece(1,7,BH);
        board.setPiece(3,7,BK);
        assertEquals(false,board.move(1,7,0,9));
        assertEquals(false,board.move(1,7,2,7));

    }

    public void testMoveKnightCapture() throws Exception {
        Board board=new Board();
        Knight BH=new Knight(1);
        King BK=new King(1);
        Rook WR=new Rook(0);

        board.setPiece(1,7,BH);
        board.setPiece(3,7,BK);
        board.setPiece(0,5,WR);
        assertEquals(true,board.move(1,7,0,5));
    }

    public void testMoveKnightCaptureSameColor() throws Exception {
        Board board=new Board();
        Knight BH=new Knight(1);
        King BK=new King(1);
        Rook BR=new Rook(1);

        board.setPiece(1,7,BH);
        board.setPiece(3,7,BK);
        board.setPiece(0,5,BR);
        assertEquals(false,board.move(1,7,0,5));
    }

    public void testMoveKnightPutKingInCheck() throws Exception{
        Board board=new Board();
        Knight BH=new Knight(1);
        King BK=new King(1);
        Bishop WB=new Bishop(0);

        board.setPiece(4,6,BH);
        board.setPiece(3,7,BK);
        board.setPiece(5,5,WB);
        assertEquals(false,board.move(4,6,6,7));
    }

    public void testMovePawnEmptyValid() throws Exception{
        Board board=new Board();
        Pawn WP=new Pawn(0);
        King WK=new King(0);
        board.setPiece(4,1,WP);
        board.setPiece(3,0,WK);
        assertEquals(true,board.move(4,1,4,3));
        assertEquals(true,board.move(4,3,4,4));
    }

    public void testMovePawnEmptyInValid() throws Exception{
        Board board=new Board();
        Pawn WP=new Pawn(0);
        King WK=new King(0);
        board.setPiece(4,1,WP);
        board.setPiece(3,0,WK);
        assertEquals(false,board.move(4,1,5,2));
        assertEquals(false,board.move(4,1,4,0));
        assertEquals(false,board.move(4,1,5,0));
        assertEquals(false,board.move(4,1,5,1));
        assertEquals(false,board.move(4,1,3,1));
    }

    public void testMovePawnCapture() throws Exception{
        Board board=new Board();
        Pawn WP=new Pawn(0);
        King WK=new King(0);
        Bishop BB=new Bishop(1);
        board.setPiece(4,1,WP);
        board.setPiece(3,0,WK);
        board.setPiece(5,2,BB);
        assertEquals(true,board.move(4,1,5,2));
    }

    public void testMovePawnCaptureInvalid() throws Exception{
        Board board=new Board();
        Pawn WP=new Pawn(0);
        King WK=new King(0);
        Bishop BB=new Bishop(1);
        Pawn BP=new Pawn(1);
        Rook BR=new Rook(1);
        Knight BH=new Knight(1);
        Bishop WB=new Bishop(0);


        board.setPiece(4,1,WP);
        board.setPiece(3,0,WK);


        //skip vertical two
        board.setPiece(4,3,BB);
        assertEquals(false,board.move(4,1,4,3));

        //skip vetical one
        board.setPiece(4,2,BP);
        assertEquals(false,board.move(4,1,4,2));

        //skip back 1
        board.setPiece(4,0,BR);
        assertEquals(false,board.move(4,1,4,0));

        //skip hrizontal 1
        board.setPiece(3,1,BH);
        assertEquals(false,board.move(4,1,3,1));

        //sam color
        board.setPiece(5,2,WB);
        assertEquals(false,board.move(4,1,5,2));

    }

    public void testMovePawnPutKingInCheck() throws Exception{
        Board board=new Board();
        Pawn WP=new Pawn(0);
        King WK=new King(0);
        Bishop BB=new Bishop(1);
        board.setPiece(4,1,WP);
        board.setPiece(3,0,WK);
        board.setPiece(5,2,BB);
        assertEquals(false,board.move(4,1,4,2));
    }

    public void testMoveQueenEmptyValid() throws Exception{
        Board board=new Board();
        Queen WQ=new Queen(0);
        King WK=new King(0);
        board.setPiece(4,0,WQ);
        board.setPiece(3,0,WK);

        //forward vertically
        assertEquals(true,board.move(4,0,4,7));

        //horizontal
        assertEquals(true,board.move(4,7,0,7));

        //diagnal down
        assertEquals(true,board.move(0,7,4,3));

        //diagnal up

        assertEquals(true,board.move(4,3,6,5));

    }

    public void testMoveQueenEmptyInValid() throws Exception{
        Board board=new Board();
        Queen BQ=new Queen(1);
        King BK=new King(1);
        board.setPiece(4,7,BQ);
        board.setPiece(3,7,BK);

        //off bound
        assertEquals(false,board.move(4,7,4,8));
        //block
        Pawn WP=new Pawn(0);
        board.setPiece(2,7,WP);
        assertEquals(false,board.move(4,7,2,7));

    }

    public void testMoveQueenCapture() throws Exception{
        Board board=new Board();
        Queen BQ=new Queen(1);
        King BK=new King(1);
        board.setPiece(4,7,BQ);
        board.setPiece(3,7,BK);

        //vertical
        Rook WR=new Rook(0);
        board.setPiece(4,3,WR);
        assertEquals(true,board.move(4,7,4,3));
    }

    public void testMoveQueenCaptureInvalid() throws Exception{
        Board board=new Board();
        Queen BQ=new Queen(1);
        King BK=new King(1);
        board.setPiece(4,7,BQ);
        board.setPiece(3,7,BK);
        Pawn BP=new Pawn(1);
        board.setPiece(4,6,BP);
        assertEquals(false,board.move(4,7,4,6));
    }

    public void testMoveQueenPutKingInCheck() throws Exception{
        Board board=new Board();
        Queen BQ=new Queen(1);
        King BK=new King(1);
        board.setPiece(4,6,BQ);
        board.setPiece(3,7,BK);
        Bishop WB=new Bishop(0);
        board.setPiece(5,5,WB);
        assertEquals(false,board.move(4,6,4,7));
    }

    public void testCheckmatewithRook() throws Exception{
        Board board=new Board();
        Queen WQ=new Queen(0);
        King BK=new King(1);
        Rook WR=new Rook(0);
        board.setPiece(5,4,WQ);
        board.setPiece(7,0,WR);
        board.setPiece(7,4,BK);
        assertEquals(true,board.isCheckmate(1));

    }

    public void testCheckCanEscape() throws Exception {
        Board board=new Board();
        Queen WQ=new Queen(0);
        King BK=new King(1);
        Rook WR=new Rook(0);
        board.setPiece(5,4,WQ);
        board.setPiece(6,0,WR);
        board.setPiece(7,4,BK);
        assertEquals(false,board.isCheckmate(1));
    }

    public void testCheckmateKingCanCapture() throws Exception {
        Board board=new Board();
        Queen WQ=new Queen(0);
        King BK=new King(1);
        Rook WR=new Rook(0);
        Pawn BP=new Pawn(1);


        board.setPiece(6,4,WQ);
        board.setPiece(7,4,BK);
        board.setPiece(7,0,WR);
        board.setPiece(6,3,BP);

        assertEquals(false,board.isCheckmate(1));
    }

    public void testCheckmateOtherCanCapture() throws Exception{
        Board board=new Board();
        Queen WQ=new Queen(0);
        King BK=new King(1);
        Pawn BP=new Pawn(1);
        Rook BR=new Rook(1);
        Knight BH=new Knight(1);

        board.setPiece(2,2,WQ);
        board.setPiece(0,0,BK);
        board.setPiece(0,1,BP);
        board.setPiece(1,0,BR);
        board.setPiece(4,1,BH);


        assertEquals(false,board.isCheckmate(1));
    }

    public void testCheckmateCanBlock1() throws Exception{
        Board board=new Board();
        Queen WQ=new Queen(0);
        King BK=new King(1);
        Pawn BP=new Pawn(1);
        Bishop BB1=new Bishop(1);
        Bishop BB=new Bishop(1);

        board.setPiece(2,2,WQ);
        board.setPiece(0,0,BK);
        board.setPiece(0,1,BP);
        board.setPiece(1,0,BB1);
        board.setPiece(0,2,BB);
        assertEquals(false,board.isCheckmate(1));

    }

    public void testCheckmateCanBlock2() throws Exception{
        Board board=new Board();
        Queen WQ=new Queen(0);
        King BK=new King(1);
        Pawn BP=new Pawn(1);
        Rook BR=new Rook(1);

        board.setPiece(3,3,WQ);
        board.setPiece(0,0,BK);
        board.setPiece(0,1,BP);
        board.setPiece(1,0,BR);
        assertEquals(false,board.isCheckmate(1));
    }

    public void testStalemate() throws Exception{
        Board board=new Board();
        Queen WQ=new Queen(0);
        King WK=new King(0);
        King BK=new King(1);

        board.setPiece(6,5,WK);
        board.setPiece(5,6,WQ);
        board.setPiece(7,7,BK);
        //board.printBoard();

        assertEquals(true,board.isStalemate(1));

    }

    public void testStalemate2() throws Exception{
        Board board=new Board();
        Queen WQ=new Queen(0);
        Queen BQ=new Queen(1);
        King WK=new King(0);
        King BK=new King(1);
        Pawn pawn1=new Pawn(1);
        Pawn pawn2=new Pawn(1);
        Pawn pawn3=new Pawn(1);
        Pawn pawn4=new Pawn(1);
        Pawn pawnW=new Pawn(0);
        Rook rook1=new Rook(1);
        Rook rook2=new Rook(1);
        Knight knight=new Knight(1);
        Bishop bishop=new Bishop(1);


        board.setPiece(3,0,WK);
        board.setPiece(3,5,WQ);
        board.setPiece(1,5,BK);
        board.setPiece(0,6,BQ);
        board.setPiece(0,4,pawn1);
        board.setPiece(1,6,pawn2);
        board.setPiece(2,5,pawn3);
        board.setPiece(3,6,pawn4);
        board.setPiece(0,3,pawnW);
        board.setPiece(0,7,rook1);
        board.setPiece(0,5,rook2);
        board.setPiece(1,7,knight);
        board.setPiece(2,7,bishop);


       // board.printBoard();

        assertEquals(true,board.isStalemate(1));

    }


}