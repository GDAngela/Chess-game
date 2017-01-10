package Classes;

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
 * Created by jingjinghuang on 9/13/16.
 *
 * citation https://www.youtube.com/watch?v=w9HR4VJ8DAw&index=29&list=PLOJzCFLZdG4zk5d-1_ah2B4kqZSeIlWtt
 *
 */
public class ChessGUI {
    private final JFrame frame;
    private  boardPanel board;
    private Board chessBoard;
    private JMenuBar menu;
    private JButton customizeBoardButton;
    private JButton resetButton;
    private JButton forfeitWhiteButton;
    private JButton forfeitBlackButton;
    private JButton undoButton;
    private JLabel whitePlayerLabel;
    private JLabel whiteScoreLabel;
    private JLabel blackPlayerLabel;
    private JLabel blackScoreLabel;




    private static Dimension OUTER_FRAME=new Dimension(600,700);
    private static Dimension BOARD_DIMENSION=new Dimension(400,350);
    private static Dimension TILE_DIMENSION=new Dimension(10,10);
    public final  int BOARD_LENGTH=8;
    public final int BOARD_WIDTH=8;
    private static String ICONPATH="src/Classes/Icon/";
    private static String ROW_LABEL = "ABCDEFGH";


    /**
     * Constructor
     * @param chessBoard
     */
    public ChessGUI(Board chessBoard){
        //Initialize to standard chessboard
        this.chessBoard=chessBoard;
        //Initialize frame
        this.frame=new JFrame("chessboard");
        this.frame.setLayout(new BorderLayout());
        this.frame.setSize(OUTER_FRAME);
        this.frame.setLayout(null);

        //add tool bar
        menu=new JMenuBar();
        menu.setBounds(0,0,600,25);
        customizeBoardButton=new JButton("Customize board");
        resetButton = new JButton("Restart");
        forfeitWhiteButton = new JButton("White forfeit");
        forfeitBlackButton=new JButton("Black forfeit");
        undoButton = new JButton("Undo");
        menu.add(customizeBoardButton);
        menu.add(resetButton);
        menu.add(forfeitWhiteButton);
        menu.add(forfeitBlackButton);
        menu.add(undoButton);
        frame.add(menu);

        //add score board
        JPanel scoreBoard=new JPanel(new GridLayout(0,4));
        frame.add(scoreBoard);
        scoreBoard.setBounds(0,25,600,25);
        whitePlayerLabel=new JLabel("Player 1 ",SwingConstants.RIGHT);
        whiteScoreLabel=new JLabel("0",SwingConstants.CENTER);
        blackPlayerLabel=new JLabel("Player 2",SwingConstants.RIGHT);
        blackScoreLabel=new JLabel("0",SwingConstants.CENTER);
        scoreBoard.add(whitePlayerLabel);
        scoreBoard.add(whiteScoreLabel);
        scoreBoard.add(blackPlayerLabel);
        scoreBoard.add(blackScoreLabel);


        //initialize the board
        this.board=new boardPanel();
        this.frame.add(this.board);
        this.board.setBounds(0,50,600,600);

        this.frame.setVisible(true);
    }

    /**
     *
      * @param name
     */
    public void updateWhiteName(String name){
        whitePlayerLabel.setText(name);
    }

    /**
     *
     * @param name
     */
    public void updateBlackName(String name){
        blackPlayerLabel.setText(name);
    }

    /**
     *
      * @param score
     */
    /**
     *
      * @param score
     */
    public void updateWhiteScore( int score){
        whiteScoreLabel.setText(Integer.toString(score));
    }

    /**
     *
     * @param score
     */
    public void updateBlackScore( int score){
        blackScoreLabel.setText(Integer.toString(score));
    }

    /**
     *
     * @param chessBoard
     */
    public void reset(Board chessBoard){
        //reset chessboard
        this.chessBoard=chessBoard;
        //remove original board panal
        this.frame.remove(board);
        this.board=new boardPanel();
        //add a new board panal
        this.frame.add(board);
        this.board.setBounds(0,50,600,600);
        this.frame.setVisible(true);
    }

    /**
     *
      * @param e
     */
    public void customizeBoardListener(ActionListener e){
        customizeBoardButton.addActionListener(e);
    }

    /**
     *
      * @param e
     */
    public void resetListener(ActionListener e) {
        resetButton.addActionListener(e);
    }


    /**
     *
     * @param e
     */
    public void forfeitWhiteListener(ActionListener e) {
        forfeitWhiteButton.addActionListener(e);
    }

    /**
     *
      * @param e
     */
    public void forfeitBlackListener(ActionListener e) {
        forfeitBlackButton.addActionListener(e);
    }

    /**
     *
     * @param e
     */
    public void undoListener(ActionListener e) {
        undoButton.addActionListener(e);
    }

    /**
     *
     * @return
     */
    public JFrame getFrame(){
        return this.frame;
    }

    /**
     * board panel constructor with
      * @return
     */
    public boardPanel getBoardPanel(){
        return this.board;
    }

    /**board panel constructor with tile button
     */

    public class boardPanel extends JPanel{
        tilePanel[][] tilesList=new tilePanel[BOARD_LENGTH][BOARD_WIDTH];
        boardPanel(){
            super(new GridLayout(BOARD_LENGTH+1,BOARD_WIDTH+1));
            for(int i=0;i<BOARD_LENGTH+1;i++){
                for(int j=0;j<BOARD_WIDTH+1;j++) {
                    if(j==0){
                        JPanel numPanel=new JPanel();
                        if(i==8){
                            numPanel.add(new JLabel(""));
                        }else {
                            numPanel.add(new JLabel("" + (9 - i-1),
                                    SwingConstants.CENTER));
                        }
                        this.add(numPanel);
                    } else if(i==8){
                        JPanel letterPanel=new JPanel();
                        letterPanel.add(new JLabel(ROW_LABEL.substring(j-1,j),SwingConstants.CENTER));
                        this.add(letterPanel);
                    }else {
                        tilePanel tile = new tilePanel(j-1, 7-i);
                        //add to list
                        this.tilesList[i][j-1] = tile;
                        //add to boardPanel
                        this.add(tile);
                    }
                }
            }

            printTileList();
            setPreferredSize(BOARD_DIMENSION);
            validate();
        }

        public void printTileList(){
            System.out.println("PRINGTING GUI**************");
            for(int i=0;i<BOARD_LENGTH;i++){
                for(int j=0;j<BOARD_WIDTH;j++){
                    System.out.print(this.tilesList[i][j].getPieceName());
                    System.out.print(" ");
                }
                System.out.println();
            }
        }

        public tilePanel getTile(int x,int y){
            return tilesList[7-y][x];
        }


    }

    /**
     *
      */
    public class tilePanel extends JButton{
        private  int tileX;
        private  int tileY;
        private String pieceName="NL";

        tilePanel(int x, int y){
            super();
            this.tileX=x;
            this.tileY=y;
            setPreferredSize(TILE_DIMENSION);
            Insets buttonMargin = new Insets(0, 0, 0, 0);
            this.setMargin(buttonMargin);
            this.assignColor(x, y);
            assignIcon(chessBoard, x, y);
            validate();
        }

        public int getTileX(){
            return tileX;
        }

        public int getTileY(){
            return tileY;
        }

        /**
         *
         * @param x
         * @param y
         */
        private void assignColor(int x,int y) {

            this.setOpaque(true);
            this.setBorderPainted(false);

            if ((x + y) % 2 == 1) {
                setBackground(Color.WHITE);
            } else {
                setBackground(Color.GRAY);
            }


        }

        /**
         *
          * @param board
         * @param x
         * @param y
         */
        public void assignIcon(Board board,int x,int y){
                this.removeAll();
                // board panel is vertically flipped chessboard
                if(!chessBoard.isEmpty(x,y)){
                    String name=chessBoard.getPiece(x,y).getName();
                    try{
                        final BufferedImage image=ImageIO.read(new File(ICONPATH+name+".gif"));
                        ImageIcon ic=new ImageIcon(image);
                        this.setIcon(ic);
                        this.pieceName=name;

                    }catch(IOException exception){
                        exception.printStackTrace();
                    }
                }else{
                    setIcon(null);
                    this.pieceName="NL";
                }
        }


        /**
         *
         * @return
         */
        public String getPieceName(){
            return this.pieceName;
        }

        /**
         *
         * @param name
         */
        public void setPieceName(String name){
            System.out.print("In setPieceName: ");
            this.pieceName=name;
            System.out.println(this.pieceName);
        }


    }


    public void update(){
        this.frame.setVisible(true);
    }



}
