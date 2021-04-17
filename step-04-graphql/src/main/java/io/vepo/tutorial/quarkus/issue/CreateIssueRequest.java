package io.vepo.tutorial.quarkus.issue;

import java.util.Objects;

public class CreateIssueRequest {

    private String title;
    private String description;
    private int reporterId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, reporterId, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CreateIssueRequest other = (CreateIssueRequest) obj;
        return Objects.equals(description, other.description) && reporterId == other.reporterId
                && Objects.equals(title, other.title);
    }

    @Override
    public String toString() {
        return String.format("CreateIssueRequest [title=%s, description=%s, reporterId=%s]", title, description,
                             reporterId);
    }

}
