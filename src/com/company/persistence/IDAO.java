package com.company.persistence;

import java.util.List;

public interface IDAO<T> {
    public T crear(T t);
    public void eliminar(int id);
    public T buscarPorId(int id);
    public List<T> buscarTodos();
}
