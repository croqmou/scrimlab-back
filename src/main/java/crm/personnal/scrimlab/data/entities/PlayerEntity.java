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
    @Size(max = 20)
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @NotNull
    @Column(name = "pwd", nullable = false, length = Integer.MAX_VALUE)
    private String pwd;

    @NotNull
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "pp", length = Integer.MAX_VALUE)
    private String pp;

    @ColumnDefault("false")
    @Column(name = "admin")
    private Boolean admin;

    @ColumnDefault("0")
    @Column(name = "ranking_points_1s")
    private Integer rankingPoints1s;

    @ColumnDefault("0")
    @Column(name = "ranking_points_2s")
    private Integer rankingPoints2s;

    @ColumnDefault("0")
    @Column(name = "ranking_points_3s")
    private Integer rankingPoints3s;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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