package crm.personnal.scrimlab.data.repositories;

import crm.personnal.scrimlab.data.entities.PrizeListEntity;
import crm.personnal.scrimlab.data.entities.embadded.PrizelistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeListRepository extends JpaRepository<PrizeListEntity, PrizelistId> {}
