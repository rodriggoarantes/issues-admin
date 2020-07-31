package br.com.ras.issues.service;

import br.com.ras.issues.model.IssueComment;
import br.com.ras.issues.model.IssueStatus;
import br.com.ras.issues.repository.IssueCommentRepository;
import br.com.ras.issues.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ras.issues.model.Issue;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.valueOf;

@Service
@Transactional
public class IssueService {

    private final IssueRepository issueRepository;
    private final IssueCommentRepository issueCommentRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, IssueCommentRepository issueCommentRepository) {
        this.issueRepository = issueRepository;
        this.issueCommentRepository = issueCommentRepository;
    }

    public Issue create(String issueName) {
        Issue newIssue = new Issue();
        newIssue.setName(issueName);
        newIssue.setStatus(IssueStatus.NEW);
        return issueRepository.save(newIssue);
    }

    public List<Issue> all() {
        return issueRepository.findAll();
    }

    public Optional<IssueComment> addComment(String comment, String issueId) {
        final Optional<Issue> issue = issueRepository.findById(valueOf(issueId));
        if (issue.isPresent() && issue.get().getStatus() != IssueStatus.DONE) {
            return Optional.of(issueCommentRepository.save(new IssueComment(comment, issue.get())));
        }
        return Optional.empty();
    }

    public Issue get(String issueId) {
        final Optional<Issue> issue = issueRepository.findById(valueOf(issueId));
        issue.ifPresent(iss ->
                iss.setComments(issueCommentRepository.findByIssue_Id(iss.getId())));
        return issue.orElse(null);
    }

    public void update(Long issueId, IssueStatus newStatus) {
        final Optional<Issue> issueOpt = issueRepository.findById(issueId);
        issueOpt.ifPresent(issue -> {
            if (issue.getStatus() == IssueStatus.DONE && newStatus == IssueStatus.NEW
                    || issue.getStatus() == IssueStatus.NEW && newStatus == IssueStatus.DONE) {
                throw new RuntimeException(
                        String.format("Cannot change issue status from %s to %s", issue.getStatus(), newStatus));
            }
            issue.setStatus(newStatus);
        });
    }

}
