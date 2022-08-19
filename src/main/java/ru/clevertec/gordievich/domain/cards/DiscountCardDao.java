package ru.clevertec.gordievich.domain.cards;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.connection.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Repository
@NoArgsConstructor(access = PRIVATE)
public class DiscountCardDao {

    private static final DiscountCardDao INSTANCE = new DiscountCardDao();

    private static final String SQL_CREATE_DISCOUNT_CARD = """
            INSERT INTO discount_card (card_name, discount_percent)
            VALUES (?, ?)            
            """;

    private static final String SQL_FIND_BY_NAME = """
            SELECT card_name, discount_percent
            FROM discount_card
            WHERE card_name = ?
            """;

    private static final String SQL_UPDATE = """
            UPDATE discount_card 
            SET discount_percent = ?
            WHERE card_name = ?
            """;

    private static final String SQL_DELETE_BY_ID = """
            DELETE FROM discount_card
            WHERE card_name = ?
            """;

    public DiscountCard createDiscountCard(DiscountCard discountCard) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_CREATE_DISCOUNT_CARD)) {
            preparedStatement.setString(1, discountCard.getCardName());
            preparedStatement.setInt(2, discountCard.getDiscountPercent());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                discountCard.setId(generatedKeys.getLong("id"));
            }
            return discountCard;
        } catch (SQLException e) {
            throw new DaoException("DiscountCardDao exception: createDiscountCard", e);
        }
    }

    public Optional<DiscountCard> findByName(String name) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            var resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(resultSet.next() ? discountCardMapper(resultSet) : null);
        } catch (SQLException e) {
            throw new DaoException("DiscountCardDao exception: findByName", e);
        }
    }

    public boolean update(DiscountCard discountCard) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, discountCard.getDiscountPercent());
            preparedStatement.setString(2, discountCard.getCardName());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("DiscountCardDao exception: update", e);
        }
    }

    public boolean deleteByName(String name) throws DaoException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("DiscountCardDao exception: deleteById", e);
        }
    }

    private DiscountCard discountCardMapper(ResultSet resultSet) {
        try {
            return DiscountCard.builder()
                    .cardName(resultSet.getString("card_name"))
                    .discountPercent(resultSet.getInt("discount_percent"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Impossible to map resultSet to DiscountCard", e);
        }
    }


    public static DiscountCardDao getInstance() {
        return INSTANCE;
    }
}
