package cacttus.education.cacttusproductcrud.repositories;

import cacttus.education.cacttusproductcrud.infrastructure.CrudRepository;
import cacttus.education.cacttusproductcrud.infrastructure.DbHelper;
import cacttus.education.cacttusproductcrud.infrastructure.ToObjectable;
import cacttus.education.cacttusproductcrud.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String query = "UPDATE Production.Products SET productname=?," +
                "supplierid=?, categoryid=?, unitprice=?, discontinued=? " +
                "WHERE productid=?";
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, item.getProductName());
            statement.setInt(2, item.getSupplierId());
            statement.setInt(3, item.getCategoryId());
            statement.setFloat(4, item.getUnitPrice());
            statement.setBoolean(5, item.isDiscontinued());
            statement.setInt(6, item.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Product item) {
        removeById(item.getProductId());
//        String query = "DELETE FROM Production.Products WHERE productid=?";
//        try (
//                Connection connection = DbHelper.getConnection();
//                PreparedStatement statement = connection.prepareStatement(query)
//        ) {
//            statement.setInt(1, item.getProductId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void removeById(Integer key) {
        String query = "DELETE FROM Production.Products WHERE productid=?";
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, key);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM Production.Products";
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            List<Product> results = null;
            ResultSet resultSet = statement.executeQuery();
            results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(convert(resultSet));
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(Integer key) {
        String query = "SELECT * FROM Production.Products WHERE productid=?";
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, key);
            Product results = null;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                results = convert(resultSet);
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product convert(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("productid"));
        if (resultSet.getObject("productname") != null) {
            product.setProductName(resultSet.getString("productname"));
        }
        product.setSupplierId(resultSet.getInt("supplierid"));
        product.setCategoryId(resultSet.getInt("categoryid"));
        product.setUnitPrice(resultSet.getFloat("unitprice"));
        product.setDiscontinued(resultSet.getBoolean("discontinued"));
        return product;
    }
}






