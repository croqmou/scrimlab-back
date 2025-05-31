package crm.personnal.scrimlab.config.domain;

public interface Mapper<A, B extends BaseBO> {

    B mapToBO(A entity);
    A mapFromBO(B bo);
}
