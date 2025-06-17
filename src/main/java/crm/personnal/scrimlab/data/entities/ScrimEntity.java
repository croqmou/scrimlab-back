package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "scrim")
public class ScrimEntity {
    @Id
    @ColumnDefault("nextval('scrim_scrim_id_seq')")
    @Column(name = "scrim_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "team_one", nullable = false)
    private TeamEntity teamEntityOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "team_two")
    private TeamEntity teamEntityTwo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "mode")
    private ModeEntity mode;

    @Column(name = "bo")
    private Integer bo;

    @Column(name = "elo")
    private Integer elo;

    @NotNull
    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @NotNull
    @Column(name = "hour", nullable = false)
    private LocalTime hour;

    @Column(name = "duration")
    private LocalTime duration;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeamEntity getTeamOne() {
        return teamEntityOne;
    }

    public void setTeamOne(TeamEntity teamEntityOne) {
        this.teamEntityOne = teamEntityOne;
    }

    public TeamEntity getTeamTwo() {
        return teamEntityTwo;
    }

    public void setTeamTwo(TeamEntity teamEntityTwo) {
        this.teamEntityTwo = teamEntityTwo;
    }

    public ModeEntity getMode() {
        return mode;
    }

    public void setMode(ModeEntity mode) {
        this.mode = mode;
    }

    public Integer getBo() {
        return bo;
    }

    public void setBo(Integer bo) {
        this.bo = bo;
    }

    public Integer getElo() {
        return elo;
    }

    public void setElo(Integer elo) {
        this.elo = elo;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}