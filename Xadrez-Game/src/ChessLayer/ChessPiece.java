package ChessLayer;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

public abstract class ChessPiece extends Piece{
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void increaseMoveCount(){
        this.moveCount++;
    }

    public void decreaseMoveCount(){
        this.moveCount--;
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }
    
    public boolean isThereOpponentPiece(Position position){
        ChessPiece piece= (ChessPiece)getBoard().piece(position);
        return piece != null && piece.getColor() != color;
    }
}
