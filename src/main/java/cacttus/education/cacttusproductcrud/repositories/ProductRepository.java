package cacttus.education.cacttusproductcrud.repositories;

import cacttus.education.cacttusproductcrud.infrastructure.CrudRepository;
import cacttus.education.cacttusproductcrud.infrastructure.DbHelper;
import cacttus.education.cacttusproductcrud.infrastructure.ToObjectable;
import cacttus.education.cacttusproductcrud.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductRepository implements CrudRepository<Product, Integer>, ToObjectable<Product> {
    @Override
    public void add(Product item) {
        String query = "INSERT INTO Production.Products " +
                "( productname,supplierid,categoryid,unitprice,discontinued)\n" +
                "VALUES (?,?,?,?,?)";

        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, item.getProductName());
            statement.setInt(2, item.getSupplierId());
            statement.setInt(3, item.getCategoryId());
            statement.setFloat(4, item.getUnitPrice());
            statement.setBoolean(5, item.isDiscontinued());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modify(Product item) {

    }

    @Override
    public void remove(Product item) {

    }

    @Override
    public void removeById(Integer key) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Integer key) {
        return null;
    }

    @Override
    public Product convert(ResultSet resultSet) {
        return null;
    }
}
