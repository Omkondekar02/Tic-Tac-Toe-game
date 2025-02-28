package cognifyz.com;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            char[][] board = new char[3][3];
            initializeBoard(board);
            displayBoard(board);
            char currentPlayer = 'X';
            boolean gameWon = false;
            int moveCount = 0;

            // Game loop
            while (moveCount < 9 && !gameWon) {
                System.out.println("Player " + currentPlayer + "'s turn.");
                System.out.print("Enter row (0, 1, 2): ");
                int row = scanner.nextInt();
                System.out.print("Enter column (0, 1, 2): ");
                int col = scanner.nextInt();

                // Check for valid input
                if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                // Make the move
                board[row][col] = currentPlayer;
                moveCount++;

                // Display updated board
                displayBoard(board);

                // Check if the current player wins
                if (checkWinner(board, currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameWon = true;
                }

                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }

            // Check for draw if no winner
            if (!gameWon && moveCount == 9) {
                System.out.println("It's a draw!");
            }

            // Ask if players want to play again
            System.out.print("Do you want to play again? (yes/no): ");
            scanner.nextLine();  // Consume the newline
            String response = scanner.nextLine().toLowerCase();
            playAgain = response.equals("yes");

        } while (playAgain);

        scanner.close();
        System.out.println("Thank you for playing!");
    }

    // Initialize the board
    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';  // Empty spaces
            }
        }
    }

    // Display the board
    public static void displayBoard(char[][] board) {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    // Check if the current player has won
    public static boolean checkWinner(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||  // Row
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) { // Column
                return true;
            }
        }
        // Diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) || 
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }
}
