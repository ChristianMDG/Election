package org.election;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    public long countAllVotes(){
        DBConnection dbConnection = new DBConnection();
        long count = 0;
        String countAllVotesSql = "select count(vote.voter_id) as total_Vote from vote";
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

       DBConnection dbConnection = new DBConnection();
       List<VoteTypeCount> voteTypeCounts = new ArrayList<>();

       String voteTypeSql = """
               SELECT vote_type, COUNT(vote_type) AS count
               FROM vote
               GROUP BY vote_type;
               """;
       try(Connection connection = dbConnection.getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(voteTypeSql)){
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               VoteTypeCount voteTypeCount = new VoteTypeCount();
               voteTypeCount.setVoteType(VoteType.valueOf(resultSet.getString("vote_type")));
               voteTypeCount.setCount(resultSet.getInt("count"));
               voteTypeCounts.add(voteTypeCount);
           }
           return voteTypeCounts;
       }catch(Exception e) {
           throw new RuntimeException(e);
       }
    }

    public List<CandidateVoteCount> countValidVotesByCandidate(){
       DBConnection dbConnection = new DBConnection();
       List<CandidateVoteCount> candidateVoteCounts = new ArrayList<>();

       String candidateVoteCountsSql = """
               SELECT candidate.name as candidate_name,
                      count(
                      case when vote_type = 'VALID' then vote_type
                      end
                      )as valid_vote
               FROM vote
                        join candidate
                             on vote.candidate_id = candidate.id
               group by candidate.name
               """;

       try(Connection connection = dbConnection.getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(candidateVoteCountsSql)){
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               CandidateVoteCount candidateVoteCount = new CandidateVoteCount();
               candidateVoteCount.setCandidateName(resultSet.getString("candidate_name"));
               candidateVoteCount.setValidVoteCount(resultSet.getInt("valid_vote"));
               candidateVoteCounts.add(candidateVoteCount);
           }
           return candidateVoteCounts;
       }catch(Exception e) {
           throw new RuntimeException(e);
       }
    }

    public VoteSummary computeVoteSummary(){
      DBConnection dbConnection = new DBConnection();
      VoteSummary voteSummary = new VoteSummary();
      String voteSummarySql = """
              select  count(
                                      case
                                          when vote_type = 'VALID' then vote_type
                                          end
                              ) as validvote,
                              count(
                                      case
                                          when vote_type = 'BLANK' then vote_type
                                          end
                              ) as blankCount,
                              count(
                                      case
                                          when vote_type = 'NULL' then vote_type
                                          end
                              ) as nullCount
                      from vote;
              
      """;
      try (Connection connection = dbConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(voteSummarySql)){
          ResultSet resultSet = preparedStatement.executeQuery();
          while (resultSet.next()) {
              voteSummary.setValidCount(resultSet.getInt("validvote"));
              voteSummary.setBlankCount(resultSet.getInt("blankcount"));
              voteSummary.setNullCount(resultSet.getInt("nullcount"));
          }
          return voteSummary;
      }catch(Exception e) {
          throw new RuntimeException(e);
      }
    }

    public double computeTurnoutRate(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ElectionResult findWinner(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
