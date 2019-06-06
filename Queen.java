import java.awt.*;

public class Queen extends Piece
{
   public Queen(String color, int row, int col)
   {
      super(color, row, col, "Queen");
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
      if(fromY == toY  && fromX > toX) {
         move = true;   //Set move to true
         for(int r = fromX - 1; r >= toX + 1; r--) { //Moves left
            if(board[r][fromY] != null)   //Check all left positions between if the path is blocked
               return "blocked";   //Return blocked error message
         }
      } else if(fromX == toX  && fromY > toY) { //Moves up
         move = true;   //Set move to true
         for(int c = fromY - 1; c >= toY + 1; c--) {
            if(board[fromX][c] != null)   //Check all up positions between if the path is blocked
               return "blocked";   //Return blocked error message
         }
      } else if(fromY == toY  && fromX < toX) { //Moves right
         move = true;   //Set move to true
         for(int r = toX - 1; r >= fromX + 1; r--) {
            if(board[r][fromY] != null)   //Check all right positions between if the path is blocked
               return "blocked";   //Return blocked error message
         }
      } else if(fromX == toX  && fromY < toY) { //Moves down
         move = true;
         for(int c = toY - 1; c >= fromY + 1; c--) {
            if(board[fromX][c] != null)   //Check all down positions between if the path is blocked
               return "blocked";   //Return blocked error message
         }
      }
      else if(fromY == toY  && fromX > toX) {
         move = true;   //Set move to true
         for(int r = fromX - 1; r >= toX + 1; r--) { //Moves left
            if(board[r][fromY] != null)   //Check all left positions between if the path is blocked
               return "blocked";   //Return blocked error message
         }
      } else if(fromX == toX  && fromY > toY) { //Moves up
         move = true;   //Set move to true
         for(int c = fromY - 1; c >= toY + 1; c--) {
            if(board[fromX][c] != null)   //Check all up positions between if the path is blocked
               return "blocked";   //Return blocked error message
         }
      } else if(fromY == toY  && fromX < toX) { //Moves right
         move = true;   //Set move to true
         for(int r = toX - 1; r >= fromX + 1; r--) {
            if(board[r][fromY] != null)   //Check all right positions between if the path is blocked
               return "blocked";   //Return blocked error message
         }
      } else if(fromX == toX  && fromY < toY) { //Moves down
         move = true;
         for(int c = toY - 1; c >= fromY + 1; c--) {
            if(board[fromX][c] != null)   //Check all down positions between if the path is blocked
               return "blocked";   //Return blocked error message
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
         return "illegal";   //This move is illegial for this piece onBoard = true;       //If we move the piece, it will still be on the board
   }
}