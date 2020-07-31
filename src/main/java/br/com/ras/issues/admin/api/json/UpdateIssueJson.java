package br.com.ras.issues.admin.api.json;

import br.com.ras.issues.admin.issue.domain.IssueStatus;

public class UpdateIssueJson {

    public IssueStatus status;

    public UpdateIssueJson() { }

    public UpdateIssueJson(IssueStatus status) {
        this.status = status;
    }
}
