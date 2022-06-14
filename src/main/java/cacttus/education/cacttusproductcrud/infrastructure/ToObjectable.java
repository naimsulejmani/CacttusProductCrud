package cacttus.education.cacttusproductcrud.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ToObjectable<T> {
    public T convert(ResultSet resultSet) throws SQLException;
}
