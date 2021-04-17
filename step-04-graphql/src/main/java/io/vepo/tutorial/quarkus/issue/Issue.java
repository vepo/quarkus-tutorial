package io.vepo.tutorial.quarkus.issue;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.vepo.tutorial.quarkus.user.User;

@Entity
@Table(name = "tb_issues")
public class Issue {

    public static class IssueBuilder {
        private String title;
        private String description;
        private User reporter;

        private IssueBuilder() {
        }

        public IssueBuilder title(String title) {
            this.title = title;
            return this;
        }

        public IssueBuilder description(String description) {
            this.description = description;
            return this;
        }

        public IssueBuilder reporter(User reporter) {
            this.reporter = reporter;
            return this;
        }

        public Issue build() {
            return new Issue(this);
        }
    }

    public static IssueBuilder builder() {
        return new IssueBuilder();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    @Size(min = 5, max = 256)
    private String title;

    @Column
    @NotNull
    @Size(min = 15, max = 1024)
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reporter_id", referencedColumnName = "id")
    @NotNull
    private User reporter;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id", referencedColumnName = "id")
    private User assignee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_issues_watchers", joinColumns = @JoinColumn(name = "issue_id"), inverseJoinColumns = @JoinColumn(name = "watcher_id"))
    private Set<User> watchers;

    private Issue(IssueBuilder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.reporter = builder.reporter;
    }

    public Issue() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Set<User> getWatchers() {
        return watchers;
    }

    public void setWatchers(Set<User> watchers) {
        this.watchers = watchers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignee, description, id, reporter, title, watchers);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Issue other = (Issue) obj;
        return Objects.equals(assignee, other.assignee) && Objects.equals(description, other.description)
                && Objects.equals(id, other.id) && Objects.equals(reporter, other.reporter)
                && Objects.equals(title, other.title) && Objects.equals(watchers, other.watchers);
    }

    @Override
    public String toString() {
        return String.format("Issue [id=%s, title=%s, description=%s, reporter=%s, assignee=%s, watchers=%s]", id,
                             title, description, reporter, assignee, watchers);
    }

}
