package org.election;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever dataRetriever = new DataRetriever();


        int totalVote = Math.toIntExact(dataRetriever.countAllVotes());
        System.out.println("------ TOTAL DES VOTES ------");
        System.out.println("Total des votes : " + totalVote);
        System.out.println();


        System.out.println("===== VOTES PAR TYPE =====");
        List<VoteTypeCount> votesByType = dataRetriever.countVotesByType();
        for (VoteTypeCount vtc : votesByType) {
            System.out.println(vtc.getVoteType() + " : " + vtc.getCount());
        }
        System.out.println();


        System.out.println("----- VOTES VALIDES PAR CANDIDAT -----");
        List<CandidateVoteCount> votesByCandidate = dataRetriever.countValidVotesByCandidate();
        for (CandidateVoteCount cvc : votesByCandidate) {
            System.out.println(cvc.getCandidateName() + " : " + cvc.getValidVoteCount());
        }
        System.out.println();


        System.out.println("===== RESUME DES VOTES =====");
        VoteSummary summary = dataRetriever.computeVoteSummary();
        System.out.println("VALID : " + summary.getValidCount());
        System.out.println("BLANK : " + summary.getBlankCount());
        System.out.println("NULL  : " + summary.getNullCount());
        System.out.println();

        double turnoutRate = dataRetriever.computeTurnoutRate();
        System.out.println("----- TAUX DE PARTICIPATION---------");
        System.out.printf("Taux de participation : %.2f%%\n", turnoutRate);
        System.out.println();


        System.out.println("------- RESULTAT DE L'ELECTION -------");
        ElectionResult winner = dataRetriever.findWinner();
        System.out.println("Gagnant : " + winner.getCandidateName()
                + " avec " + winner.getValidVoteCount() + " votes VALID");
    }
}