package crm.personnal.scrimlab.data.repositories;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, String> {
    @Query("""
        SELECT t FROM TeamEntity t
        WHERE t.captain = :player
           OR t.playerEntityTwo = :player
           OR t.playerEntityThree = :player
           OR t.sub = :player
           OR t.secondSub = :player
           OR t.coach = :player
           OR t.manager = :player
    """)
    List<TeamEntity> findAllByPlayerInAnyRole(@Param("player") PlayerEntity player);
}
