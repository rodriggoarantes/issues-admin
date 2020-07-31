package br.com.ras.issues.admin.issue.infra;

import br.com.ras.issues.admin.issue.domain.IssueId;
import br.com.ras.issues.admin.issue.domain.IssueIdSequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaIssueIdSequenceGenerator implements IssueIdSequenceGenerator {

    private final CrudIssueRepository issueRepository;

    @Autowired
    public JpaIssueIdSequenceGenerator(CrudIssueRepository repository) {
        this.issueRepository = repository;
    }

    @Override
    public IssueId nextId() {
        final IssueId currentId = issueRepository.findTopById();
        return currentId == null ? IssueId.from("1") : currentId.next();
    }
}