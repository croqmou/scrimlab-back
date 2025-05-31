package crm.personnal.scrimlab.domain.bo;

import crm.personnal.scrimlab.config.domain.BaseBO;

public record PlayerBO (
        String username,
        String pwd,
        String email,
        String pp,
        boolean admin,
        int rankingPoints1s,
        int rankingPoints2s,
        int rankingPoints3s
) implements BaseBO {
}
