package ChessLayer.ChessPieces;

import BoardGame.Board;
import BoardGame.Position;
import ChessLayer.ChessMatch;
import ChessLayer.ChessPiece;
import ChessLayer.Color;

public class Pawn extends ChessPiece{

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch= chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean [][] matriz= new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p= new Position(0, 0);

        if(getColor()== Color.WHITE){
            //Acima
            p.setValues(position.getRow()-1, position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                matriz[p.getRow()][p.getColumn()]= true;
            }

            p.setValues(position.getRow()-2, position.getColumn());
            Position p2= new Position(position.getRow()-1, position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount()==0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)){
                matriz[p.getRow()][p.getColumn()]= true;
            }

            //Diagonal
            p.setValues(position.getRow()-1, position.getColumn()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                matriz[p.getRow()][p.getColumn()]= true;
            }

            p.setValues(position.getRow()-1, position.getColumn()+1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                matriz[p.getRow()][p.getColumn()]= true;
            }

            //en passant white
            if(position.getRow()==3){
                Position posAux= new Position(position.getRow(), position.getColumn()-1);
                if(getBoard().positionExists(posAux) && isThereOpponentPiece(posAux) && getBoard().piece(posAux)== chessMatch.getEnPassantVulnerable()){
                    matriz[posAux.getRow()-1][posAux.getColumn()]=true;
                }

                Position posAux2= new Position(position.getRow(), position.getColumn()+1);
                if(getBoard().positionExists(posAux2) && isThereOpponentPiece(posAux2) && getBoard().piece(posAux2)== chessMatch.getEnPassantVulnerable()){
                    matriz[posAux2.getRow()-1][posAux2.getColumn()]=true;
                }
            }

        }
        else{
            //Acima
            p.setValues(position.getRow()+1, position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                matriz[p.getRow()][p.getColumn()]= true;
            }

            p.setValues(position.getRow()+2, position.getColumn());
            Position p2= new Position(position.getRow()+1, position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount()==0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)){
                matriz[p.getRow()][p.getColumn()]= true;
            }

            //Diagonal
            p.setValues(position.getRow()+1, position.getColumn()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                matriz[p.getRow()][p.getColumn()]= true;
            }

            p.setValues(position.getRow()+1, position.getColumn()+1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                matriz[p.getRow()][p.getColumn()]= true;
            }

            //en passant black
            if(position.getRow()==4){
                Position posAux= new Position(position.getRow(), position.getColumn()-1);
                if(getBoard().positionExists(posAux) && isThereOpponentPiece(posAux) && getBoard().piece(posAux)== chessMatch.getEnPassantVulnerable()){
                    matriz[posAux.getRow()+1][posAux.getColumn()]=true;
                }

                Position posAux2= new Position(position.getRow(), position.getColumn()+1);
                if(getBoard().positionExists(posAux2) && isThereOpponentPiece(posAux2) && getBoard().piece(posAux2)== chessMatch.getEnPassantVulnerable()){
                    matriz[posAux2.getRow()+1][posAux2.getColumn()]=true;
                }
            }
        }
        return matriz;
    }

    @Override
    public String toString() {
        return "P";
    }

}
