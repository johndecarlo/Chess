import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ChessBoardDriver {  	//Driver Program

   public static ChessBoard screen;			//Game window
   public static JLabel player1_taken;    //Label for player1_taken
   public static JLabel player2_taken;    //Label for player2_taken
   public static JLabel last_turn;        //Message for players
   public static JLabel message;          //Message for players
   
   public static void main(String[]args) {
      screen = new ChessBoard();             //Initalize the chess board   
      JFrame frame = new JFrame("Chess");	   //window title
      createSideMessages();                  //Generates our side bar
      
      frame.setSize(850, 675);					   //Size of game window
      frame.setLocation(350, 50);				   //location of game window on the screen
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);                //Cannot change size of the screen
      frame.setContentPane(screen);		         //Set screen to the panel
      frame.setVisible(true);                   //Set board visible
      frame.addKeyListener(new listen());		   //Get input from the keyboard
   }
   
   public static void createSideMessages() {
      screen.setLayout(null);          //Set layout to null
      player1_taken = screen.getPlayer1Taken();    //Initialize player1_taken
      player2_taken = screen.getPlayer2Taken();    //Initialize player2_taken
      last_turn = screen.getLastTurn();   //Initialize last_turn
      message = screen.getMessage();          //Initalize message
      player1_taken.setBounds(675, 10, 150, 25);   //Set bounds for player1_taken
      player2_taken.setBounds(675, 210, 150, 25);  //Set bounds for player2_taken
      last_turn.setBounds(675, 410, 150, 100);     //Set bounds for last_turn
      message.setBounds(675, 460, 150, 100);       //Set bounds for message
      screen.add(player1_taken);       //Add player1_taken to screen
      screen.add(player2_taken);       //Add player1_taken to screen
      screen.add(last_turn);           //Add last_turn to screen
      screen.add(message);             //Add message to screen
   }
      
   public static class listen implements KeyListener 
   {
      
      public void keyTyped(KeyEvent e)
      {
         
      }
      
      public void keyPressed(KeyEvent e)
      {
         
      }
      
      public void keyReleased(KeyEvent e)
      {
         screen.processUserInput(e.getKeyCode());
      }
   }

}
