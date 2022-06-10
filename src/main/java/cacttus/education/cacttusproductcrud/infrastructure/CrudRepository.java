package cacttus.education.cacttusproductcrud.infrastructure;

import java.util.List;

public interface CrudRepository<T, Tid> {
    public void add(T item);

    public void modify(T item);

    public void remove(T item);

    public void removeById(Tid key);

    public List<T> getAll();

    public T getById(Tid key);
}
