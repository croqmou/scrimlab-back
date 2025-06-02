package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "ban")
public class BanEntity {
    @Id
    @Column(name = "playerentity", nullable = false, length = Integer.MAX_VALUE)
    private String playerentity;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "playerentity", nullable = false)
    private PlayerEntity playerEntity;

    @NotNull
    @Column(name = "\"time\"", nullable = false)
    private LocalDate time;

    public String getPlayerentity() {
        return playerentity;
    }

    public void setPlayerentity(String playerentity) {
        this.playerentity = playerentity;
    }

    public PlayerEntity getPlayer() {
        return playerEntity;
    }

    public void setPlayer(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

}