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

    @Column(name = "team_banner", length = Integer.MAX_VALUE)
    private String teamBanner;

    @Size(max = 50)
    @Column(name = "team_description", length = 50)
    private String teamDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "captain")
    private PlayerEntity captain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "second_sub")
    private PlayerEntity secondSub;

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
    private Integer rankingPoints = 0;

    @ColumnDefault("0")
    @Column(name = "team_goals")
    private Integer teamGoals = 0;

    @ColumnDefault("0")
    @Column(name = "team_wins")
    private Integer teamWins = 0;

    @ColumnDefault("0")
    @Column(name = "team_loses")
    private Integer teamLoses = 0;


    public String getTeamBanner() {
        return teamBanner;
    }

    public void setTeamBanner(String teamBanner) {
        this.teamBanner = teamBanner;
    }

    public Integer getTeamLoses() {
        return teamLoses;
    }

    public void setTeamLoses(Integer teamLoses) {
        this.teamLoses = teamLoses;
    }

    public Integer getTeamWins() {
        return teamWins;
    }

    public void setTeamWins(Integer teamWins) {
        this.teamWins = teamWins;
    }

    public Integer getTeamGoals() {
        return teamGoals;
    }

    public void setTeamGoals(Integer teamGoals) {
        this.teamGoals = teamGoals;
    }

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

    public PlayerEntity getCaptain() {
        return captain;
    }

    public void setCaptain(PlayerEntity captain) {
        this.captain = captain;
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

    public PlayerEntity getPlayerEntityTwo() {
        return playerEntityTwo;
    }

    public void setPlayerEntityTwo(PlayerEntity playerEntityTwo) {
        this.playerEntityTwo = playerEntityTwo;
    }

    public PlayerEntity getPlayerEntityThree() {
        return playerEntityThree;
    }

    public void setPlayerEntityThree(PlayerEntity playerEntityThree) {
        this.playerEntityThree = playerEntityThree;
    }

    public PlayerEntity getSecondSub() {
        return secondSub;
    }

    public void setSecondSub(PlayerEntity secondSub) {
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