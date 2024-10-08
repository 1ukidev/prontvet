package prontvet.database;

public interface Repository<T> {
    /**
     * Salva uma entidade no banco de dados.
     * 
     * @param entity Entidade a ser salva.
     * @return Entidade salva.
     */
    public T save(T entity);
}
