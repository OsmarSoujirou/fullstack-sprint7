package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/schema.sql")
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private static boolean initialized = false;

    @BeforeEach
    public void setUp() {
        if (!initialized) {
            entityManager.persist(new Product(7L,
                    "Jaqueta Puffer",
                    "A Jaqueta Puffer Juvenil...",
                    "jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku",
                    "Nintendo",
                    new BigDecimal("199.90"),
                    null,
                    Color.BLUE,
                    147));
            entityManager.persist(new Product(1L,
                    "Regata Infantil",
                    "A Regata Infantil Mario Bros...",
                    "regata-infantil-mario-bros-branco-14040174_sku",
                    "Nintendo",
                    new BigDecimal("29.90"),
                    null,
                    Color.WHITE,
                    98));
            entityManager.persist(new Product(9L,
                    "Camisa Infantil",
                    "A Camisa Infantil Mario Bros...",
                    "regata-infantil-mario-bros-branco-14040174_sku",
                    "Nintendo",
                    new BigDecimal("39.90"),
                    null,
                    Color.WHITE,
                    98));
        }
    }

    @Test
    void shouldListAllProductsOrderedByName() {

        List<Product> products = productRepository.findAllByOrderByName();

        assertEquals(3, products.size());

        Product firstProduct = products.get(0);
        assertEquals(9L, firstProduct.getCode());
        assertEquals("Camisa Infantil", firstProduct.getName());

        Product secondProduct = products.get(1);
        assertEquals(7L, secondProduct.getCode());
        assertEquals("Jaqueta Puffer", secondProduct.getName());

        Product thirdProduct = products.get(2);
        assertEquals(1L, thirdProduct.getCode());
        assertEquals("Regata Infantil", thirdProduct.getName());
    }


    @Test
    void shouldReportAllProductsByColor() {

        List<ProductByColorDto> reportProductsByColor = productRepository.productsByColor();

        assertEquals(2, reportProductsByColor.size());

        ProductByColorDto blueProducts = reportProductsByColor.stream()
                .filter(r -> r.getColor().equals(Color.BLUE.getDescription()))
                .findFirst()
                .orElse(null);

        assertEquals(1, blueProducts.getAmount());
        assertEquals(Color.BLUE.getDescription(), blueProducts.getColor());


        ProductByColorDto whiteProducts = reportProductsByColor.stream()
                .filter(r -> r.getColor().equals(Color.WHITE.getDescription()))
                .findFirst()
                .orElse(null);

        assertEquals(2, whiteProducts.getAmount());
        assertEquals(Color.WHITE.getDescription(), whiteProducts.getColor());


        ProductByColorDto redProducts = reportProductsByColor.stream()
                .filter(r -> r.getColor().equals(Color.RED.getDescription()))
                .findFirst()
                .orElse(null);

        Assert.assertThrows(NullPointerException.class, () -> redProducts.getAmount());

    }

}