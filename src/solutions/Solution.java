package solutions;

import game.Game;

public interface Solution {
    /**
     * Search for the near optimal solution to the given game
     * @param game The game to be solved
     * @return A String of moves (represented in char) in order to solve the game
     */
    String solve(Game game, int verbose);
}
