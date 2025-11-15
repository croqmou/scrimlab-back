package crm.personnal.scrimlab.data.entities;

import crm.personnal.scrimlab.data.entities.embadded.PrizelistId;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "prizelist")
public class PrizeListEntity {
    @EmbeddedId
    private PrizelistId id;

    @MapsId("player")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "player", nullable = false)
    private PlayerEntity player;

    @Column(name = "result")
    private Integer result;

    @Column(name = "tournament_date")
    private LocalDate tournamentDate;

    public PrizelistId getId() {
        return id;
    }

    public void setId(PrizelistId id) {
        this.id = id;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public LocalDate getTournamentDate() {
        return tournamentDate;
    }

    public void setTournamentDate(LocalDate tournamentDate) {
        this.tournamentDate = tournamentDate;
    }

}