import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
 
public class ChessGame2
{
   public static  SparseMatrix<Piece> board;
   public static  boolean gameIsOver;
   public static    String player1, player2;
   public static   int playCount;


   public static boolean checkmate(String color)
   {
      int[] kingPos = new int[2];
      kingPos = findKing(color);
      int x = kingPos[0];
      int y = kingPos[1];
      Piece king = board.get(x, y);
      if(king != null && king.moveIsValid(board, x, y, x + 1, y, true) == false && king.moveIsValid(board, x, y, x - 1, y, true) == false && 
      king.moveIsValid(board, x, y, x, y + 1, true) == false && king.moveIsValid(board, x, y, x, y - 1, true) == false &&
      king.moveIsValid(board, x, y, x + 1, y + 1, true) == false && king.moveIsValid(board, x, y, x - 1, y - 1, true) == false && 
      king.moveIsValid(board, x, y, x - 1, y + 1, true) == false && king.moveIsValid(board, x, y, x + 1, y - 1, true) == false &&
      king.kingInCheck(board, x, y) == true)
      {
         return true;
      }
      return false;
   }

   public static boolean check(String color)
   {
      int[] kingPos = new int[2];
      kingPos = findKing(color);
      int x = kingPos[0];
      int y = kingPos[1];
      Piece king = board.get(x, y);
      if(king != null && king.kingInCheck(board, x, y) == true)
      {
         return true;
      }
      return false; 
   }


   public static int[] findKing(String color)
   {
      int [] pos = new int[2];
      for(int i = 0; i < 7; i++)
      {
         for(int j = 0; j < 7; j++)
         {
            Piece temp = board.get(i, j);
            if(temp != null && temp.getType().equals("K") && temp.getColor().equals(color))
            {
               pos[0] = temp.getRow();
               pos[1] = temp.getCol();
               return pos;
            }
         }
      }
      return pos;
   }


   public static boolean gameIsOver()
   {
      if(checkmate("Black") == true)
      {
         //System.out.println(board);
         System.out.println("Black is in checkmate");
         System.out.println(player1 + " wins the game!!!");
         //gameIsOver = true; 
         return true;
      } 
      if(checkmate("White") == true)
      {
                  //System.out.println(board);
         System.out.println("White is in checkmate");
         System.out.println(player2 + " wins the game!!!");
                  //gameIsOver = true; 
         return true;
      } 
      return false;
   }


   public static void setUpBoard()
   {
      board = new SparseMatrix(8, 8);
      gameIsOver = false;
      playCount = 0;
      player1 = "white";
      player2 = "Black";
      
      //Black Pieces set up
      board.add(0, 0 , new Rook("Black", 0, 0, "R")); 
      board.add(0, 1 , new Knight("Black", 0, 1, "H")); 
      board.add(0, 2 , new Bishop("Black", 0, 2, "B")); 
      board.add(0, 3 , new Queen("Black", 0, 3, "Q")); 
      board.add(0, 4 , new King("Black", 0, 4, "K")); 
      board.add(0, 5 , new Bishop("Black", 0, 5, "B")); 
      board.add(0, 6 , new Knight("Black", 0, 6, "H")); 
      board.add(0, 7 , new Rook("Black", 0, 7, "R"));
      board.add(1, 0 , new Pawn("Black", 1, 0, "P")); 
      board.add(1, 1 , new Pawn("Black", 1, 1, "P")); 
      board.add(1, 2 , new Pawn("Black", 1, 2, "P")); 
      board.add(1, 3 , new Pawn("Black", 1, 3, "P"));
      board.add(1, 4 , new Pawn("Black", 1, 4, "P")); 
      board.add(1, 5 , new Pawn("Black", 1, 5, "P")); 
      board.add(1, 6 , new Pawn("Black", 1, 6, "P")); 
      board.add(1, 7 , new Pawn("Black", 1, 7, "P"));
      //White Pieces set up
      board.add(7, 7 , new Rook("White", 7, 7, "R")); 
      board.add(7, 6 , new Knight("White", 7, 6, "H")); 
      board.add(7, 5 , new Bishop("White", 7, 5, "B")); 
      board.add(7, 4 , new Queen("White", 7, 4, "Q")); 
      board.add(7, 3 , new King("White", 7, 3, "K")); 
      board.add(7, 2 , new Bishop("White", 7, 2, "B")); 
      board.add(7, 1 , new Knight("White", 7, 1, "H")); 
      board.add(7, 0 , new Rook("White", 7, 0, "R"));
      board.add(6, 0 , new Pawn("White", 6, 0, "P")); 
      board.add(6, 1 , new Pawn("White", 6, 1, "P")); 
      board.add(6, 2 , new Pawn("White", 6, 2, "P")); 
      board.add(6, 3 , new Pawn("White", 6, 3, "P"));
      board.add(6, 4 , new Pawn("White", 6, 4, "P")); 
      board.add(6, 5 , new Pawn("White", 6, 5, "P")); 
      board.add(6, 6 , new Pawn("White", 6, 6, "P")); 
      board.add(6, 7 , new Pawn("White", 6, 7, "P"));
   
   }
   
   
   public static void main(String[] arg)
   {
      Scanner input = new Scanner(System.in);
      setUpBoard();
      //board.toString();
      //Enter the names of both players
      /*
      System.out.println("Enter the name of the white player");
      player1 = input.next();
      System.out.println("Enter the name of the black player");
      player2 = input.next();
      */
      //Keeps game running until checkmate
      while(gameIsOver()== false)//gameIsOver == false)
      {
         if(playCount % 2 == 0)
         {
            //Player 1's turn to move
            System.out.println("It is " + player1 + "'s turn");
            System.out.println(board);
            System.out.println("Enter the row location of the piece which you would like to move");
            int fromRow1 = input.nextInt();
            System.out.println("Enter the column location of the piece which you would like to move");
            int fromCol1 = input.nextInt();
         
            //Sees if the location the player entered contains a piece, elses asks for re-entry
            while(board.get(fromRow1, fromCol1) == null || !board.get(fromRow1, fromCol1).getColor().equals("White"))
            {
               System.out.println("There is no piece there or you are not allowed to move that piece");
               System.out.println("Enter the row location of the piece which you would like to move");
               fromRow1 = input.nextInt();
               System.out.println("Enter the column location of the piece which you would like to move");
               fromCol1 = input.nextInt();
            }
            
            //Sees if the piece they want to move is their color, else they must choose another piece
         
            System.out.println("Enter the row location of where you would like to move the piece");
            int toRow1 = input.nextInt();
            System.out.println("Enter the column location of where you would like to move the piece");
            int toCol1 = input.nextInt();
          
            //Sees if the Piece is a white Pawn
            Piece temp1 = board.get(fromRow1, fromCol1);
            if(temp1.getType().equals("P") && temp1.getColor().equals("White"))
            {
               //Sees if their move is valid, or else they need to input a new location to move
               while(temp1.moveIsValid(board, fromRow1, fromCol1, toRow1, toCol1, true) == false)
               {
                  System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                  System.out.println("Enter the row location of where you would like to move the piece");
                  toRow1 = input.nextInt();
                  System.out.println("Enter the column location of where you would like to move the piece");
                  toCol1 = input.nextInt();
               }
               //Changes Pawn if it reaches the end of the board
               if(toRow1 == 0)
               {
                  System.out.println("Your pawn reached the end of the board and can now change into any piecd you want");
                  System.out.println("What would you like to make your Pawn become?");
                  String changeType1 = input.next();
                  //Makes sure the piece entered is a valid type
                  while(!changeType1.equals("Knight") || !changeType1.equals("Bishop") || !changeType1.equals("Rook") || !changeType1.equals("Queen"))
                  {
                     System.out.println("That is not a valid type to change your piece to");
                     System.out.println("What would you like to make your Pawn become?");
                     changeType1 = input.next();
                  }
                  //Changes Pawn to a Knight
                  if(changeType1.equals("Knight"))
                  {
                     Knight hor = new Knight("Black", toRow1, toCol1, "H");
                     board.remove(fromRow1, fromCol1);
                     if(board.get(toRow1, toCol1) != null)
                     {
                        board.remove(toRow1, toCol1);
                     }
                     board.add(toRow1, toCol1, hor);
                  }
                  else
                     //Changes Pawn to a Bishop
                     if(changeType1.equals("Bishop"))
                     {
                        Bishop b = new Bishop("White", toRow1, toCol1, "B");
                        board.remove(fromRow1, fromCol1);
                        if(board.get(toRow1, toCol1) != null)
                        {
                           board.remove(toRow1, toCol1);
                        }
                        board.add(toRow1, toCol1, b);
                     }
                     else
                        //Changes Pawn to a Rook
                        if(changeType1.equals("Rook"))
                        {
                           Rook r = new Rook("Black", toRow1, toCol1, "R");
                           board.remove(fromRow1, fromCol1);
                           if(board.get(toRow1, toCol1) != null)
                           {
                              board.remove(toRow1, toCol1);
                           }
                           board.add(toRow1, toCol1, r);
                        }
                        else
                           //Changes Pawn to a Queen
                           if(changeType1.equals("Queen"))
                           {
                              Queen q = new Queen("Black", toRow1, toCol1, "Q");
                              board.remove(fromRow1, fromCol1);
                              if(board.get(toRow1, toCol1) != null)
                              {
                                 board.remove(toRow1, toCol1);
                              }
                              board.add(toRow1, toCol1, q);
                           }
               }
                  //Moves the pawn up one spot on the board if the move is valid
               else
               {
                  Pawn pn = new Pawn("Black", toRow1, toCol1, "P");
                  board.remove(fromRow1, fromCol1);
                  if(board.get(toRow1, toCol1) != null)
                  {
                     board.remove(toRow1, toCol1);
                  }
                  board.add(toRow1, toCol1, pn);
               }              
            }
            else
               //Sees if the piece is a white Knight
               if(temp1.getType().equals("H") && temp1.getColor().equals("White"))
               {
                  //See's if their move is valid, else makes them re enter their move
                  while(temp1.moveIsValid(board, fromRow1, fromCol1, toRow1, toCol1, true) == false)
                  {
                     System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                     System.out.println("Enter the row location of where you would like to move the piece");
                     toRow1 = input.nextInt();
                     System.out.println("Enter the column location of where you would like to move the piece");
                     toCol1 = input.nextInt();
                  }
                  //Moves the Knight to its new location
                  Knight hor = new Knight("White", toRow1, toCol1, "H");
                  board.remove(fromRow1, fromCol1);
                  if(board.get(toRow1, toCol1) != null)
                  {
                     board.remove(toRow1, toCol1);
                  }
                  board.add(toRow1, toCol1, hor);
               }
               else
                  //Sees if the Piece is a White Bishop
                  if(temp1.getType().equals("B") && temp1.getColor().equals("White"))
                  {
                     //See's if their move is valid, else makes them re enter their move
                     while(temp1.moveIsValid(board, fromRow1, fromCol1, toRow1, toCol1, true) == false)
                     {
                        System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                        System.out.println("Enter the row location of where you would like to move the piece");
                        toRow1 = input.nextInt();
                        System.out.println("Enter the column location of where you would like to move the piece");
                        toCol1 = input.nextInt();
                     }
                  //Moves the Bishop to its new location
                     Bishop b = new Bishop("White", toRow1, toCol1, "B");
                     board.remove(fromRow1, fromCol1);
                     if(board.get(toRow1, toCol1) != null)
                     {
                        board.remove(toRow1, toCol1);
                     }
                     board.add(toRow1, toCol1, b);
                  }
                  else
                     //Sees if the piece is a White Rook
                     if(temp1.getType().equals("R") && temp1.getColor().equals("White"))
                     {
                        //See's if their move is valid, else makes them re enter their move
                        while(temp1.moveIsValid(board, fromRow1, fromCol1, toRow1, toCol1, true) == false)
                        {
                           System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                           System.out.println("Enter the row location of where you would like to move the piece");
                           toRow1 = input.nextInt();
                           System.out.println("Enter the column location of where you would like to move the piece");
                           toCol1 = input.nextInt();
                        }
                     //Moves the Rook to its new location
                        Rook r = new Rook("White", toRow1, toCol1, "R");
                        board.remove(fromRow1, fromCol1);
                        if(board.get(toRow1, toCol1) != null)
                        {
                           board.remove(toRow1, toCol1);
                        }
                        board.add(toRow1, toCol1, r);
                     }
                     else
                        //Sees if the piece is a White Queen
                        if(temp1.getType().equals("Q") && temp1.getColor().equals("White"))
                        {
                           //See's if their move is valid, else makes them re enter their move
                           while(temp1.moveIsValid(board, fromRow1, fromCol1, toRow1, toCol1, true) == false)
                           {
                              System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                              System.out.println("Enter the row location of where you would like to move the piece");
                              toRow1 = input.nextInt();
                              System.out.println("Enter the column location of where you would like to move the piece");
                              toCol1 = input.nextInt();
                           }
                        //Moves the Queen to its new location
                           Queen q = new Queen("White", toRow1, toCol1, "Q");
                           board.remove(fromRow1, fromCol1);
                           if(board.get(toRow1, toCol1) != null)
                           {
                              board.remove(toRow1, toCol1);
                           }
                           board.add(toRow1, toCol1, q);
                        }
                        else
                           //Sees if the Piece is a White King
                           if(temp1.getType().equals("K") && temp1.getColor().equals("White"))
                           {
                              //See's if their move is valid, else makes them re enter their move
                              while(temp1.moveIsValid(board, fromRow1, fromCol1, toRow1, toCol1, true) == false)
                              {
                                 System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                                 System.out.println("Enter the row location of where you would like to move the piece");
                                 toRow1 = input.nextInt();
                                 System.out.println("Enter the column location of where you would like to move the piece");
                                 toCol1 = input.nextInt();
                              }
                           //Moves the King to its new location
                              King k = new King("White", toRow1, toCol1, "K");
                              board.remove(fromRow1, fromCol1);
                              if(board.get(toRow1, toCol1) != null)
                              {
                                 board.remove(toRow1, toCol1);
                              }
                              board.add(toRow1, toCol1, k);
                           } 
            /*
            if(temp1.checkmate(board, "Black") == true)
            {
               System.out.println(board);
               System.out.println("Black is in checkmate");
               System.out.println(player1 + " wins the game!!!");
               gameIsOver = true; 
            } 
            else*/
            if(check("Black") == true)
            {
               System.out.println("Black is in check");
            }                            
            playCount++;
         }
         else
            if(playCount % 2 == 1)
            {
               //Player 2's turn to move
               System.out.println("It is " + player2 +"'s turn");
               System.out.println(board);
               System.out.println("Enter the row location of the piece which you would like to move");
               int fromRow2 = input.nextInt();
               System.out.println("Enter the column location of the piece which you would like to move");
               int fromCol2 = input.nextInt();
               
               while(board.get(fromRow2, fromCol2) == null || !board.get(fromRow2, fromCol2).getColor().equals("Black"))
               {
                  System.out.println("There is no piece there or you are not allowed to move that piece");
                  System.out.println("Enter the row location of the piece which you would like to move");
                  fromRow2 = input.nextInt();
                  System.out.println("Enter the column location of the piece which you would like to move");
                  fromCol2 = input.nextInt();
               }
            
               System.out.println("Enter the row location of where you would like to move the piece");
               int toRow2 = input.nextInt();
               System.out.println("Enter the column location of where you would like to move the piece");
               int toCol2 = input.nextInt();
               
               Piece temp2 = board.get(fromRow2, fromCol2);
               
               //Sees if the piece is a Black Pawn
               if(temp2.getType().equals("P") && temp2.getColor().equals("Black"))
               {
                  //Sees if their move is valid, or else they need to input a new location to move
                  while(temp2.moveIsValid(board, fromRow2, fromCol2, toRow2, toCol2, true) == false)
                  {
                     System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                     System.out.println("Enter the row location of where you would like to move the piece");
                     toRow2 = input.nextInt();
                     System.out.println("Enter the column location of where you would like to move the piece");
                     toCol2 = input.nextInt();
                  }
               //Changes Pawn if it reaches the end of the board
                  if(toRow2 == 7)
                  {
                     System.out.println("Your pawn reached the end of the board and can now change into any piece you captured");
                     System.out.println("What would you like to make your Pawn become?");
                     String changeType2 = input.next();
                  //Makes sure the piece entered is a valid type
                     while(!changeType2.equals("Knight") || !changeType2.equals("Bishop") || !changeType2.equals("Rook") || !changeType2.equals("Queen"))
                     {
                        System.out.println("That is not a valid type to change your piece to");
                        System.out.println("What would you like to make your Pawn become?");
                        changeType2 = input.next();
                     }
                  //Changes Pawn to a Knight
                     if(changeType2.equals("Knight"))
                     {
                        Knight hor = new Knight("Black", toRow2, toCol2, "H");
                        board.remove(fromRow2, fromCol2);
                        if(board.get(toRow2, toCol2) != null)
                        {
                           board.remove(toRow2, toCol2);
                        }
                        board.add(toRow2, toCol2, hor);
                     }
                     else
                     //Changes Pawn to a Bishop
                        if(changeType2.equals("Bishop"))
                        {
                           Bishop b = new Bishop("Black", toRow2, toCol2, "B");
                           board.remove(fromRow2, fromCol2);
                           if(board.get(toRow2, toCol2) != null)
                           {
                              board.remove(toRow2, toCol2);
                           }
                           board.add(toRow2, toCol2, b);
                        }
                        else
                        //Changes Pawn to a Rook
                           if(changeType2.equals("Rook"))
                           {
                              Rook r = new Rook("Black", toRow2, toCol2, "R");
                              board.remove(fromRow2, fromCol2);
                              if(board.get(toRow2, toCol2) != null)
                              {
                                 board.remove(toRow2, toCol2);
                              }
                              board.add(toRow2, toCol2, r);
                           }
                           else
                           //Changes Pawn to a Queen
                              if(changeType2.equals("Queen"))
                              {
                                 Queen q = new Queen("Black", toRow2, toCol2, "Q");
                                 board.remove(fromRow2, fromCol2);
                                 if(board.get(toRow2, toCol2) != null)
                                 {
                                    board.remove(toRow2, toCol2);
                                 }
                                 board.add(toRow2, toCol2, q);
                              }
                  }
                  //Moves the pawn up one spot on the board if the move is valid
                  else
                  {
                     Pawn pn = new Pawn("Black", toRow2, toCol2, "P");
                     board.remove(fromRow2, fromCol2);
                     if(board.get(toRow2, toCol2) != null)
                     {
                        board.remove(toRow2, toCol2);
                     }
                     board.add(toRow2, toCol2, pn);
                  }  
               }
               else
                  //Sees if the Piece is a Black Knight
                  if(temp2.getType().equals("H") && temp2.getColor().equals("Black"))
                  {
                     //See's if their move is valid, else makes them re enter their move
                     while(temp2.moveIsValid(board, fromRow2, fromCol2, toRow2, toCol2, true) == false)
                     {
                        System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                        System.out.println("Enter the row location of where you would like to move the piece");
                        toRow2 = input.nextInt();
                        System.out.println("Enter the column location of where you would like to move the piece");
                        toCol2 = input.nextInt();
                     }
                  //Moves the Knight to its new location
                     Knight hor = new Knight("Black", toRow2, toCol2, "H");
                     board.remove(fromRow2, fromCol2);
                     if(board.get(toRow2, toCol2) != null)
                     {
                        board.remove(toRow2, toCol2);
                     }
                     board.add(toRow2, toCol2, hor);
                  }
                  else
                     //Sees if the piece is a Black Bishop
                     if(temp2.getType().equals("B") && temp2.getColor().equals("Black"))
                     {
                        //See's if their move is valid, else makes them re enter their move
                        while(temp2.moveIsValid(board, fromRow2, fromCol2, toRow2, toCol2, true) == false)
                        {
                           System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                           System.out.println("Enter the row location of where you would like to move the piece");
                           toRow2 = input.nextInt();
                           System.out.println("Enter the column location of where you would like to move the piece");
                           toCol2 = input.nextInt();
                        }
                        //Moves the Rook to its new location
                        Bishop b = new Bishop("Black", toRow2, toCol2, "B");
                        board.remove(fromRow2, fromCol2);
                        if(board.get(toRow2, toCol2) != null)
                        {
                           board.remove(toRow2, toCol2);
                        }
                        board.add(toRow2, toCol2, b);
                     }
                     else
                        //Sees if the Piece is a Black Rook
                        if(temp2.getType().equals("R") && temp2.getColor().equals("Black"))
                        {
                           //See's if their move is valid, else makes them re enter their move
                           while(temp2.moveIsValid(board, fromRow2, fromCol2, toRow2, toCol2, true) == false)
                           {
                              System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                              System.out.println("Enter the row location of where you would like to move the piece");
                              toRow2 = input.nextInt();
                              System.out.println("Enter the column location of where you would like to move the piece");
                              toCol2 = input.nextInt();
                           }
                        //Moves the Rook to its new location
                           Rook r = new Rook("Black", toRow2, toCol2, "R");
                           board.remove(fromRow2, fromCol2);
                           if(board.get(toRow2, toCol2) != null)
                           {
                              board.remove(toRow2, toCol2);
                           }
                           board.add(toRow2, toCol2, r);
                        }
                        else
                           //Sees if the piece is a Black Queen
                           if(temp2.getType().equals("Q") && temp2.getColor().equals("Black"))
                           {
                           //See's if their move is valid, else makes them re enter their move
                              while(temp2.moveIsValid(board, fromRow2, fromCol2, toRow2, toCol2, true) == false)
                              {
                                 System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                                 System.out.println("Enter the row location of where you would like to move the piece");
                                 toRow2 = input.nextInt();
                                 System.out.println("Enter the column location of where you would like to move the piece");
                                 toCol2 = input.nextInt();
                              }
                           //Moves the Queen to its new location
                              Queen q = new Queen("Black", toRow2, toCol2, "Q");
                              board.remove(fromRow2, fromCol2);
                              if(board.get(toRow2, toCol2) != null)
                              {
                                 board.remove(toRow2, toCol2);
                              }
                              board.add(toRow2, toCol2, q);
                           }
                           else
                           //Sees if the Piece is a Black King
                              if(temp2.getType().equals("K") && temp2.getColor().equals("Black"))
                              {
                              //See's if their move is valid, else makes them re enter their move
                                 while(board.get(fromRow2, fromCol2).moveIsValid(board, fromRow2, fromCol2, toRow2, toCol2, true) == false)
                                 {
                                    System.out.println("That is an invalid move for that piece.  Enter a new location to move to");
                                    System.out.println("Enter the row location of where you would like to move the piece");
                                    toRow2 = input.nextInt();
                                    System.out.println("Enter the column location of where you would like to move the piece");
                                    toCol2 = input.nextInt();
                                 }
                              //Moves the King to its new location
                                 King k = new King("Black", toRow2, toCol2, "K");
                                 board.remove(fromRow2, fromCol2);
                                 if(board.get(toRow2, toCol2) != null)
                                 {
                                    board.remove(toRow2, toCol2);
                                 }
                                 board.add(toRow2, toCol2, k);
                              }
                              /*
               if(temp2.checkmate(board, "White") == true)
               {
                  System.out.println(board);
                  System.out.println("White is in checkmate");
                  System.out.println(player2 + " wins the game!!!");
                  gameIsOver = true; 
               } 
               else
               */
               if(check("White") == true)
               {
                  System.out.println("White is in check");
               }
               playCount++;
            }
      }
   }
}