package br.com.ras.issues.admin.issue.domain;

import br.com.ras.issues.admin.issue.domain.IssueId;
import br.com.ras.issues.admin.issue.domain.Issue;

import java.util.List;

public interface IssueRepository {
    List<Issue> findAll();
    Issue save(Issue issue);
    Issue findBy(IssueId issueId);
}