import java.util.*;

public class Cell<anyType>
{
   private anyType value;
   private int row;
   private int col;
   
   public Cell(anyType v, int r, int c)
   {
      value = v;
      row = r;
      col = c;
   }
   
   public anyType getValue()
   {
      return value;
   } 
   
   public int getRow()
   {
      return row;
   }
   
   public int getCol()
   {
      return col;
   }
   
   public void setValue(anyType v)
   {
      value = v;
   }
   
   public void setRow(int r)
   {
      row = r;
   }
   
   public void setCol(int c)
   {
      col = c;
   }
}