package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendEntityId implements Serializable {
    private static final long serialVersionUID = 7792205705835027067L;
    @Size(max = 20)
    @NotNull
    @Column(name = "player", nullable = false, length = 20)
    private String player;

    @Size(max = 20)
    @NotNull
    @Column(name = "friend", nullable = false, length = 20)
    private String friend;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
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
                Objects.equals(this.player, entity.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friend, player);
    }

}