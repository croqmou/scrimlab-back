package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "team")
public class TeamEntity {
    @Id
    @Size(max = 26)
    @Column(name = "team_name", nullable = false, length = 26)
    private String teamName;

    @Column(name = "team_logo", length = Integer.MAX_VALUE)
    private String teamLogo;

    @Size(max = 50)
    @Column(name = "team_description", length = 50)
    private String teamDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "player_one")
    private PlayerEntity playerEntityOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "player_two")
    private PlayerEntity playerEntityTwo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "player_three")
    private PlayerEntity playerEntityThree;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "sub")
    private PlayerEntity sub;

    @Column(name = "second_sub", length = Integer.MAX_VALUE)
    private String secondSub;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "coach")
    private PlayerEntity coach;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "manager")
    private PlayerEntity manager;

    @ColumnDefault("0")
    @Column(name = "ranking_points")
    private Integer rankingPoints;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public PlayerEntity getPlayerOne() {
        return playerEntityOne;
    }

    public void setPlayerOne(PlayerEntity playerEntityOne) {
        this.playerEntityOne = playerEntityOne;
    }

    public PlayerEntity getPlayerTwo() {
        return playerEntityTwo;
    }

    public void setPlayerTwo(PlayerEntity playerEntityTwo) {
        this.playerEntityTwo = playerEntityTwo;
    }

    public PlayerEntity getPlayerThree() {
        return playerEntityThree;
    }

    public void setPlayerThree(PlayerEntity playerEntityThree) {
        this.playerEntityThree = playerEntityThree;
    }

    public PlayerEntity getSub() {
        return sub;
    }

    public void setSub(PlayerEntity sub) {
        this.sub = sub;
    }

    public String getSecondSub() {
        return secondSub;
    }

    public void setSecondSub(String secondSub) {
        this.secondSub = secondSub;
    }

    public PlayerEntity getCoach() {
        return coach;
    }

    public void setCoach(PlayerEntity coach) {
        this.coach = coach;
    }

    public PlayerEntity getManager() {
        return manager;
    }

    public void setManager(PlayerEntity manager) {
        this.manager = manager;
    }

    public Integer getRankingPoints() {
        return rankingPoints;
    }

    public void setRankingPoints(Integer rankingPoints) {
        this.rankingPoints = rankingPoints;
    }

}