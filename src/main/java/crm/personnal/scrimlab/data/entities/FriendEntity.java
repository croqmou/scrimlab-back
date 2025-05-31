package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "friend")
public class FriendEntity {
    @EmbeddedId
    private FriendEntityId id;

    @MapsId("player")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "player", nullable = false)
    private PlayerEntity playerEntity;

    public FriendEntityId getId() {
        return id;
    }

    public void setId(FriendEntityId id) {
        this.id = id;
    }

    public PlayerEntity getPlayer() {
        return playerEntity;
    }

    public void setPlayer(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

}