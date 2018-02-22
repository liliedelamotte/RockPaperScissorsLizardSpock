// ldelamotte17@georgefox.edu

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author liliedelamotte
 */
public class RockPaperScissorsLizardSpock
{
    // create String literal matrix GAMEBOARD for printing results
    private static final String[][] GAMEBOARD =
        {
            { "Rock ties rock.", "Paper covers rock.", "Rock crushes scissors", 
                "Rock crushes lizard.", "Spock vapoizes rock." },
            { "Paper covers rock.", "Paper ties paper.", "Scissors cuts paper.", 
                "Lizard eats paper.", "Paper covers spock." },
            { "Rock crushes scissors.", "Scissors cuts paper.", 
                "Scissors ties scissors.", "Scissors decapitates lizard.", 
                "Spock smashes scissors." },
            { " Rock crushes lizard.", "Lizard eats paper.", 
                "Scissors decapitates lizard.", "Lizard ties lizard.", 
                "Lizard poisons spock." },
            { "Spock vaporizes rock.", "Paper covers spock.", 
                "Spock smashes scissors.", "Lizard poisons spock.", 
                "Spock ties spock." }
        };
    
    // declare instance variables
    private int _p1Move;
    private int _p2Move;
    private final Random _rand;
    
    /**
     * class constructor
     */
    public RockPaperScissorsLizardSpock()
    {
        _p1Move = 0;
        _p2Move = 0;
        _rand = new Random();
    }
    
    /**
     * sets player and computer moves to the integer sent in
     * @param p1Move, player 1's move
     */
    public void makeMove(int p1Move)
    {
        this.makeMove(p1Move, getComputerMove());
    }
    
    /**
     * sets the moves to the integers sent in
     * @param p1Move, player 1's move
     * @param p2Move, player 2's move
     */
    public void makeMove(int p1Move, int p2Move)
    {
        _p1Move = p1Move;
        _p2Move = p2Move;
    }
    
    /**
     * returns 0 for a tie, 1 if p1 won, 2 if p1's opponent won
     * @return 
     */
    public int determineWinner()
    {
        final int[][] SCOREBOARD =
        {
            { 0, 2, 1, 1, 2 },
            { 1, 0, 2, 2, 1 },
            { 2, 1, 0, 1, 2 },
            { 2, 1, 2, 0, 1 },
            { 1, 2, 1, 2, 0 }
        };
        return SCOREBOARD[_p1Move][_p2Move];
    }
    
    /**
     * returns the official result of the round as a String
     * @return 
     */
    public String getResults()
    {
        return GAMEBOARD[_p1Move][_p2Move];
    }
    
    /**
     * gets the computer's move by generating a random number
     * @return 
     */
    public int getComputerMove()
    {
        // gets a random value between 0 and 4 inclusive
        return _rand.nextInt(4);
    }
    
    /**
     * checks whether or not a move is valid or not
     * @param move, String move sent in by user
     * @return boolean, whether or not move is valid
     */
    public boolean isValidMove(String move)
    {
        final String[] MOVE_OPTIONS =
        { "Rock", "Paper", "Scissors", "Lizard", "Spock" };
        return (Arrays.asList(MOVE_OPTIONS).contains(move));
    }
}