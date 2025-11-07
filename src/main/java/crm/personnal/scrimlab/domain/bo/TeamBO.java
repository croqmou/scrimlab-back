package crm.personnal.scrimlab.domain.bo;

import crm.personnal.scrimlab.config.domain.BaseBO;

public class TeamBO implements BaseBO {
    private String teamName;
    private String teamLogo;
    private String teamBanner;
    private String teamDescription;
    private PlayerBO captain;
    private PlayerBO playerEntityTwo;
    private PlayerBO playerEntityThree;
    private PlayerBO sub;
    private PlayerBO secondSub;
    private PlayerBO coach;
    private PlayerBO manager;
    private Integer rankingPoints;
    private Integer teamGoals;
    private Integer teamWins;
    private Integer teamLoses;

    public TeamBO(String teamName, String teamLogo, String teamBanner, String teamDescription, PlayerBO captain, PlayerBO playerEntityTwo, PlayerBO playerEntityThree, PlayerBO sub, PlayerBO secondSub, PlayerBO coach, PlayerBO manager, Integer rankingPoints, Integer teamGoals, Integer teamWins, Integer teamLoses) {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.teamBanner = teamBanner;
        this.teamDescription = teamDescription;
        this.captain = captain;
        this.playerEntityTwo = playerEntityTwo;
        this.playerEntityThree = playerEntityThree;
        this.sub = sub;
        this.secondSub = secondSub;
        this.coach = coach;
        this.manager = manager;
        this.rankingPoints = rankingPoints;
        this.teamGoals = teamGoals;
        this.teamWins = teamWins;
        this.teamLoses = teamLoses;
    }

    public String getTeamBanner() {
        return teamBanner;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public PlayerBO getCaptain() {
        return captain;
    }

    public PlayerBO getPlayerEntityTwo() {
        return playerEntityTwo;
    }

    public PlayerBO getPlayerEntityThree() {
        return playerEntityThree;
    }

    public PlayerBO getSub() {
        return sub;
    }

    public PlayerBO getSecondSub() {
        return secondSub;
    }

    public PlayerBO getCoach() {
        return coach;
    }

    public PlayerBO getManager() {
        return manager;
    }

    public Integer getRankingPoints() {
        return rankingPoints;
    }

    public Integer getTeamGoals() {
        return teamGoals;
    }

    public Integer getTeamWins() {
        return teamWins;
    }

    public Integer getTeamLoses() {
        return teamLoses;
    }

    public void setCaptain(PlayerBO captain) {
        this.captain = captain;
    }
}
