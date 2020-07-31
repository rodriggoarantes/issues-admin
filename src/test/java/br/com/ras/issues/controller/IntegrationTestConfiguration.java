package br.com.ras.issues.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.ras.issues.repository.IssueCommentRepository;
import br.com.ras.issues.repository.IssueRepository;
import br.com.ras.issues.service.IssueService;

@Configuration
@EnableAutoConfiguration
public class IntegrationTestConfiguration {

    @Bean
    public IssueController issueController(IssueService issueService) throws Exception {
        return new IssueController(issueService);
    }

    @Bean
    public IssueService issueService(IssueRepository issueRepository, IssueCommentRepository issueCommentRepository) throws Exception {
        return new IssueService(issueRepository, issueCommentRepository);
    }
}
