package br.com.ras.issues.admin.api;

import br.com.ras.issues.admin.api.json.IssueJson;
import br.com.ras.issues.admin.api.json.NewIssueCommentJson;
import br.com.ras.issues.admin.api.json.NewIssueJson;
import br.com.ras.issues.admin.api.json.UpdateIssueJson;
import br.com.ras.issues.admin.issue.application.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.ras.issues.admin.issue.domain.Issue;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping(value = "/{issueId}", method = GET)
    @ResponseBody
    public IssueJson get(@PathVariable String issueId) {
        Issue issue = issueService.get(issueId);
        return IssueJson.fromModel(issue);
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public List<IssueJson> all() {
        return issueService.all().stream()
                .map(IssueJson::fromModel)
                .collect(toList());
    }

    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    public Long newIssue(@RequestBody NewIssueJson newIssueJson) {
        Issue createdIssue = issueService.create(newIssueJson.name);
        return createdIssue.getId().value();
    }

    @RequestMapping(value = "/{issueId}", method = PUT)
    @ResponseStatus(OK)
    public ResponseEntity<Void> update(@PathVariable String issueId, @RequestBody UpdateIssueJson updateIssueJson) {
        try {
            issueService.update(issueId, updateIssueJson.status);
            return ok().build();
        } catch (RuntimeException ex) {
            return status(FORBIDDEN).build();
        }
    }

    @RequestMapping(value = "/{issueId}/comment", method = POST)
    @ResponseStatus(OK)
    public ResponseEntity<Long> newIssueComment(@PathVariable String issueId, @RequestBody NewIssueCommentJson newIssueCommentJson) {
        try {
            issueService.addComment(newIssueCommentJson.comment, issueId);
            return ok().build();
        } catch (RuntimeException ex) {
            return status(FORBIDDEN).build();
        }
    }
}
