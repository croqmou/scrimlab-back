package crm.personnal.scrimlab.data.entities.embadded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrizelistId implements Serializable {
    private static final long serialVersionUID = 6596519630685314219L;
    @NotNull
    @Column(name = "player", nullable = false, length = Integer.MAX_VALUE)
    private String player;

    @NotNull
    @Column(name = "tournament_name", nullable = false, length = Integer.MAX_VALUE)
    private String tournamentName;

    public PrizelistId(String player, String tournamentName) {
        this.player = player;
        this.tournamentName = tournamentName;
    }

    public PrizelistId() {}

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrizelistId entity = (PrizelistId) o;
        return Objects.equals(this.tournamentName, entity.tournamentName) &&
                Objects.equals(this.player, entity.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentName, player);
    }

}