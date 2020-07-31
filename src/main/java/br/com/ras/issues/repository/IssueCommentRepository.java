package br.com.ras.issues.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.ras.issues.model.IssueComment;

import java.util.List;

public interface IssueCommentRepository extends CrudRepository<IssueComment, Long> {
    List<IssueComment> findByIssue_Id(Long issue);
}
