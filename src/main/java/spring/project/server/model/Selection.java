/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Veljko
 */
@Entity
public class Selection implements Serializable {

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    @NotNull()
    User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @NotNull()
    @JoinColumn(name = "CONFEDERATION_ID")
    private Confederation confederation;
    @Size(min = 3)
    @Column(unique = true)
    @NotEmpty()
    private String name;
    @PositiveOrZero
    private int rank;
    @PositiveOrZero
    private int points;
    private boolean active;
    @JsonIgnore
    @OneToMany(mappedBy = "away", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Match> awayMatches;

    @JsonIgnore
    @OneToMany(mappedBy = "host", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Match> hostMatches;


    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Confederation getConfederation() {
        return confederation;
    }

    public void setConfederation(final Confederation confederation) {
        this.confederation = confederation;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(final int rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(final int points) {
        this.points = points;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public List<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(final List<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }

    public List<Match> getHostMatches() {
        return hostMatches;
    }

    public void setHostMatches(final List<Match> hostMatches) {
        this.hostMatches = hostMatches;
    }


    @Override
    public String toString() {
        return "Selection{" + "id=" + id + ", confederation=" + confederation + ", name=" + name + ", rank=" + rank + ", points=" + points + ", active=" + active + ", user=" + user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Selection other = (Selection) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


}
