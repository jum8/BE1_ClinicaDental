package com.company.dao;

public interface IDAO<T> {
    public T guardar(T t);
    public void eliminar(Long id);
    public T buscar(Long id);
}
