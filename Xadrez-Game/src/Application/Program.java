package Application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ChessLayer.ChessException;
import ChessLayer.ChessMatch;
import ChessLayer.ChessPiece;
import ChessLayer.ChessPosition;

public class Program {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        Scanner sc= new Scanner(System.in);
        List<ChessPiece> capChessPieces= new ArrayList<>();


        while(!chessMatch.getCheckMate()){
            try{
                UI.clearScreen();
                UI.printMatch(chessMatch, capChessPieces);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source= UI.readChessPosition(sc);

                boolean[][] possibleMoves= chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target= UI.readChessPosition(sc);

                ChessPiece capturedPiece= chessMatch.performChessMove(source, target);

                if(capturedPiece != null){
                    capChessPieces.add(capturedPiece);
                }

                if(chessMatch.getPromoted() != null){
                    System.out.print("Digite o nome de uma peça para a promoção do peão (Q/R/N/B): ");
                    String tipo= sc.nextLine().toUpperCase();
                    while(!tipo.equals("Q") && !tipo.equals("N") && !tipo.equals("B") && !tipo.equals("R")){
                        System.out.print("Valor inválido: Digite o nome de uma peça para a promoção do peão (Q/R/N/B): ");
                        tipo= sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(tipo);
                }
            }
            catch(ChessException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, capChessPieces);
    }
}
