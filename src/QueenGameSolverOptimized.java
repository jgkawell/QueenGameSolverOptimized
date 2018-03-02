
public class QueenGameSolverOptimized {

    private static char[][] board;
    private static int size; //the number of X's the user wants to find a board for which = board.length
    private static KeyboardInputClass keyboardInput;
    private static char showBoards;
    private static int numberOfBoards;
    private static int columnJump;
    private static int storedNumberOfBoards;

    public static void main(String[] args) {
        buildBoard();

        int startingColumn;
        
        if(isEven()){
            startingColumn = 1 % board.length;
        }else{
            startingColumn = 4 % board.length;
        }

        columnJumpDecider();        

        if (placeX3rd(0, startingColumn)) {
            //printBoard();
        } else {
            System.out.println("No possible board that fits the requirements.");
        }

        System.out.println(size + ": " + numberOfBoards);
        storedNumberOfBoards = numberOfBoards;
        numberOfBoards = 0;
    }
    
    private static void buildBoard() {

        board = new char[size][size];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                board[row][column] = '*';
            }//end inner for loop
        }//end outer for loop
    }

    private static boolean placeX3rd(int row, int column) {
        int passedColumn = column;
        int loopLimit = board.length;

        if (row < board.length) {
            for (; column < loopLimit; column++) {
                boolean validPosition = checkValidity(row, column);
                board[row][column] = 'X';
                
                showEachBoard();

                if (validPosition) {
                    int startingColumnForNextRow;
                    //check to see if prime, if true iterate over 2 columns to make it O(N).
                    //Otherwise iterate over N (some integer value) for better efficiency.
                    if (isPrime()) {
                        startingColumnForNextRow = (column + 2) % (board.length);
                    } else {
                        columnJumpDecider();
                        startingColumnForNextRow = (column + 4) % board.length;
                    }//end of inner if/else

                    if (placeX3rd(row + 1, startingColumnForNextRow)) {
                        return true;
                    } else {
                        board[row][column] = '*';
                    }//end of inner if/else
                } else {
                    board[row][column] = '*';
                }//end of outer if/else

                if (column == board.length - 1) {
                    column = -1;
                    loopLimit = passedColumn;
                }
            }//end for loop
            return false;
        }//end outer if
        return true;
    }

    private static void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                System.out.print(board[row][column] + " ");
            }//end inner for loop
            System.out.println("");
        }//end outer for loop
    }

    private static boolean checkValidity(int row, int column) {
        numberOfBoards++;
        int columnLeft = column;
        int columnRight = column;

        for (int rowUp = row - 1; rowUp >= 0; rowUp--) {
            if (board[rowUp][column] == 'X') {
                return false;
            }//end if
            columnLeft--;
            if (columnLeft >= 0) {
                if (board[rowUp][columnLeft] == 'X') {
                    return false;
                }//end inner if
            }//end outer if
            columnRight++;
            if (columnRight < board.length) {
                if (board[rowUp][columnRight] == 'X') {
                    return false;
                }//emd inner if
            }//end outer if
        }//end of for loop
        return true;
    }
    
    private static boolean isPrime() {
        if (size % 2 == 0) {
            return false;
        }

        for (int i = 3; i < size; i += 2) {
            if (size % i == 0) {
                return false;
            }
        }
        columnJump = 1;
        return true;
    }

    public static void inputFromAutoPlayer(int sideLengthFromAuto, char showBoardsFromAuto) {
        size = sideLengthFromAuto;
        showBoards = showBoardsFromAuto;
    }

    private static boolean isEven() {
        if (size % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static void showEachBoard() {
        if (showBoards == 'Y') {
            printBoard();
            System.out.println("The number of boards evaluated is: " + numberOfBoards);
            //keyboardInput.getKeyboardInput("");
        }
    }

    private static void columnJumpDecider() {
        if (size % 5 == 0 && size % 7 != 0) {
            columnJump = 7;
        } else if (size % 5 != 0 && size % 7 == 0) {
            columnJump = 5;
        } else if (size % 5 == 0 && size % 7 == 0) {
            columnJump = 1;
        } else {
            columnJump = 7;
        }
    }
    
    public static int getStoredNumberOfBoards() {
        return storedNumberOfBoards;
    }
}//end class
//*******************************************************************************
//*******************************************************************************
