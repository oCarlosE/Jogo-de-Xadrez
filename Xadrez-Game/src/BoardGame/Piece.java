package BoardGame;

public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setPosition(Position position){
        this.position= position;
    }

    

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] matriz= possibleMoves();
        for (int Width= 0; Width<matriz.length; Width++) {
            for(int Height= 0; Height<matriz.length; Height++){
                if(matriz[Width][Height]){
                    return true;
                }
            }
        }
        return false;
    }
}
