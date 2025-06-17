package crm.personnal.scrimlab.config.domain;

public interface OutputMapper<A, B extends BaseBO> {

    A mapFromBO(B bo);
}
