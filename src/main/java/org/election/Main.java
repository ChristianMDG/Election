package org.election;


public class Main {
    public static void main(String[] args) {
    DataRetriever dataRetriever = new DataRetriever();
        System.out.println("totalVote="+dataRetriever.countAllVotes());
    }
}