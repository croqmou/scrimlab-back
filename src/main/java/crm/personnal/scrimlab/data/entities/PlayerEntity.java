package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "player")
public class PlayerEntity {
    @Id
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @NotNull
    @Column(name = "pwd", nullable = false, length = Integer.MAX_VALUE) //TODO ⚠️ à hasher dans une vraie app
    private String pwd;

    @Column(name = "pp", length = Integer.MAX_VALUE)
    private String pp;

    @ColumnDefault("false")
    @Column(name = "admin")
    private Boolean admin;

    @ColumnDefault("0")
    @Column(name = "ranking_points_1s")
    private Integer rankingPoints1s = 0;

    @ColumnDefault("0")
    @Column(name = "ranking_points_2s")
    private Integer rankingPoints2s = 0;

    @ColumnDefault("0")
    @Column(name = "ranking_points_3s")
    private Integer rankingPoints3s = 0;

    @ColumnDefault("0")
    @Column(name = "player_goals")
    private Integer playerGoals = 0;

    @ColumnDefault("0")
    @Column(name = "player_wins")
    private Integer playerWins = 0;

    @ColumnDefault("0")
    @Column(name = "player_loses")
    private Integer playerLoses = 0;

    public Integer getPlayerLoses() {
        return playerLoses;
    }

    public void setPlayerLoses(Integer playerLoses) {
        this.playerLoses = playerLoses;
    }

    public Integer getPlayerWins() {
        return playerWins;
    }

    public void setPlayerWins(Integer playerWins) {
        this.playerWins = playerWins;
    }

    public Integer getPlayerGoals() {
        return playerGoals;
    }

    public void setPlayerGoals(Integer playerGoals) {
        this.playerGoals = playerGoals;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getRankingPoints1s() {
        return rankingPoints1s;
    }

    public void setRankingPoints1s(Integer rankingPoints1s) {
        this.rankingPoints1s = rankingPoints1s;
    }

    public Integer getRankingPoints2s() {
        return rankingPoints2s;
    }

    public void setRankingPoints2s(Integer rankingPoints2s) {
        this.rankingPoints2s = rankingPoints2s;
    }

    public Integer getRankingPoints3s() {
        return rankingPoints3s;
    }

    public void setRankingPoints3s(Integer rankingPoints3s) {
        this.rankingPoints3s = rankingPoints3s;
    }

}