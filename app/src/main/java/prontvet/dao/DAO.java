package prontvet.dao;

import java.util.List;

public interface DAO<T> {
    /**
     * Salva um objeto no banco de dados.
     * 
     * @param t
     */
    T save(T t);

    /**
     * Busca por todos os objetos no banco de dados.
     * 
     * @param t
     */
    List<T> findAll();
}
