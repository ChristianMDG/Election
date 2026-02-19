package org.election;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DataRetriever {

    public long countAllVotes(){
        DBConnection dbConnection = new DBConnection();
        long count = 0;
        String countAllVotesSql = "select count(*) from vote";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(countAllVotesSql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getLong(1);
            }
            return count;
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<VoteTypeCount> countVotesByType(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<CandidateVoteCount> countValidVotesByCandidate(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public VoteSummary computeVoteSummary(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double computeTurnoutRate(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ElectionResult findWinner(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
