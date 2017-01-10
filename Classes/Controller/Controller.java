package Classes;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.border.*;
import java.util.concurrent.TimeUnit;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;


/**
 * Created by jingjinghuang on 9/18/16.
 */
public class Controller {
    private Player playerWhite;
    private Player playerBlack;
    private Player currentPlayer;
    private Board board;
    private ChessGUI view;
    private ChessGUI.tilePanel source;
    private ChessGUI.tilePanel dest;
    private Pieces preSource;
    private Pieces preDest;
    private int preSourceX;
    private int preSourceY;
    private int preDestX;
    private int preDestY;
    private int currentWhiteScore;
    private int currentBlackScore;
    private int length;
    private int width;



    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }


    /**
     *
     * @param length
     * @param width
     */
    public Controller( final int length,final int width){
        this.length=length;
        this.width=width;
        board=new Board(length,width);
        view=new ChessGUI(board);
        currentBlackScore=0;
        currentWhiteScore=0;

        //get name from user
        getUserName();

        //set current player to be white
        currentPlayer=playerWhite;

        //update username on view
        view.updateBlackName(playerBlack.getName());
        view.updateWhiteName(playerWhite.getName());


        /** attach move listener to each tile. This is an implicit game loop as well
         * it controls taking turns and ensure that a player can only move their piece, not the opponent's
         */
        attachActionToTile();


        view.customizeBoardListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeBoard();
            }
        });

        //menu listener restart button
        view.resetListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                //update score tied so both get 1 score
                currentBlackScore+=1;
                currentWhiteScore+=1;
                view.updateBlackScore(currentBlackScore);
                view.updateWhiteScore(currentWhiteScore);
                JOptionPane.showMessageDialog(view.getFrame(),
                        "Tied");
                reset(length, width);



            }
        });

        view.forfeitWhiteListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                forfeitWhite();
            }
        });

        view.forfeitBlackListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                forfeitBlack();
            }
        });

        view.undoListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                undo();
            }
        });

    }

    /**
     * get name from user
     */
    public void getUserName(){
        // create pop up window
        JTextField White = new JTextField();
        JTextField Black = new JTextField();
        Object[] message = {
                "White user name:", White,
                "Black user name:", Black
        };
        int option = JOptionPane.showConfirmDialog(view.getFrame(), message, "User name", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            // get strings from Jtextfield and set 2 player
            String whiteName=White.getText();
            String blackName=Black.getText();
            if(!whiteName.equals("")) {
                playerWhite = new Player(whiteName, 0);
            }else{
                playerWhite=new Player("WHITE",0);
            }
            if(!blackName.equals("")) {
                playerBlack = new Player(blackName, 1);
            }else{
                playerBlack=new Player("BLACK",1);
            }
        }else{
            playerWhite=new Player("WHITE",0);
            playerBlack=new Player("BLACK",1);
        }
    }

    public void customizeBoard(){
        //create pop up window to get coordinates
        JTextField princessBlackX = new JTextField();
        JTextField princessWhiteX = new JTextField();
        JTextField princessWhiteY= new JTextField();
        JTextField princessBlackY = new JTextField();

        JTextField empressBlackX = new JTextField();
        JTextField empressWhiteX = new JTextField();
        JTextField empressWhiteY= new JTextField();
        JTextField empressBlackY = new JTextField();
        Object[] message = {
                "princessBlackX:", princessBlackX,
                "princessBlackY:", princessBlackY,
                "princessWhiteX:", princessWhiteX,
                "princessWhiteY:", princessWhiteY,

                "empressBlackX:", empressBlackX,
                "empressBlackY:", empressBlackY,
                "empressWhiteX:", empressWhiteX,
                "empressWhiteY:", empressWhiteY
        };
        JOptionPane.showConfirmDialog(view.getFrame(), message, "User name", JOptionPane.DEFAULT_OPTION);
        int pBlackX=-1;
        int pBlackY=-1;
        int pWhiteX=-1;
        int pWhiteY=-1;
        int eBlackX=-1;
        int eBlackY=-1;
        int eWhiteX=-1;
        int eWhiteY=-1;

        System.out.println("pBlackX is "+princessBlackX.getText());
        //check if input is null and set coordinates
        if(!princessBlackX.getText().equals("")){
            pBlackX=Integer.parseInt(princessBlackX.getText());
        }
        if(!princessBlackY.getText().equals("")){
            pBlackY=Integer.parseInt(princessBlackY.getText());
        }
        if(!princessWhiteX.getText().equals("")){
            pWhiteX=Integer.parseInt(princessWhiteX.getText());
        }
        if(!princessWhiteY.getText().equals("")){
            pWhiteY=Integer.parseInt(princessWhiteY.getText());
        }
        if(!empressBlackX.getText().equals("")){
            eBlackX=Integer.parseInt(empressBlackX.getText());
        }
        if(!empressBlackY.getText().equals("")){
            eBlackY=Integer.parseInt(empressBlackY.getText());
        }
        if(!empressWhiteX.getText().equals("")){
            eWhiteX=Integer.parseInt(empressWhiteX.getText());
        }
        if(!empressWhiteY.getText().equals("")){
            eWhiteY=Integer.parseInt(empressWhiteY.getText());
        }

        //create a new standard board and set new pieces
        this.board=new Board(length,width);
        if(pWhiteX!=-1 && pWhiteY!=-1){
            board.setPiece(pWhiteX,pWhiteY,new Princess(0));
        }
        if(pBlackX!=-1 && pBlackY!=-1){
            board.setPiece(pBlackX,pBlackY,new Princess(1));
        }
        if(eWhiteX!=-1 && eWhiteY!=-1){
            board.setPiece(eWhiteX,eWhiteY,new Empress(0));
        }
        if(eBlackX!=-1 && eBlackY!=-1){
            board.setPiece(eBlackX,eBlackY,new Empress(1));
        }
        view.reset(board);
        attachActionToTile();
    }

    /**
     * reset model board and view
      * @param length
     * @param width
     */
    public void reset(int length,int width){
        preSource=null;
        preDest=null;
        currentPlayer=playerWhite;
        board=new Board(length,width);
        view.reset(board);
        //reattach the action listener to each tile
        attachActionToTile();
    }

    /**
     * update score and reset view
      */
    public void forfeitWhite(){
        JOptionPane.showMessageDialog(view.getFrame(), "Black wins! ");
        //update score and view
        currentBlackScore+=1;
        view.updateBlackScore(currentBlackScore);
        reset(length, width);
    }

    public void forfeitBlack(){
        JOptionPane.showMessageDialog(view.getFrame(), "White wins! ");
        //update score and view
        currentWhiteScore+=1;
        view.updateWhiteScore(currentWhiteScore);
        reset(length, width);
    }


    /**
     *
     */
    public void undo(){
        if(preSource!=null) {
            int curColor=preSource.getColor();
            // set current player
            if(curColor==1){
                currentPlayer=playerBlack;
            }else{
                currentPlayer=playerWhite;
            }
            // set presource and predest
            board.setPiece(preSourceX, preSourceY, preSource);
            board.setPiece(preDestX, preDestY, preDest);
            //update view
            view.getBoardPanel().getTile(preSourceX, preSourceY).assignIcon(board, preSourceX, preSourceY);
            view.getBoardPanel().getTile(preDestX, preDestY).assignIcon(board, preDestX, preDestY);
            view.getFrame().setVisible(true);
            //reset presource and predest
            preSource=null;
            preDest=null;
        }else{
            JOptionPane.showMessageDialog(view.getFrame(),
                    "Can't undo! ");
        }
    }

    /**
     * This is an even driven game loop it make sure players are taking turns and only move their own piece
     */
    public void attachActionToTile(){
        for(int i = 0; i < view.BOARD_LENGTH; i ++){
            for (int j = 0; j < view.BOARD_WIDTH; j++){
                //each tile will attach its button action listener
                ChessGUI.tilePanel currentTile=view.getBoardPanel().getTile(i, j);
                //get the coordinates of current tile
                final int I = currentTile.getTileX();
                final int J = currentTile.getTileY();
                currentTile.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(final MouseEvent e) {

                        if (isLeftMouseButton(e)){
                            //if source is null set source first
                            if(source==null){
                                source=view.getBoardPanel().getTile(I,J);
                                Pieces current=board.getPiece(I,J);
                                //if clicked on empty nothing happen source reset to null
                                if(current==null ){
                                    source=null;
                                }else{
                                    //if clicked piece is not empty check if its own piece.if not
                                    //give warning
                                    if(current.getColor()!=currentPlayer.getColor()){
                                        System.out.println("its "+currentPlayer.getColor()+" 's turn");
                                        source=null;
                                        //todo popup warning
                                        String warning;
                                        if(currentPlayer.getColor()==0){
                                            warning="It's White's turn";
                                        }else{
                                            warning="It's Black's turn";
                                        }

                                        JOptionPane.showMessageDialog(view.getFrame(),
                                                warning);
                                    }
                                }
                                /*
                                if(source!=null) {
                                    System.out.print("set source source is ");
                                    System.out.println(source.getPieceName());
                                }else{
                                    System.out.println("source is set to null");
                                }
                                */
                            }else{
                                //after made sure we have a source,set dest
                                dest=view.getBoardPanel().getTile(I,J);
                                int sourceX=source.getTileX();
                                int sourceY=source.getTileY();
                                int destX=dest.getTileX();
                                int destY=dest.getTileY();

                                //memorize move set presource and predest and their coordinates
                                preSource=board.getPiece(sourceX,sourceY);
                                preDest=board.getPiece(destX,destY);
                                preSourceX=sourceX;
                                preSourceY=sourceY;
                                preDestX=destX;
                                preDestY=destY;

                                /*
                                System.out.println("presource is set to be "+preSource.getName());
                                if(preDest!=null){
                                    System.out.println("preDest is set to be "+preDest.getName());
                                }
                                */

                                //update board model
                                boolean success=board.move(sourceX,sourceY,destX,destY);
                                /*
                                System.out.println("After presource is set to be "+preSource.getName());
                                if(preDest!=null){
                                    System.out.println("After preDest is set to be "+preDest.getName());
                                }
                                board.printBoard();
                                */
                                //check if move success
                                if(success) {
                                    //System.out.println("succuss")
                                    //if success update view
                                    view.getBoardPanel().getTile(destX,destY).assignIcon(board,destX,destY);
                                    view.getBoardPanel().getTile(sourceX,sourceY).assignIcon(board,sourceX,sourceY);
                                    view.getBoardPanel().printTileList();
                                    //reset source and dest
                                    source=null;
                                    dest=null;
                                    //display updated view
                                    view.getFrame().setVisible(true);

                                    //check condition
                                    int curColor=1-currentPlayer.getColor();
                                    boolean isCheck=board.isChecked(curColor);
                                    boolean isCheckMate=board.isCheckmate(curColor);
                                    boolean isStalemate=board.isStalemate(curColor);

                                    //change player
                                    if(currentPlayer.getColor()==1){
                                        currentPlayer=playerWhite;
                                    }else{
                                        currentPlayer=playerBlack;
                                    }
                                    //check is in check
                                    if(isCheck && (!isCheckMate) ){
                                        JOptionPane.showMessageDialog(view.getFrame(),
                                                "Check! ");
                                    }else if(isCheckMate){
                                        //check if checkmate
                                        JOptionPane.showMessageDialog(view.getFrame(),
                                                "Checkmate! ");
                                        //end game
                                        if(currentPlayer.getColor()==1){
                                            currentBlackScore+=1;
                                            view.updateBlackScore(currentBlackScore);
                                        }else{
                                            currentWhiteScore+=1;
                                            view.updateBlackScore(currentWhiteScore);
                                        }
                                        reset(length,width);


                                    }else if(isStalemate){
                                        //check if stalemate
                                        JOptionPane.showMessageDialog(view.getFrame(),
                                                "Stalemate! ");
                                        //end game
                                        currentWhiteScore+=1;
                                        currentBlackScore+=1;
                                        view.updateBlackScore(currentWhiteScore);
                                        view.updateBlackScore(currentBlackScore);
                                        reset(length,width);
                                    }
                                    /*
                                    System.out.println("isCheck is "+isCheck+" checkmate is "+ isCheckMate + " is " +
                                            "stalemate is " + isStalemate);
                                    */



                                }else{
                                    System.out.println("faild");
                                    //warn it's a illgle move
                                    JOptionPane.showMessageDialog(view.getFrame(),
                                            "Illegal Move! ");
                                    //reset
                                    source=null;
                                    dest=null;
                                    preSource=null;
                                    preDest=null;
                                }

                            }
                        }
                    }

                    @Override
                    public void mousePressed(final MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(final MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(final MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(final MouseEvent e) {

                    }


                });
            }
        }

    }


    public static void main(String[] args)
    {
        Controller ctrl = new Controller(8,8);
    }


}
