package crm.personnal.scrimlab.config.domain;

public interface OutputMapper<A, B extends BaseBO> {
    B mapToBO(A entity);
    A mapFromBO(B entity);
}
