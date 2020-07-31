package br.com.ras.issues.admin.issue.infra;

import br.com.ras.issues.admin.issue.domain.Issue;
import br.com.ras.issues.admin.issue.domain.IssueId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CrudIssueRepository extends CrudRepository<Issue, IssueId> {
    @Query("select max(t.id) from Issue t")
    IssueId findTopById();
}
