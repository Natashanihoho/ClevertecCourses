package ru.clevertec.gordievich.domain.cards;

import lombok.NoArgsConstructor;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.connection.ConnectionManager;

import java.sql.SQLException;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DiscountCardDao {

    private static final DiscountCardDao INSTANCE = new DiscountCardDao();
    private static final DiscountCardMapper mapper = new DiscountCardMapper();
    private static final String SQL_FIND_BY_NAME = """
            SELECT name, discount_percent
            FROM discount_card
            WHERE name = ?
            """;

    public Optional<DiscountCard> findByName(String name) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            var resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(resultSet.next() ? mapper.apply(resultSet) : null);
        } catch (SQLException e) {
            throw new DaoException("DiscountCardDao exception", e);
        }
    }

    public static DiscountCardDao getInstance() {
        return INSTANCE;
    }
}
