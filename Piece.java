import java.util.*;
import java.awt.*;

public abstract class Piece implements Pieceable
{
   private String color;
   private int row;
   private int col;
   private String type;
   
   public Piece(String color, int row, int col, String type) {
      this.color = color;
      this.row = row;
      this.col = col;
      this.type = type;
   }
   
   public String getColor() {
      return color;
   }
   
   public void setColor(String color) {
      this.color = color;
   }  
   
   public int getRow() {
      return row;
   }
   
   public int getCol() {
      return col;
   }
   
   public void setRow(int row) {
      this.row = row;
   }
   
   public void setCol(int col) {
      this.col = col;
   }
        
   public String getType() {
      return type;
   }
   
   public void setType(String type) {
      this.type = type;
   }
   
   public abstract String moveIsValid(Piece[][] board, int fromX, int fromY, int toX, int toY, boolean kingCheck); //Move is vaild for each different piece

   public String toString() {
      return type;
   }
       
   public boolean kingAtRisk(Piece[][] board, int fromX, int fromY, int kingX, int kingY) { //Sees if the king is at risk    
      boolean kingAtRisk = false;
      Piece p = board[fromX][fromY];
      board[fromX][fromY] = null;
      Piece k = board[kingX][kingY];
      
      for(int x = 0; x < board.length; x++) { //Traverses through the board
         for(int y = 0; x < board[0].length; x++) {
            Piece piece = board[x][y];
            if(piece == null || piece.getColor().equals(p.getColor())) { //Skips piece of the same color
               continue;
            }
            if(piece.moveIsValid(board, x, y, kingX, kingY, false).equals("legal")) { //If a piece can attack the king from a certain position, it returns true
               board[fromX][fromY] = p;
               return true;
            }
         }
      }
      board[fromX][fromY] = p;
      return false;
   }
     
   public boolean checkmate(Piece[][] board, String color) { //Sees if the king is in check mate 
      for(int x = 0; x < board.length; x++) {
         for(int y = 0; x < board[0].length; x++) {
            Piece piece = board[x][y];
            if(piece != null && piece.getColor().equals(color)) {
               for(int r = 0; r < board.length; r++) {
                  for(int c = 0; c < board[0].length; c++) {
                     if(moveIsValid(board, x, y, r, c, false).equals("legal")) //Sees if there is a piece that can move to help protect the king
                        return false; //Finds one and returns false
                  }
               } 
            }
         }
      }
      return true;    //Returns true
   }

                  
   public boolean kingInCheck(Piece[][] board, int kingX, int kingY) { //Sees if the king is in check
      boolean blocked = false;
      boolean kingAtRisk = false;
      Piece k = board[kingX][kingY];
      for(int x = 0; x < board.length; x++) {
         for(int y = 0; x < board[0].length; x++) {
            Piece piece = board[x][y];
            if(piece != null && k != null && !piece.getColor().equals(k.getColor())) {
               if(moveIsValid(board, x, y, kingX, kingY, false).equals("legal")) //Sees if the piece being moved affects the kings
                  return true; 
            }
         }
      }
      return false;  //Sees that the king is not in check
   }
}