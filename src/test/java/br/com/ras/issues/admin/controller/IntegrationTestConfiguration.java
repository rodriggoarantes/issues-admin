package br.com.ras.issues.admin.controller;

import br.com.ras.issues.admin.api.IssueController;
import br.com.ras.issues.admin.issue.domain.IssueIdSequenceGenerator;
import br.com.ras.issues.admin.issue.infra.CrudIssueRepository;
import br.com.ras.issues.admin.issue.infra.JpaIssueIdSequenceGenerator;
import br.com.ras.issues.admin.issue.infra.JpaIssueRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.ras.issues.admin.issue.domain.IssueRepository;
import br.com.ras.issues.admin.issue.application.IssueService;

@Configuration
@EnableAutoConfiguration
public class IntegrationTestConfiguration {

    @Bean
    public IssueRepository issueRepository(CrudIssueRepository crudIssueRepository) {
        return new JpaIssueRepository(crudIssueRepository);
    }

    @Bean
    public IssueController issueController(IssueService issueService) {
        return new IssueController(issueService);
    }

    @Bean
    public IssueIdSequenceGenerator issueIdSequenceGenerator(CrudIssueRepository crudIssueRepository) {
        return new JpaIssueIdSequenceGenerator(crudIssueRepository);
    }

    @Bean
    public IssueService issueService(IssueRepository issueRepository,
                                     IssueIdSequenceGenerator issueIdSequenceGenerator) {
        return new IssueService(issueRepository, issueIdSequenceGenerator);
    }
}
