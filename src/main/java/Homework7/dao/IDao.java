package Homework7.dao;

public interface IDao<T> {

    void insert(T t);

    void update(T t);

    void deleteById(Integer id);

    T findById(Integer id);
}
