package crm.personnal.scrimlab.domain.bo;

import crm.personnal.scrimlab.config.domain.BaseBO;

public class PlayerBO implements BaseBO {
    private String username;
    private String pwd;
    private String email;
    private String pp;
    private boolean admin;
    private int playerGoals;
    private int playerWins;
    private int playerLoses;
    private int rankingPoints1s;
    private int rankingPoints2s;
    private int rankingPoints3s;

    public PlayerBO(String username, String pwd, String email, String pp, boolean admin, int playerGoals, int playerWins, int playerLoses, int rankingPoints1s, int rankingPoints2s, int rankingPoints3s) {
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.pp = pp;
        this.admin = admin;
        this.playerGoals = playerGoals;
        this.playerWins = playerWins;
        this.playerLoses = playerLoses;
        this.rankingPoints1s = rankingPoints1s;
        this.rankingPoints2s = rankingPoints2s;
        this.rankingPoints3s = rankingPoints3s;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public String getPp() {
        return pp;
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getRankingPoints1s() {
        return rankingPoints1s;
    }

    public int getRankingPoints2s() {
        return rankingPoints2s;
    }

    public int getRankingPoints3s() {
        return rankingPoints3s;
    }

    public int getPlayerGoals() {
        return playerGoals;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getPlayerLoses() {
        return playerLoses;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
