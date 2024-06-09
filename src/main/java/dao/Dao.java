package dao;

import entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface  Dao <F, T>{


    List<T> findAll();

    Student findById(Integer id);

    boolean delete(F id);

    void update(T entity);

    T save(T entity) throws SQLException;

}