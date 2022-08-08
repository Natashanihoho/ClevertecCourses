package ru.clevertec.gordievich.domain.products;


import lombok.NoArgsConstructor;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.connection.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();

    private static final String SQL_CREATE_PRODUCT = """
            INSERT INTO product (description, price, available_number, is_special_offer)  
            VALUES (?, ?, ?, ?)            
            """;

    private static final String SQL_FIND_BY_ID = """
            SELECT id, description, price, available_number, is_special_offer 
            FROM product
            WHERE id = ?
            """;

    private static final String SQL_FIND_ALL_BY_PAGE = """
            SELECT id, description, price, available_number, is_special_offer 
            FROM product   
            OFFSET ?  
            LIMIT 10     
            """;

    private static final String SQL_UPDATE = """
            UPDATE product 
            SET description = ?,
                price = ?,
                available_number = ?,
                is_special_offer = ?
            WHERE id = ?
            """;

    private static final String SQL_DELETE_BY_ID = """
            DELETE FROM product
            WHERE id = ?
            """;

    public Product createProduct(Product product) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementMapper(preparedStatement, product);
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                System.out.println(generatedKeys.getLong("id"));
                product.setId(generatedKeys.getLong("id"));
            }
            return product;
        } catch (SQLException e) {
            throw new DaoException("ProductDao exception: createProduct", e);
        }
    }

    public Optional<Product> findById(int id) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(resultSet.next() ? productMapper(resultSet) : null);
        } catch (SQLException e) {
            throw new DaoException("ProductDao exception: findById", e);
        }
    }

    public List<Product> findAllByPage(int page) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_FIND_ALL_BY_PAGE)) {
            List<Product> products = new ArrayList<>();
            preparedStatement.setInt(1, 10 * (page - 1));
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(productMapper(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException("ProductDao exception: findById", e);
        }
    }

    public boolean update(Product product) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatementMapper(preparedStatement, product);
            preparedStatement.setLong(5, product.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("ProductDao exception: update", e);
        }
    }

    public boolean deleteById(Long id) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("ProductDao exception: deleteById", e);
        }
    }

    private Product productMapper(ResultSet resultSet) {
        try {
            return Product.builder()
                    .id(resultSet.getLong("id"))
                    .description(resultSet.getString("description"))
                    .price(resultSet.getDouble("price"))
                    .availableNumber(resultSet.getInt("available_number"))
                    .isSpecialOffer(resultSet.getBoolean("is_special_offer"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Impossible to map resultSet to Product", e);
        }
    }

    private void preparedStatementMapper(PreparedStatement preparedStatement, Product product) throws SQLException {
        preparedStatement.setString(1, product.getDescription());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getAvailableNumber());
        preparedStatement.setBoolean(4, product.isSpecialOffer());
    }


    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
