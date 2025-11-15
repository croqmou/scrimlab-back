package crm.personnal.scrimlab.data.entities;

import crm.personnal.scrimlab.data.entities.embadded.FriendEntityId;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "friend")
public class FriendEntity {
    @EmbeddedId
    private FriendEntityId id;

    @MapsId("playerentity")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "playerentity", nullable = false)
    private PlayerEntity playerentity;

    public FriendEntityId getId() {
        return id;
    }

    public void setId(FriendEntityId id) {
        this.id = id;
    }

    public PlayerEntity getPlayerentity() {
        return playerentity;
    }

    public void setPlayerentity(PlayerEntity playerentity) {
        this.playerentity = playerentity;
    }

}