package com.accenture.multibank.dao;

/**
 * @author manuel
 * @version 11/23/16
 */
public interface AbstractDAO<P, O> {
    O save(O o);
    O find(P p);
    void update(O o);
    O delete(P p);
}
