package crm.personnal.scrimlab.domain.bo;

import crm.personnal.scrimlab.config.domain.BaseBO;

public class TeamBO implements BaseBO {
    private String teamName;
    private String teamLogo;
    private String teamDescription;
    private PlayerBO captain;
    private PlayerBO playerEntityTwo;
    private PlayerBO playerEntityThree;
    private PlayerBO sub;
    private PlayerBO secondSub;
    private PlayerBO coach;
    private PlayerBO manager;
    private Integer rankingPoints;

    public TeamBO(String teamName, String teamLogo, String teamDescription, PlayerBO captain, PlayerBO playerEntityTwo, PlayerBO playerEntityThree, PlayerBO sub, PlayerBO secondSub, PlayerBO coach, PlayerBO manager, Integer rankingPoints) {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.teamDescription = teamDescription;
        this.captain = captain;
        this.playerEntityTwo = playerEntityTwo;
        this.playerEntityThree = playerEntityThree;
        this.sub = sub;
        this.secondSub = secondSub;
        this.coach = coach;
        this.manager = manager;
        this.rankingPoints = rankingPoints;
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

    public void setCaptain(PlayerBO captain) {
        this.captain = captain;
    }
}
