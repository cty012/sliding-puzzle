package solutions;

import game.Game;
import game.Move;
import numpuzzle.NumPuzzleGame;

import java.util.PriorityQueue;

public class AStarSolution implements Solution {
    @Override
    public String solve(Game game) {
        /*
         * This solution uses a priority queue to iterate over all possible states that can be reached
         * by the starting state
         */
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) ->
                Integer.compare(game.evaluate(o1), game.evaluate(o2)));
        pq.add(game.getState());

        int minDisplacement = ((NumPuzzleGame)game).getDisplacement(game.getState());

        int count = 0;
        while (!pq.isEmpty()) {
            // Get the game state with the least evaluation score
            String nextState = pq.remove();
            game.loadState(nextState);

            int disp = ((NumPuzzleGame)game).getDisplacement(nextState);
            if (disp < minDisplacement) {
                minDisplacement = disp;
                System.out.printf("%d/0: %d iterations\n", disp, count);
            }

            // Return if the final state has been reached
            if (game.isFinalState()) {
                return game.getPastMoves();
            }

            // Add the new states generated by the valid moves
            for (Move move : game.getValidMoves()) {
                game.move(move);
                String state = game.getState();
                // Trim all states that are far from minimum
                disp = ((NumPuzzleGame)game).getDisplacement(state);
                if (disp - minDisplacement <= 4) pq.add(state);
                game.undo();
            }
            count++;
        }

        // Return empty string if no solution found
        return "";
    }
}