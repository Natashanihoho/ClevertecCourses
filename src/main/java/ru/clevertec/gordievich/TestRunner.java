package ru.clevertec.gordievich;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.clevertec.gordievich.domain.cards.DiscountCard;
import ru.clevertec.gordievich.domain.products.Product;

public class TestRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

//            DiscountCard discountCard = DiscountCard.builder()
//                    .cardName("CARD1234")
//                    .discountPercent(5)
//                    .build();
//
//            session.save(discountCard);

            Product product = Product.builder()
                    .description("Cherry")
                    .price(2.37)
                    .availableNumber(25)
                    .isSpecialOffer(false)
                    .build();

            session.save(product);

            session.getTransaction().commit();

        }
    }
}
