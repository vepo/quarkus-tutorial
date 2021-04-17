package io.vepo.tutorial.quarkus.issue;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.vepo.tutorial.quarkus.user.Users;

@Path("/issue")
@ApplicationScoped
public class IssueEndpoint {
    @Inject
    Issues issues;

    @Inject
    Users users;

    @GET
    @Path("/{issueId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Issue getIssue(@PathParam("issueId") int issueId) {
        return issues.get(issueId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIssue(CreateIssueRequest request) {
        return Response.created(URI.create(String.format("/issue/%d",
                                                         +issues.create(Issue.builder().title(request.getTitle())
                                                                             .description(request.getDescription())
                                                                             .reporter(this.users.get(request.getReporterId()))
                                                                             .build())
                                                                .getId())))
                       .build();
    }
}
