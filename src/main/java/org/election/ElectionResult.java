package org.election;

import java.util.Objects;

public class ElectionResult {
    private String candidateName;
    private Double validVoteCount;

    public ElectionResult() {}
    public ElectionResult(String candidateName,Double validVoteCount) {
        this.candidateName = candidateName;
        this.validVoteCount = validVoteCount;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Double getValidVoteCount() {
        return validVoteCount;
    }

    public void setValidVoteCount(Double validVoteCount) {
        this.validVoteCount = validVoteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ElectionResult that = (ElectionResult) o;
        return Objects.equals(candidateName, that.candidateName) && Objects.equals(validVoteCount, that.validVoteCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateName, validVoteCount);
    }

    @Override
    public String toString() {
        return "ElectionResult{" +
                "candidateName='" + candidateName + '\'' +
                ", validVoteCount='" + validVoteCount + '\'' +
                '}';
    }
}
