package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "playertitle")
public class PlayertitleEntity {
    @EmbeddedId
    private PlayertitleEntityId id;

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

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }

}