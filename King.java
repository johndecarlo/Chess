import java.awt.*;

public class King extends Piece
{
   public King(String color, int row, int col) {
      super(color, row, col, "King");
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
      if(fromX == toX || fromY == toY) {
         if(fromY == toY  && fromX + 1 == toX) {   //Moves Right
            move = true;
         } else if(fromX == toX  && fromY + 1 == toY) {  //Moves Down
            move = true;
         } else if(fromY == toY  && fromX - 1 == toX) {  //Moves Left
            move = true;
         } else if(fromX == toX  && fromY - 1 == toY) {  //Moves Up
            move = true;
         }
      } else if(fromX + 1 == toX && fromY + 1 == toY) { //Moves down right
         move = true;
      } else if(fromX + 1 == toX && fromY - 1 == toY) { //Moves down left 
         move = true;
      } else if(fromX - 1 == toX && fromY + 1 == toY) { //Moves up right
         move = true;
      } else if(fromX - 1 == toX && fromY - 1 == toY) { //Moves up left
         move = true;
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