package br.com.ras.issues.admin.issue.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class IssueComment {

    @Id
    @GeneratedValue(strategy= AUTO)
    private Long id;

    private String comment;

    private IssueComment() {}

    public IssueComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }
}
