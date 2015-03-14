package hu.rbr.sfinapp.core.service;

import hu.rbr.sfinapp.core.db.BaseDao;

import java.util.List;

public abstract class BaseService<E> {

    public List<E> getAll() {
        return getDao().getAll();
    }

    public E get(int id) {
        return getDao().get(id);
    }

    public E create(E acc) {
        return getDao().create(acc);
    }

    public E update(int id, E entity) {
        return getDao().update(id, entity);
    }

    public void delete(int id) {
        getDao().delete(id);
    }


    protected abstract BaseDao<E> getDao();
}
