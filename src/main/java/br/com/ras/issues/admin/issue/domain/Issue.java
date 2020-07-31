package br.com.ras.issues.admin.issue.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@Entity
public class Issue {

    @Id
    @Embedded
    private IssueId id;
    private String name;
    private IssueStatus status;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<IssueComment> comments;

    @SuppressWarnings("unused")
    private Issue() {}

    public Issue(String name, IssueId issueId) {
        this.id = issueId;
        this.name = name;
        this.status = IssueStatus.NEW;
    }

    public String getName() {
        return name;
    }

    public IssueId getId() {
        return id;
    }

    public List<IssueComment> getComments() {
        return unmodifiableList(comments);
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void changeStatusTo(IssueStatus newStatus) {
        if (this.status == IssueStatus.DONE && newStatus == IssueStatus.NEW
                || this.status == IssueStatus.NEW && newStatus == IssueStatus.DONE) {
            throw new RuntimeException(
                    String.format("Cannot change issue status from %s to %s", this.status, newStatus));
        }
        this.status = newStatus;
    }

    public void addComment(String comment) {
        if (status == IssueStatus.DONE) {
            throw new RuntimeException("Cannot add comment to done issue");
        }
        comments.add(new IssueComment(comment));
    }
}
