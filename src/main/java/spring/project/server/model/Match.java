/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Veljko
 */

@Entity
@Table(name = "MATCH_TABLE")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "MATCH_DATE")
    @NotNull()
    private LocalDate date;

    @OneToOne()
    @JoinColumn(name = "MATCH_HOST")
    @NotNull()
    private Selection host;

    @OneToOne()
    @JoinColumn(name = "MATCH_AWAY")
    @NotNull()
    private Selection away;

    @PositiveOrZero
    private int hostGoals;

    @PositiveOrZero
    private int awayGoals;

    @ManyToOne
    @NotNull
    private MatchType matchType;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NotNull
    User user;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public Selection getHost() {
        return host;
    }

    public void setHost(final Selection host) {
        this.host = host;
    }

    public Selection getAway() {
        return away;
    }

    public void setAway(final Selection away) {
        this.away = away;
    }

    public int getHostGoals() {
        return hostGoals;
    }

    public void setHostGoals(final int hostGoals) {
        this.hostGoals = hostGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(final int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(final MatchType matchType) {
        this.matchType = matchType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", date=" + date + ", host=" + host + ", away=" + away + ", hostGoals=" + hostGoals + ", awayGoals=" + awayGoals + ", matchType=" + matchType + ", user=" + user + '}';
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.date);
        hash = 59 * hash + Objects.hashCode(this.host);
        hash = 59 * hash + Objects.hashCode(this.away);
        hash = 59 * hash + this.hostGoals;
        hash = 59 * hash + this.awayGoals;
        hash = 59 * hash + Objects.hashCode(this.matchType);
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
        final Match other = (Match) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}
