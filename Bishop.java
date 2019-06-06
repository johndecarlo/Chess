
import java.awt.*;

public class Bishop extends Piece {

   public Bishop(String color, int row, int col) {
      super(color, row, col, "Bishop");
   }
   
   public String moveIsValid(Piece[][] board, int fromX, int fromY, int toX, int toY, boolean checkKing) {
      boolean move = false;         //The move is valid this piece
      boolean blocked = false;      //If there is a piece in the spot we want to move
      boolean kingAtRisk = false;   //If the piece moving puts the king at risk 
      //If the selected move is the exact same location
      if(fromX == toX && fromY == toY) {
         return "same";  //Return false
      }
      if(toX < 0 || toX > 7 || toY < 0 || toY > 7) {  //If the selected move is off the board
         return "off";  //Set on board
      }
      if(board[toX][toY] != null && this.getColor().equals(board[toX][toY].getColor())) { //If the selected piece is not null and is the player's color
         return "color";  //Set color to true
      }
      if(toX - fromX == toY - fromY) { //Moves down to the right
         move = true;  
         for(int r1 = fromX + 1, c1 = fromY + 1; r1 < toX && c1 < toY; r1++, c1++) {
            System.out.println("DR " + r1 + ", " + c1); 
            if(board[r1][c1] != null) {   //Check all diagonal positions between if the path is blocked
               return "blocked";   //Return blocked error message
            }
         }     
      } else if(toX - fromX == fromY - toY) { //Moves down to the left
         move = true; 
         for(int r2 = fromX + 1, c2 = fromY - 1; r2 < toX && c2 > toY; r2++, c2--) {
            System.out.println("DL " + r2 + ", " + c2); 
            if(board[r2][c2] != null) {   //Check all diagonal positions between if the path is blocked
               return "blocked";   //Return blocked error message
            }
         }       
      } else if(fromX - toX == toY - fromY) { //Moves up to the right
         move = true;  
         for(int r3 = fromX - 1, c3 = fromY + 1; r3 > toX && c3 < toY; r3--, c3++) {
            System.out.println("UR " + r3 + ", " + c3); 
            if(board[r3][c3] != null) {   //Check all diagonal positions between if the path is blocked
               return "blocked";   //Return blocked error message
            }
         }      
      } else if(fromX - toX == fromY - toY) { //Moves up to the left
         move = true;
         for(int r4 = fromX - 1, c4 = fromY - 1; r4 > toX && c4 > toY; r4--, c4--) {
            System.out.println("UL " + r4 + ", " + c4); 
            if(board[r4][c4] != null) {   //Check all diagonal positions between if the path is blocked
               return "blocked";   //Return blocked error message
            }
         }        
      }
      if(checkKing == true) {
         Piece p = board[fromX][fromY];
         if(p != null && p.getColor().equals("White")) {
            int[] kingPos = new int[2];
            kingPos = ChessBoard.findKing("White");
            kingAtRisk = kingAtRisk(board, fromX, fromY, kingPos[0], kingPos[1]);
            if(kingAtRisk)
               return "kingAtRisk"; //The king is at risk
         }
         else
            if(p != null && p.getColor().equals("Black")) {
               int[] kingPos = new int[2];
               kingPos = ChessBoard.findKing("Black");
               kingAtRisk = kingAtRisk(board, fromX, fromY, kingPos[0], kingPos[1]);
               if(kingAtRisk)
                  return "kingAtRisk"; //The king is at risk
            }
      }          
      if(move)
         return "legal";   //This move is legal for this piece
      else
         return "illegal";   //This move is illegial for this piece
   }
}