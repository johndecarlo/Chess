import java.awt.*;

public class Knight extends Piece
{
   public Knight(String color, int row, int col) {
      super(color, row, col, "Knight");
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
      if(toX == fromX + 2 && toY == fromY + 1) {   //Right 2, Up 1
         move = true;  //Set move to true
      } else if(toX == fromX + 2 && toY == fromY - 1) {  //Right 2, Down 1
         move = true;  //Set move to true
      } else if(toX == fromX - 2 && toY == fromY + 1) {  //Left 2, Up 1
         move = true;  //Set move to true
      } else if(toX == fromX - 2 && toY == fromY - 1) {  //Left 2, Down 1
         move = true;  //Set move to true
      } else if(toY == fromY + 2 && toX == fromX + 1) {  //Up 2, Right 1
         move = true;  //Set move to true
      } else if(toY == fromY + 2 && toX == fromX - 1) {  //Up 2, Left 1
         move = true;  //Set move to true
      } else if(toY == fromY - 2 && toX == fromX + 1) {  //Down 2, Right 1
         move = true;  //Set move to true
      } else if(toY == fromY - 2 && toX == fromX - 1) {  //Down 2, Left 1
         move = true;  //Set move to true
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