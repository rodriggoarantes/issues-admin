package br.com.ras.issues.controller.json;

import br.com.ras.issues.model.Issue;

public class NewIssueJson {

    public String name;

    public NewIssueJson() { }

    public NewIssueJson(String name) {
        this.name = name;
    }

    public Issue asModel() {
        Issue issue = new Issue();
        issue.setName(name);
        return issue;
    }
}
