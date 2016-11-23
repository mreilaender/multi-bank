package com.accenture.multibank.dao;

/**
 * @author manuel
 * @version 11/23/16
 */
public interface AbstractDAO<P, O> {
    void save(O o);
    void find(P p);
    void update(O o);
    void delete(P p);
}
