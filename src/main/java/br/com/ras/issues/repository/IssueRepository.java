package br.com.ras.issues.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.ras.issues.model.Issue;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findAll();
}
