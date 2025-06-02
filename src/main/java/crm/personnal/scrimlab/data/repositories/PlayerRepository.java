package crm.personnal.scrimlab.data.repositories;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, String> {
    PlayerEntity findByEmailAndPwd(String email, String pwd);
}
