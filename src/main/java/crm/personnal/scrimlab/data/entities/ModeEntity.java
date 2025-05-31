package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mode")
public class ModeEntity {
    @Id
    @Column(name = "mode_name", nullable = false, length = Integer.MAX_VALUE)
    private String modeName;

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    //TODO [Reverse Engineering] generate columns from DB
}