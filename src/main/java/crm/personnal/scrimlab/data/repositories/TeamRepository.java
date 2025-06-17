package crm.personnal.scrimlab.data.repositories;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, String> {
}
