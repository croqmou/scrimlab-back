package crm.personnal.scrimlab.config.domain;

public interface InputMapper<A, B extends BaseBO> {

    B mapToBO(A entity);
    A mapFromBO(B entity);
}
