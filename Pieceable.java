import java.awt.*;

public interface Pieceable
{
   public String moveIsValid(Piece[][] board, int fromX, int fromY, int toX, int toY, boolean checkKing);
}