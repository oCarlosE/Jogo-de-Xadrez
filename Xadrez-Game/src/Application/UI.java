package Application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ChessLayer.ChessMatch;
import ChessLayer.ChessPiece;
import ChessLayer.ChessPosition;
import ChessLayer.Color;

public class UI {

    //https://github.com/acenelio/chess-system-java/blob/master/src/application/UI.java
    public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc){
        try{
        String position= sc.nextLine();
        Character column= position.charAt(0);
        Integer row= Integer.parseInt(position.substring(1));
        return new ChessPosition(column, row);
        }
        catch(RuntimeException e){
            throw new InputMismatchException("Erro lendo posição: Valores válidos 1-8/a-h");
        }
    }

    public static void printBoard(ChessPiece[][] chessPieces) {
        for(int Width=0; Width<chessPieces.length; Width++){
            System.out.print(( 8-Width)+" ");
            for(int Height=0; Height< chessPieces.length; Height++){
                printPiece(chessPieces[Width][Height], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    
    public static void printPiece(ChessPiece chessPiece, boolean background){
        if(background){
            System.out.print(ANSI_GREEN_BACKGROUND);
        }
        if(chessPiece == null){
            System.out.print("-"+ ANSI_RESET);
        }
        else{
            if (chessPiece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + chessPiece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + chessPiece + ANSI_RESET);
            }
        }

        System.out.print(" ");
    }

    public static void printBoard(ChessPiece[][] chessPieces, boolean[][] possibleMoves){
        for(int Width=0; Width<chessPieces.length; Width++){
            System.out.print(( 8-Width)+" ");
            for(int Height=0; Height< chessPieces.length; Height++){
                printPiece(chessPieces[Width][Height], possibleMoves[Width][Height]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printMatch(ChessMatch match, List<ChessPiece> capChessPieces){
        printBoard(match.getPieces());
        System.out.println();
        
        printCapturedPieces(capChessPieces);
        System.out.println();

        System.out.println("Turn: "+ match.getTurn());

        if(!match.getCheckMate()){
            System.out.println("Waiting player: "+ match.getCurrentPlayer());

            if(match.getCheck()){
                System.out.println("CHECK!!");
            }
        }
        else{
            System.out.println("CHECKMATE!!");
            System.out.println("Winner: "+match.getCurrentPlayer());
        }
    }

    private static void printCapturedPieces(List<ChessPiece> captured){
        List<ChessPiece> whitePieces= captured.stream().filter(x->x.getColor()== Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> blackPieces= captured.stream().filter(x->x.getColor()== Color.BLACK).collect(Collectors.toList());

        System.out.println("Captured Pieces: ");
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(whitePieces.toArray()));
        System.out.print(ANSI_RESET);

        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(blackPieces.toArray()));
        System.out.print(ANSI_RESET);
    }
}
