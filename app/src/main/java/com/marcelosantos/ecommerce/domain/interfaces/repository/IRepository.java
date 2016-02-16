package com.marcelosantos.ecommerce.domain.interfaces.repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Marcelo on 13/02/2016.
 */
public interface IRepository<T, ID> {

    void createOrUpdate(T object) throws SQLException;
    void createIfNotExists(T object) throws SQLException;
    void create(T object) throws SQLException;
    void update(T object) throws SQLException;
    void delete(T object) throws SQLException;
    T queryForId(ID id) throws SQLException;
    List<T> queryForAll() throws SQLException;
    void refresh(T object) throws SQLException;
}
