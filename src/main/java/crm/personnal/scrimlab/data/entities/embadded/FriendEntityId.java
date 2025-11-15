package crm.personnal.scrimlab.data.entities.embadded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendEntityId implements Serializable {
    private static final long serialVersionUID = -2016080749368922723L;
    @NotNull
    @Column(name = "playerentity", nullable = false, length = Integer.MAX_VALUE)
    private String playerentity;

    @NotNull
    @Column(name = "friend", nullable = false, length = Integer.MAX_VALUE)
    private String friend;

    public String getPlayerentity() {
        return playerentity;
    }

    public void setPlayerentity(String playerentity) {
        this.playerentity = playerentity;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FriendEntityId entity = (FriendEntityId) o;
        return Objects.equals(this.friend, entity.friend) &&
                Objects.equals(this.playerentity, entity.playerentity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friend, playerentity);
    }

}