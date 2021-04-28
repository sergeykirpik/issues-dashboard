package com.example.springdemo.issuesdashboard.events;

import com.example.springdemo.issuesdashboard.github.GithubClient;
import com.example.springdemo.issuesdashboard.github.RepositoryEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventsController {

    private final GithubProjectRepository repository;

    private final GithubClient githubClient;

    public EventsController(GithubProjectRepository repository, GithubClient githubClient) {
        this.repository = repository;
        this.githubClient = githubClient;
    }

    @GetMapping("/events/{repoName}")
    @ResponseBody
    public RepositoryEvent[] fetchEvents(@PathVariable String repoName) {
        GithubProject project = repository.findByRepoName(repoName);
        return githubClient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
    }
}
