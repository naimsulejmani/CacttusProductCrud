package cacttus.education.cacttusproductcrud.infrastructure;

import java.sql.ResultSet;

public interface ToObjectable<T> {
    public T convert(ResultSet resultSet);
}
