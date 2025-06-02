package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayertitleEntityId implements Serializable {
    private static final long serialVersionUID = 2189096570439358136L;
    @NotNull
    @Column(name = "playerentity", nullable = false, length = Integer.MAX_VALUE)
    private String playerentity;

    @NotNull
    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;

    public String getPlayerentity() {
        return playerentity;
    }

    public void setPlayerentity(String playerentity) {
        this.playerentity = playerentity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlayertitleEntityId entity = (PlayertitleEntityId) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.playerentity, entity.playerentity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, playerentity);
    }

}