package crm.personnal.scrimlab.data.entities;

import crm.personnal.scrimlab.data.entities.embadded.PlayertitleEntityId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "playertitle")
public class PlayertitleEntity {
    @EmbeddedId
    private PlayertitleEntityId id;

    @MapsId("playerentity")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "playerentity", nullable = false)
    private PlayerEntity playerentity;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "use", nullable = false)
    private Boolean use = false;

    public PlayertitleEntityId getId() {
        return id;
    }

    public void setId(PlayertitleEntityId id) {
        this.id = id;
    }

    public PlayerEntity getPlayerentity() {
        return playerentity;
    }

    public void setPlayerentity(PlayerEntity playerentity) {
        this.playerentity = playerentity;
    }

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }

}