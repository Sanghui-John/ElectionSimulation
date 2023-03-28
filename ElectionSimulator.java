// Election Simulator

// This class is an output program of Election Simulator.
// by adjusting constant values, one can run customized election simulation.
// constant values:
//      int NUM_SIMS: The number of simulations to run
//      int NUM_DISTS: The number of districts in each simulation
//      double POLL_AVG: The polling average for our candidate
//      double POLL_ERROR: The margin of error of the polls

// Our main method consists of 3 parts.
//      1. Intro : line34 ; calls the method "intro"
//      2. Body(simulation) : line36 ~ line81
//      3. Outro(summary) : line84 ; calls the method "sumamry"

import java.util.*;

public class ElectionSimulator {

    public static final int NUM_SIMS = 5;
    public static final int NUM_DISTS = 10;
    public static final double POLL_AVG = .52;
    public static final double POLL_ERR = .05;

    public static void main(String[] args) {
        Random randomNum = new Random();
        intro();

        double sumOurPercentage = 0;
        for(int numSims = 1; numSims <= NUM_SIMS; numSims++) {
            System.out.println("Running simulation #" + numSims + ":");
            
            int totalVotes = 0;
            int totalOurVotes = 0;
            int totalOpponentVotes = 0;

            for(int district = 1; district <= NUM_DISTS; district ++) {
                int numVoters = randomNum.nextInt(1000) + 1;
                double pollingError = randomNum.nextGaussian() * 0.5 * POLL_ERR;
                double votePercentage = POLL_AVG + pollingError;
                int ourVotes = (int)(numVoters * votePercentage);

                totalVotes = totalVotes + numVoters;
                totalOurVotes = totalOurVotes + ourVotes;
                totalOpponentVotes = totalOpponentVotes + (numVoters - ourVotes);
            }

            boolean winOrLose = totalOurVotes >= (totalVotes * 0.5);

            double ourPercentage = (1.0 * totalOurVotes / totalVotes) * 100;
            double opponentPercentage = (1.0 * totalOpponentVotes / totalVotes) * 100;
            sumOurPercentage = sumOurPercentage + ourPercentage;

            double ourPercentageTwoDecimal = (double) Math.round(ourPercentage * 100) / 100;
            double opponentPercentageTwoDecimal = (double) Math.round(opponentPercentage * 100) / 100;

            int ourSymbol = totalOurVotes / 100;
            int opponentSymbol = totalOpponentVotes / 100;

            System.out.println("  Win? " + winOrLose);
            System.out.println("  Results: " + totalOurVotes + " (" + ourPercentageTwoDecimal + "%) - "
                                + totalOpponentVotes + " (" + opponentPercentageTwoDecimal + "%)");
            System.out.print("  Visualization: ");
            
            for(int plusSymbol = 0; plusSymbol < ourSymbol; plusSymbol++) {
                System.out.print("+");
            }
            System.out.println();
            System.out.print("                 ");
            for(int minusSymbol = 0; minusSymbol < opponentSymbol; minusSymbol++) {
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();

        summary(sumOurPercentage);
    }

    // Prints the intro of a simulation
    // Intro consists general information of the conducted simulation    
    public static void intro() {
        System.out.println("Welcome to the Election Simulator!");
        System.out.println("Running " + NUM_SIMS + " simulations of " + NUM_DISTS + " districts.");
        System.out.println("Our candidate is polling at " + POLL_AVG * 100 + "% with a " 
                            + POLL_ERR * 100 + "% margin of error.");
        System.out.println();
    }
    
    // Prints the summary(outro) of a simulation
    // Summary computes and prints the Average Vote Percentage
    // Parameters:
    //      double sumOurPercentage: the sum of the percentage of votes that our candidate received in each simulation
    public static void summary(double sumOurPercentage) {
        double averagePercentage = sumOurPercentage / NUM_SIMS;
        double averagePercentageTwoDecimal = (double) Math.round(averagePercentage * 100) / 100;
        System.out.println("Average vote percentage: " + averagePercentageTwoDecimal + "%");
    }

}
