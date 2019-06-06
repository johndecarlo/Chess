import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener
{
   public static JLabel player1_taken;    //Label for player1_taken
   public static JLabel player2_taken;    //Label for player2_taken
   public static JLabel last_turn;        //Message for players
   public static JLabel message;          //Message for players
   
   private ImageIcon whitePawn = new ImageIcon("pieces/whitePawn.png");  //White Pawn Image
   private ImageIcon blackPawn = new ImageIcon("pieces/blackPawn.png");  //Black Pawn Image
   private ImageIcon whiteKnight = new ImageIcon("pieces/whiteKnight.png"); //White Knight Image
   private ImageIcon blackKnight = new ImageIcon("pieces/blackKnight.png"); //Black Knight Image
   private ImageIcon whiteBishop = new ImageIcon("pieces/whiteBishop.png"); //White Bishop Image
   private ImageIcon blackBishop = new ImageIcon("pieces/blackBishop.png"); //Black Bishop Image
   private ImageIcon whiteRook = new ImageIcon("pieces/whiteRook.png");  //White Rook Image
   private ImageIcon blackRook = new ImageIcon("pieces/blackRook.png");  //Black Rook Image
   private ImageIcon whiteQueen = new ImageIcon("pieces/whiteQueen.png");   //White Queen Image
   private ImageIcon blackQueen = new ImageIcon("pieces/blackQueen.png");   //Black Queen Image
   private ImageIcon whiteKing = new ImageIcon("pieces/whiteKing.png");  //White King Image
   private ImageIcon blackKing = new ImageIcon("pieces/blackKing.png");  //Black King Image
   private ImageIcon blankDark = new ImageIcon("pieces/blankDarkBrown.jpg");   //Dark Brown Tile
   private ImageIcon blankLight = new ImageIcon("pieces/blankLightBrown.jpg"); //Light Brown Tile

   private static final int SIZE=80;	//size of pieces being drawn
 
   public static Piece[][] board;               //Our board that holds all our pieces
   public static ArrayList<Piece> whiteTaken;   //White pieces that have been taken
   public static ArrayList<Piece> blackTaken;   //Black pieces that have been taken
   public static boolean gameIsOver;            //Whether the game has ended or not
   public static String player1, player2;       //
   public static int playCount;
   public static int blackCount, whiteCount;
   public static boolean selected;
   public static int r1, c1, r2, c2, r3, c3;
   private static int playerR;			//start row for the player
   private static int playerC;			//start col for the player
     
   protected static int mouseX;			//locations for the mouse pointer
   protected static int mouseY;

   public Piece getLocation(int row, int col) {
      return board[row][col];
   }

   public ChessBoard() {
      addMouseListener( this );
      addMouseMotionListener( this );
      mouseX = 0;
      mouseY = 0;
      setUpBoard();	
   }
   
   public JLabel getPlayer1Taken() {
      return player1_taken;
   }
   
   public JLabel getPlayer2Taken() {
      return player2_taken;
   }
   
   public JLabel getLastTurn() {
      return last_turn;
   }
   
   public JLabel getMessage() {
      return message;
   }
   
   public static boolean checkmate(String color) {
      int[] kingPos = new int[2];
      kingPos = findKing(color);
      int x = kingPos[0];
      int y = kingPos[1];
      Piece king = board[x][y];
      if(king != null && king.moveIsValid(board, x, y, x + 1, y, false) == "kingAtRisk" && king.moveIsValid(board, x, y, x - 1, y, true) == "kingAtRisk" && 
      king.moveIsValid(board, x, y, x, y + 1, true) == "kingAtRisk" && king.moveIsValid(board, x, y, x, y - 1, true) == "kingAtRisk" &&
      king.moveIsValid(board, x, y, x + 1, y + 1, true) == "kingAtRisk" && king.moveIsValid(board, x, y, x - 1, y - 1, true) == "kingAtRisk" && 
      king.moveIsValid(board, x, y, x - 1, y + 1, true) == "kingAtRisk" && king.moveIsValid(board, x, y, x + 1, y - 1, true) == "kingAtRisk") {
         return true;
      }
      return false;
   }

   public static boolean check(String color) {
      int[] kingPos = new int[2];
      kingPos = findKing(color);
      int x = kingPos[0];
      int y = kingPos[1];
      Piece king = board[x][y];
      if(king != null && king.kingInCheck(board, x, y) == true) {
         return true;
      }
      return false; 
   }

   public static boolean gameIsOver() {
      if(checkmate("Black") == true)
         return true;
      else if(checkmate("White") == true)
         return true;
      else
         return false;
   }

   public static int[] findKing(String color) {
      int [] pos = new int[2];
      for(int i = 0; i < 8; i++) {
         for(int j = 0; j < 8; j++) {
            Piece temp = board[i][j];
            if(temp != null && temp.getType().equals("K") && temp.getColor().equals(color)) {
               pos[0] = temp.getRow();
               pos[1] = temp.getCol();
               return pos;
            }
         }
      }
      return pos;
   }


   public static void setUpBoard() {
      board = new Piece[8][8];
      whiteTaken = new ArrayList();
      blackTaken = new ArrayList();
      gameIsOver = false;
      playCount = 0;
      player1 = "white";
      player2 = "Black";
      selected = false;
      blackCount = 0;
      whiteCount = 0;
      r1 = -1;
      c1 = -1;
      r2 = -1;
      c2 = -1;
      r3 = -1;
      c3 = -1;
      
      //Black Pieces set up
      board[0][0] = new Rook("Black", 0, 0); 
      board[0][1] = new Knight("Black", 0, 1); 
      board[0][2] = new Bishop("Black", 0, 2); 
      board[0][3] = new Queen("Black", 0, 3); 
      board[0][4] = new King("Black", 0, 4);
      board[0][5] = new Bishop("Black", 0, 5); 
      board[0][6] = new Knight("Black", 0, 6); 
      board[0][7] = new Rook("Black", 0, 7);
      board[1][0] = new Pawn("Black", 1, 0); 
      board[1][1] = new Pawn("Black", 1, 1); 
      board[1][2] = new Pawn("Black", 1, 2); 
      board[1][3] = new Pawn("Black", 1, 3);
      board[1][4] = new Pawn("Black", 1, 4); 
      board[1][5] = new Pawn("Black", 1, 5); 
      board[1][6] = new Pawn("Black", 1, 6); 
      board[1][7] = new Pawn("Black", 1, 7);
      //White Pieces set up
      board[7][7] = new Rook("White", 7, 7); 
      board[7][6] = new Knight("White", 7, 6); 
      board[7][5] = new Bishop("White", 7, 5);
      board[7][4] = new King("White", 7, 4); 
      board[7][3] = new Queen("White", 7, 3);  
      board[7][2] = new Bishop("White", 7, 2); 
      board[7][1] = new Knight("White", 7, 1); 
      board[7][0] = new Rook("White", 7, 0);
      board[6][7] = new Pawn("White", 6, 0); 
      board[6][6] = new Pawn("White", 6, 1); 
      board[6][5] = new Pawn("White", 6, 2); 
      board[6][4] = new Pawn("White", 6, 3);
      board[6][3] = new Pawn("White", 6, 4); 
      board[6][2] = new Pawn("White", 6, 5); 
      board[6][1] = new Pawn("White", 6, 6); 
      board[6][0] = new Pawn("White", 6, 7);
      
      player1_taken = new JLabel("<html>White Pieces Taken</html>");    //Initialize player1_taken
      player2_taken = new JLabel("<html>Black Pieces Taken</html>");    //Initialize player2_taken
      last_turn = new JLabel("<html>No moves have been made</html>");   //Initialize last_turn
      message = new JLabel("<html>White Player's Turn</html>");          //Initalize message
   }


	//post:  shows different pictures on the screen in grid format depending on the values stored in the array board
	//			0-blank, 1-white, 2-black and gives priority to drawing the player
   public void showBoard(Graphics g) {
      int x =0, y=0;		//upper left corner location of where image will be drawn
      for(int r = 0; r < board.length; r++) {
         x = 0;						//reset the row distance
         for(int c = 0; c < board[0].length; c++) {
            if((r % 2 == 0 && c % 2 == 0) || (r % 2 == 1 && c % 2 == 1))
               g.drawImage(blankDark.getImage(), x, y, SIZE, SIZE, null);  //Sets to dark tile
            if((r % 2 == 0 && c % 2 == 1) || (r % 2 == 1 && c % 2 == 0))
               g.drawImage(blankLight.getImage(), x, y, SIZE, SIZE, null);  //Sets to light tile
            //Draw the piece on the board   
            Piece temp = board[r][c];   
            if(temp != null && temp.getColor().equals("White") && temp.getType().equals("Pawn"))
               g.drawImage(whitePawn.getImage(), x, y, SIZE, SIZE, null); //Sets to white pawn
            else if(temp != null && temp.getColor().equals("Black") && temp.getType().equals("Pawn"))
               g.drawImage(blackPawn.getImage(), x, y, SIZE, SIZE, null); //Sets to black pawn
            else if(temp != null && temp.getColor().equals("White") && temp.getType().equals("Knight"))
               g.drawImage(whiteKnight.getImage(), x, y, SIZE, SIZE, null); //Sets to white knight
            else if(temp != null && temp.getColor().equals("Black") && temp.getType().equals("Knight"))
               g.drawImage(blackKnight.getImage(), x, y, SIZE, SIZE, null); //Sets to black knight
            else if(temp != null && temp.getColor().equals("White") && temp.getType().equals("Bishop"))
               g.drawImage(whiteBishop.getImage(), x, y, SIZE, SIZE, null); //Sets to white bishop
            else if(temp != null && temp.getColor().equals("Black") && temp.getType().equals("Bishop"))
               g.drawImage(blackBishop.getImage(), x, y, SIZE, SIZE, null); //Sets to black bishop
            else if(temp != null && temp.getColor().equals("White") && temp.getType().equals("Rook"))
               g.drawImage(whiteRook.getImage(), x, y, SIZE, SIZE, null); //Sets to white rook
            else if(temp != null && temp.getColor().equals("Black") && temp.getType().equals("Rook"))
               g.drawImage(blackRook.getImage(), x, y, SIZE, SIZE, null); //Sets to black rook
            else if(temp != null && temp.getColor().equals("White") && temp.getType().equals("Queen"))
               g.drawImage(whiteQueen.getImage(), x, y, SIZE, SIZE, null); //Sets to white queen
            else if(temp != null && temp.getColor().equals("Black") && temp.getType().equals("Queen"))
               g.drawImage(blackQueen.getImage(), x, y, SIZE, SIZE, null); //Sets to black queen
            else if(temp != null && temp.getColor().equals("White") && temp.getType().equals("King"))
               g.drawImage(whiteKing.getImage(), x, y, SIZE, SIZE, null); //Sets to white king
            else if(temp != null && temp.getColor().equals("Black") && temp.getType().equals("King"))
               g.drawImage(blackKing.getImage(), x, y, SIZE, SIZE, null); //Sets to black king                                    
            x+=SIZE;
         }
         y+=SIZE;
      }
   }

	//THIS METHOD IS ONLY CALLED THE MOMENT A KEY IS HIT - NOT AT ANY OTHER TIME
	//pre:   k is a valid keyCode
	//post:  changes the players position depending on the key that was pressed (sent from the driver)
	//			keeps the player in the bounds of the size of the array board, then the enemy moves
   public void processUserInput(int k)
   {
      if(k==KeyEvent.VK_Q || k==KeyEvent.VK_ESCAPE)					//End the program	
         System.exit(1);
      repaint();			//refresh the screen
   }

   public void paintComponent(Graphics g) {
      g.setColor(Color.blue);
      super.paintComponent(g); 		
      showBoard(g);					//draw the contents of the array board on the screen
   }
   
	 //***BEGIN MOUSE STUFF***
   private class Listener implements ActionListener {
      public void actionPerformed(ActionEvent e) {	//this is called for each timer iteration - make the enemy move randomly
         repaint();
      }
   }

   public void mouseClicked( MouseEvent e ) {
      int button = e.getButton();
      if(playCount % 2 == 0) { //White Players turn
         if(button == MouseEvent.BUTTON1){
            int mouseR = (mouseY/SIZE);
            int mouseC = (mouseX/SIZE);
            if(mouseR >=0 && mouseC >= 0 && mouseR < board.length && mouseC < board[0].length) {
               playerR = mouseR;
               playerC = mouseC;
               if(button == MouseEvent.BUTTON1)	{
               //board.get(playerR, playerC).moveIsValid(board, playerR, playerC, mouseR, mouseC, true);
                  if(selected == false) {
                     Piece temp = board[playerR][playerC]; //Makes piece temp and sees if the color is white
                     if(temp != null && temp.getColor().equals("White")) {
                        r1 = playerR; //Gets piece
                        c1 = playerC;
                        message.setText(temp.getColor() + " " + temp.getType() + " selected.");
                        selected = true;
                     }
                  }
                  else  {//Aleady selected piece
                     r2 = playerR; //Gets the move location of the piece
                     c2 = playerC;
                     Piece temp = board[r1][c1]; 
                     if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("legal")) { //Sees if the piece can move to that location
                        board[r1][c1] = null;
                        if(board[r2][c2] != null) { //Check to see if the piece is 
                           blackTaken.add(board[r2][c2]); //Adds piece to the taken pile
                           board[r2][c2] = null;
                        }
                        board[r2][c2] = temp;
                        //Method to select the pawn if it reaches the end of the board
                        for(int r = 0; r < board[0].length; r++) {
                           Piece tempWhite = board[0][r];
                           if(tempWhite != null && tempWhite.getType().equals("P") && tempWhite.getColor().equals("White")) {
                              Piece whiteTemp = whiteTaken.get(whiteCount);
                              whiteTaken.add(tempWhite);
                              board[0][r] = whiteTemp;
                              r3 = playerR;
                              c3 = playerC;
                              if(tempWhite.getRow() == r3 && tempWhite.getCol() == c3) {
                                 if(whiteCount == whiteTaken.size()) {
                                    blackCount = 0;
                                 }
                                 temp = board[0][r];
                                 whiteTemp = whiteTaken.get(whiteCount);
                                 board[0][r] = null;
                                 whiteTaken.add(temp);
                                 board[0][r] = whiteTemp;
                                 whiteCount++;
                                 r3 = playerR;
                                 c3 = playerC;
                              } 
                           }
                        }
                        message.setText("<html>Black Player's Turn</html>");       
                        playCount++; //Goes to the next player
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("illegal")) {
                        message.setText("<html>That is not a legal move</html>");   //Print out error message
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("same")) {
                        message.setText("<html>Move your piece to a different location</html>");   //Print out error message
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("off")) {
                        message.setText("<html>That is not a legal move</html>");   //Print out error message
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("color")) {
                        message.setText("<html>There is a piece there that's the same color</html>");   //Print out error message
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("blocked")) {
                        message.setText("<html>There is a piece blocking your move to there</html>");   //Print out error message
                     }
                     selected = false;
                  }
               }
            }
            else {
               playerR = board.length/2;
               playerC = board[0].length/2;
            }
         } 
         repaint();
      }
      
      if(playCount % 2 == 1) { //Black Players Turn
         if(button == MouseEvent.BUTTON1) {
            int mouseR = (mouseY/SIZE);
            int mouseC = (mouseX/SIZE); 
            if(mouseR >=0 && mouseC >= 0 && mouseR < board.length && mouseC < board[0].length) {
               playerR = mouseR;
               playerC = mouseC;
               if(button == MouseEvent.BUTTON1)	{
               //board.get(playerR, playerC).moveIsValid(board, playerR, playerC, mouseR, mouseC, true);
                  if(selected == false) {
                     Piece temp = board[playerR][playerC];
                     if(temp != null && temp.getColor().equals("Black")) { //Sees if the piece is black
                        r1 = playerR;
                        c1 = playerC;
                        message.setText(temp.getColor() + " " + temp.getType() + " selected.");
                        selected = true;
                     }
                  }
                  else { //Aleady selected piece
                     r2 = playerR;
                     c2 = playerC;
                     Piece temp = board[r1][c1]; //Creates a piece called temp
                     if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("legal")) { //Sees if the piece can move and doesnt risk the king
                        board[r1][c1] = null; //Moves piece
                        if(board[r2][c2] != null) { //Removes the piece and adds to discard if there is a piece there
                           whiteTaken.add(board[r2][c2]);
                           board[r2][c2] = null;
                        }
                        board[r2][c2] = temp;
                        //Method to change the black pawn if it reaches the opposite side of the board
                        for(int r = 0; r < board[0].length; r++) {
                           Piece tempBlack = board[7][r];
                           if(tempBlack != null && tempBlack.getType().equals("P") && tempBlack.getColor().equals("Black")) {
                              Piece blackTemp = blackTaken.get(blackCount);
                              blackTaken.add(tempBlack);
                              board[7][r] = blackTemp;
                              r3 = playerR;
                              c3 = playerC;
                              if(tempBlack.getRow() == r3 && tempBlack.getCol() == c3) {
                                 if(blackCount == blackTaken.size()) {
                                    blackCount = 0;
                                 }
                                 temp = board[7][r];
                                 blackTemp = blackTaken.get(blackCount);
                                 board[7][r] = blackTemp;
                                 blackTaken.add(temp);
                                 blackCount++;
                                 r3 = playerR;
                                 c3 = playerC;
                              } 
                           }
                        }
                        message.setText("<html>White Player's Turn</html>");
                        playCount++; //Next players move
                     }  else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("illegal")) {
                        message.setText("<html>That is not a legal move</html>");   //Print out error message
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("same")) {
                        message.setText("<html>Move your piece to a different location</html>");   //Print out error message
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("off")) {
                        message.setText("<html>That is not a legal move</html>");   //Print out error message
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("color")) {
                        message.setText("<html>There is a piece there that's the same color</html>");   //Print out error message
                     } else if(temp.moveIsValid(board, r1, c1, r2, c2, true).equals("blocked")) {
                        message.setText("<html>There is a piece blocking your move to there</html>");   //Print out error message
                     }
                     selected = false; 
                  }
               }
            }
            else {
               playerR = board.length/2;
               playerC = board[0].length/2;
            }
         } 
      }
      repaint();
   }

   public void mousePressed( MouseEvent e )
   {}

   public void mouseReleased( MouseEvent e )
   {}

   public void mouseEntered( MouseEvent e )
   {}

   public void mouseMoved( MouseEvent e)
   {
      mouseX = e.getX();
      mouseY = e.getY();
      
      int mouseR = (mouseY/SIZE);
      int mouseC = (mouseX/SIZE);
      if(mouseR >=0 && mouseC >= 0 && mouseR < board.length && mouseC < board[0].length) {
         playerR = mouseR;
         playerC = mouseC;
      }
      else {
         playerR = board.length/2;
         playerC = board[0].length/2;
      
      }
      repaint();			//refresh the screen
   }

   public void mouseDragged( MouseEvent e)
   {}

   public void mouseExited( MouseEvent e )
   {}
}
