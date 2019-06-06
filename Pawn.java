import java.awt.*;

public class Pawn extends Piece {

   public Pawn(String color, int row, int col) {
      super(color, row, col, "Pawn");
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
      //If the piece selected is a white pawn
      if(board[fromX][fromY] != null && board[fromX][fromY].getColor().equals("White")) {
         if(fromY == toY && toX == fromX - 1) { //Moves up one spot
            move = true;   //Set move to true
            if(board[toX][toY] != null) { //Blocked if a piece is there
               return "blocked";   //Return blocked error message
            }
         }
         if(fromY == toY && toX == fromX - 2 && fromX == 6) { //Moves up one spot
            move = true;   //Set move to true
            if(board[toX][toY] != null) { //Blocked if a piece is there
               return "blocked";   //Return blocked error message
            }
         }
         if((toY == fromY - 1 && toX == fromX - 1) && (board[toX][toY] != null && !this.getColor().equals(board[toX][toY].getColor()))) { //Takes piece to the piece's right
            move = true;   //Set move equal to true
         } 
         if((toY == fromY + 1 && toX == fromX - 1) && (board[toX][toY] != null && !this.getColor().equals(board[toX][toY].getColor()))) { //Takes piece tp the piece's left
            move = true;   //Set move equal to true
         }
      }
      else  //If the piece selected is a black pawn
         if(board[fromX][fromY] != null && board[fromX][fromY].getColor().equals("Black")) {
            if(fromY == toY && toX == fromX + 1) { //Moves up one spot
               move = true;   //Set move equal to true
               if(board[toX][toY] != null) { //Blocked if a piece is there
                  return "blocked";   //Return blocked error message
               }
            }
            if(fromY == toY && toX == fromX + 2 && fromX == 1) { //Moves up one spot
               move = true;   //Set move equal to true
               if(board[toX][toY] != null) { //Blocked if a piece is there
                  return "blocked";   //Return blocked error message
               }
            }
            if((toY == fromY - 1 && toX == fromX + 1) && (board[toX][toY] != null && !this.getColor().equals(board[toX][toY].getColor()))) { //Takes piece to the left
               move = true;   //Set move equal to true
            } 
            if((toY == fromY + 1 && toX == fromX + 1) && (board[toX][toY] != null && !this.getColor().equals(board[toX][toY].getColor()))) { //Takes piece to the right
               move = true;   //Set move equal to true
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