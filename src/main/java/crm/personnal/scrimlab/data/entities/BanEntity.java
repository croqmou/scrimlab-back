package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "ban")
public class BanEntity {
    @Id
    @Size(max = 20)
    @Column(name = "player", nullable = false, length = 20)
    private String player;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "player", nullable = false)
    private PlayerEntity playerEntity1;

    @NotNull
    @Column(name = "\"time\"", nullable = false)
    private LocalDate time;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public PlayerEntity getPlayer1() {
        return playerEntity1;
    }

    public void setPlayer1(PlayerEntity playerEntity1) {
        this.playerEntity1 = playerEntity1;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

}