package br.com.ras.issues.admin.issue.application;

import br.com.ras.issues.admin.issue.domain.Issue;
import br.com.ras.issues.admin.issue.domain.IssueId;
import br.com.ras.issues.admin.issue.domain.IssueIdSequenceGenerator;
import br.com.ras.issues.admin.issue.domain.IssueRepository;
import br.com.ras.issues.admin.issue.domain.IssueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IssueService {

    private final IssueRepository issueRepository;
    private final IssueIdSequenceGenerator issueSequence;

    @Autowired
    public IssueService(IssueRepository issueRepository, IssueIdSequenceGenerator issueSequence) {
        this.issueRepository = issueRepository;
        this.issueSequence = issueSequence;
    }

    public Issue create(String issueName) {
        return issueRepository.save(new Issue(issueName, issueSequence.nextId()));
    }

    public List<Issue> all() {
        return issueRepository.findAll();
    }

    public void addComment(String comment, String issueId) {
        Issue issue = issueRepository.findBy(IssueId.from(issueId));
        issue.addComment(comment);
        issueRepository.save(issue);
    }

    public Issue get(String issueId) {
        return issueRepository.findBy(IssueId.from(issueId));
    }

    public void update(String issueId, IssueStatus newStatus) {
        Issue issue = issueRepository.findBy(IssueId.from(issueId));
        issue.changeStatusTo(newStatus);
    }

}
