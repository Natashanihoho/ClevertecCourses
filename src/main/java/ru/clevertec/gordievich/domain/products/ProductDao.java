package ru.clevertec.gordievich.domain.products;


import lombok.NoArgsConstructor;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.connection.ConnectionManager;

import java.sql.SQLException;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();
    private static final ProductMapper mapper = new ProductMapper();

    private static final String SQL_FIND_BY_ID = """
            SELECT id, description, price, available_number, is_special_offer 
            FROM product
            WHERE id = ?
            """;

    private static final String SQL_UPDATE_AVAILABLE_NUMBER = """
            UPDATE product 
            SET available_number = ?
            WHERE id = ?
            """;

    public Optional<Product> findById(int id) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(resultSet.next() ? mapper.apply(resultSet) : null);
        } catch (SQLException e) {
            throw new DaoException("ProductDao exception: findById", e);
        }
    }

    public boolean updateAvailableNumber(Product product) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_UPDATE_AVAILABLE_NUMBER)) {
            preparedStatement.setInt(1, product.getAvailableNumber());
            preparedStatement.setInt(2, product.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("ProductDao exception: updateAvailableNumber", e);
        }
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
