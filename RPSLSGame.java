// ldelamotte17@georgefox.edu

// import Scanner
import java.util.Scanner;

/**
 *
 * @author liliedelamotte
 */
public class RPSLSGame 
{
    public static void main(String[] args) 
    {
        // declare variables
        int menuChoice = 1;
        int numRoundsToPlay = 1;
        int p1Move = 1;
        int p2Move = 1;
        int[] scores = new int[3];
        String player1Label = "Player 1";
        String player2Label = "Player 2";
        Scanner in = new Scanner(System.in);
        RockPaperScissorsLizardSpock myGame;
        
        // displays a welcome message as soon as the game opens
        displayWelcomeMessage();
        // gets menu choice from the user
        menuChoice = getMenuChoice(in);
        // main game logic
        while (menuChoice != 3)
        {
            // creates a new game object everytime user wants to play
            myGame = new RockPaperScissorsLizardSpock();
            // runs a human vs. computer game
            if (menuChoice == 1)
            {
                // asks user how many rounds they wish to play
                numRoundsToPlay = getNumRoundsToPlay(in);
                // runs the game however many times user wants
                for (int i = 0; i < numRoundsToPlay; i++)
                {
                    // gets the human move
                    p1Move = getPlayerMove(in, myGame, player1Label); 
                    // gets the computer move
                    myGame.makeMove(p1Move);
                    // prints out results as String literal
                    displayResults(myGame);
                    // adds 1 point to whoever won game
                    scores[myGame.determineWinner()]++;
                }  
            }
            // runs a human vs. human game
            else
            {
                // asks user how many rounds they wish to play
                numRoundsToPlay = getNumRoundsToPlay(in);
                // runs the game however many times the user wants
                for (int i = 0; i < numRoundsToPlay; i++)
                {
                    // gets first human move
                    p1Move = getPlayerMove(in, myGame, player1Label);
                    // gets second human move
                    p2Move = getPlayerMove(in, myGame, player2Label);
                    // makes moves on the game object
                    myGame.makeMove(p1Move, p2Move);
                    // prints out results in a String literal
                    displayResults(myGame);
                    // adds 1 point to whoever won game
                    scores[myGame.determineWinner()]++;
                }  
            }
            // displays final game scores
            displayWinner(scores, menuChoice);
            // resets game scores
            resetScores(scores);
            // asks user if they would like to continue to play or not
            menuChoice = getMenuChoice(in);
        }
        // displays an exit message after user has decided to quit
        displayExitMessage();
    }
    
    /**
     * displays a welcome message
     */
    private static void displayWelcomeMessage()
    {
        System.out.println("------------------------------------------------");
        System.out.println("Welcome to Rock, Paper, Scissors, Lizard, Spock!");
        System.out.println("------------------------------------------------");
        System.out.println();
    }
    
    /**
     * gets menu choice from user
     * @param in, the Scanner
     * @return menuChoice, what game type user wants to play
     */
    private static int getMenuChoice(Scanner in)
    {
        int menuChoice;
        // displays the menu Choices to the user
        displayMenuChoices();
        // gets choice from user
        System.out.print("Your choice: ");
        menuChoice = in.nextInt();
        System.out.println();
        // continues to ask user for menu choice if input is not 1, 2, or 3
        while (menuChoice != 1 && menuChoice != 2 && menuChoice != 3)
        {
            // prints error message
            System.out.println("ERROR: that is an invalid input.");
            displayMenuChoices();
            // asks user for menu choice again
            System.out.print("Your choice: ");
            menuChoice = in.nextInt();
            System.out.println();
        }
        // returns the menu choice to the main method
        return menuChoice;
    }
    
    /**
     * displays the three menu choices to user
     */
    private static void displayMenuChoices()
    {
       System.out.println("Please choose from the following menu: ");
       System.out.println("1. Play Human vs. Computer (1-player)");
       System.out.println("2. Play Human vs. Human (2-player)");
       System.out.println("3. Quit");
    }
    
    /**
     * gets the number of rounds user wants to play
     * @param in, the Scanner
     * @return numRoundsToPlay
     */
    private static int getNumRoundsToPlay(Scanner in)
    {
        // creates a number of rounds to play variable
        int numRoundsToPlay;
        // asks user how many rounds they would like to play
        System.out.println("How many rounds would you like to play?");
        System.out.print("Your choice: ");
        numRoundsToPlay = in.nextInt();
        System.out.println();
        // continues to ask user for numRoundsToPlay if input is less than 1
        while (numRoundsToPlay < 0)
        {
            // prints error message
            System.out.println("ERROR: that is an invalid input.");
            // asks user for numRoundsToPlay again
            System.out.println("How many rounds would you like to play?");
            System.out.print("Your choice: ");
            numRoundsToPlay = in.nextInt();
        }
        // resets the buffer
        in.nextLine();
        // returns number of rounds user want to play
        return numRoundsToPlay;
    }
    
    /**
     * gets the player move from user converts String to index
     * @param in, the Scanner
     * @param RockPaperScissorsLizardSpock myGame, the game object
     * @return playerMoveIndex, where String input is located in array
     */
    private static int getPlayerMove(Scanner in, 
            RockPaperScissorsLizardSpock myGame, String label)
    {
        // creates an array of Strings with all the move options
        final String[] MOVE_OPTIONS =
        { "Rock", "Paper", "Scissors", "Lizard", "Spock" };
        String playerMove;
        int playerMoveIndex = 0;
        int i = 0;
        boolean moveFound = false;
        
        // asks user to choose one of five move options
        System.out.print(label + ": choose Rock, Paper, Scissors, "
                + "Lizard, or Spock: ");
        playerMove = in.nextLine();
        System.out.println();
        // for as long as the input is invalid the user is prompted again
        while (myGame.isValidMove(playerMove) == false) 
        {
            // prints error message
            System.out.println("ERROR: that is an invalid input.");
            // asks user for input again
            System.out.print(label + ": choose Rock, Paper, Scissors, "
                + "Lizard, or Spock: ");
            playerMove = in.nextLine();
            System.out.println();
        }
        
        // assigns move to index where String is located in MOVE_OPTIONS array
        while (i < MOVE_OPTIONS.length && !moveFound)
        {
            // enters loop if move has been found
            if (playerMove.equals(MOVE_OPTIONS[i]))
            {
                // assigns an index
                playerMoveIndex = i;
                // causes loop to exit once move has been found
                moveFound = true;
            }
            // increases counter to avoid infinite loop
            i++;
        }
        // returns the index where String is located in MOVE_OPTIONS array
        return playerMoveIndex;
    }
    /**
     * displays results as a string literal
     * @param myGame, the game object
     */
    private static void displayResults(RockPaperScissorsLizardSpock myGame)
    {
        System.out.println("------------------------------------------------");
        System.out.println("Results: " + myGame.getResults());
        System.out.println("------------------------------------------------");
        System.out.println();
    }
    
    /**
     * displays scores as p1 wins, p2 or computer wins, and losses
     * @param scores, the array of wins, losses, and ties
     */
    private static void displayWinner(int[] scores, int menuChoice)
    {
        if (menuChoice == 1)
        {
            System.out.println();
            System.out.println("------------------------------------------"
                    + "--------------------");
            System.out.println("Game Results -- Player 1 Wins: " + scores[1] + 
                    " | Computer Wins: " + scores[2] + " | Ties: " + scores[0]);
            System.out.println("------------------------------------------"
                    + "--------------------");
            System.out.println();
        }
        else
        {
            System.out.println();
            System.out.println("------------------------------------------"
                    + "--------------------");
            System.out.println("Game Results -- Player 1 Wins: " + scores[1] + 
                    " | Player 2 Wins: " + scores[2] + " | Ties: " + scores[0]);
            System.out.println("------------------------------------------"
                    + "--------------------");
            System.out.println();
        }
    }
    
    /**
     * resets the scores array to 0 after game is finished
     * @param scores, an array of number of wins, losses, ties
     * @return scores, and array of number of wins, losses, ties
     */
    private static int[] resetScores(int[] scores)
    {
        for (int i = 0; i < scores.length; i++)
        {
            scores[i] = 0;
        }
        return scores;
    }
    
    /**
     * displays an exit message
     */
    private static void displayExitMessage()
    {
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("Thanks for playing!");
        System.out.println("------------------------------------------------");
    }
}