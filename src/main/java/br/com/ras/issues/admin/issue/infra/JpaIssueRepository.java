package br.com.ras.issues.admin.issue.infra;

import br.com.ras.issues.admin.issue.domain.Issue;
import br.com.ras.issues.admin.issue.domain.IssueId;
import br.com.ras.issues.admin.issue.domain.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaIssueRepository implements IssueRepository {

    private final CrudIssueRepository crudIssueRepository;

    @Autowired
    public JpaIssueRepository(CrudIssueRepository crudIssueRepository) {
        this.crudIssueRepository = crudIssueRepository;
    }

    @Override
    public List<Issue> findAll() {
        final List<Issue> issues = new ArrayList<>();
        crudIssueRepository.findAll().forEach(issues::add);
        return issues;
    }

    @Override
    public Issue save(Issue issue) {
        return crudIssueRepository.save(issue);
    }

    @Override
    public Issue findBy(IssueId issueId) {
        return crudIssueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Nenhuma registro encontrado"));
    }
}
