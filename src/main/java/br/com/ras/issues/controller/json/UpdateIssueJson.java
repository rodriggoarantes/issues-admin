package br.com.ras.issues.controller.json;

import br.com.ras.issues.model.IssueStatus;

public class UpdateIssueJson {

    public IssueStatus status;

    public UpdateIssueJson() { }

    public UpdateIssueJson(IssueStatus status) {
        this.status = status;
    }
}
