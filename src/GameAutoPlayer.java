
public class GameAutoPlayer {

    private static KeyboardInputClass keyboardInput;

    public static void main(String[] args) {
        keyboardInput = new KeyboardInputClass();
        int startingTrial = keyboardInput.getInteger(true, 1, 1, Integer.MAX_VALUE,
                "Enter an integer to start trials (default = 1):");

        int endingTrial = keyboardInput.getInteger(true, 100, 1, Integer.MAX_VALUE,
                "Enter an integer to end trials (default = 100):");

        char seeBoards = keyboardInput.getCharacter(true, 'N', "YN", 1,
                "Would you like to see each board? (Y/N: Default = N)");

        int totalNumberOfBoards = 0;

        for (int trialNumber = startingTrial; trialNumber <= endingTrial; trialNumber++) {
            QueenGameSolverOptimized.inputFromAutoPlayer(trialNumber, seeBoards);
            
            QueenGameSolverOptimized.main(args);

            totalNumberOfBoards = totalNumberOfBoards + QueenGameSolverOptimized.getStoredNumberOfBoards();
        }

        System.out.println("The total number of boards evaluated: " + totalNumberOfBoards);
    }
}
