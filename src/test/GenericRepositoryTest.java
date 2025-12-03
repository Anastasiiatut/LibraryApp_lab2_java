package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.*;
import java.util.List;
import java.util.Optional;

public class GenericRepositoryTest {

    // Клас для тестування (як приклад)
    private record Product(String sku, String name) {}

    private GenericRepository<Product> repository;

    @BeforeEach
    void setUp() {
        // Визначаємо IdentityExtractor: використовуємо SKU як унікальний ідентифікатор
        IdentityExtractor<Product> productIdentity = Product::sku;
        // або (prod -> prod.sku());

        repository = new GenericRepository<>(productIdentity);

        // Додаємо базові дані для тестування
        repository.add(new Product("P001", "Laptop"));
        repository.add(new Product("P002", "Monitor"));
    }

    @Test
    void add_shouldIncreaseSize() {
        // Перевірка коректності додавання
        assertEquals(2, repository.size());
        repository.add(new Product("P003", "Mouse"));
        assertEquals(3, repository.size());
    }

    @Test
    void add_shouldThrowExceptionForDuplicateIdentity() {
        // Перевірка заборони додавання дублікатів
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.add(new Product("P001", "Duplicate Laptop"));
        });
        assertTrue(thrown.getMessage().contains("P001' вже існує"));
        assertEquals(2, repository.size());
    }

    @Test
    void findByIdentity_shouldReturnObjectWhenFound() {
        // Пошук існуючого об'єкта
        Optional<Product> found = repository.findByIdentity("P002");

        assertTrue(found.isPresent());
        assertEquals("Monitor", found.get().name());
    }

    @Test
    void findByIdentity_shouldReturnEmptyOptionalWhenNotFound() {
        // Пошук неіснуючого об'єкта
        Optional<Product> found = repository.findByIdentity("P999");

        assertTrue(found.isEmpty());
    }

    @Test
    void deleteByIdentity_shouldReturnTrueAndRemoveObject() {
        // Видалення існуючого об'єкта
        boolean deleted = repository.deleteByIdentity("P001");

        assertTrue(deleted);
        assertEquals(1, repository.size());
        assertTrue(repository.findByIdentity("P001").isEmpty());
    }

    @Test
    void deleteByIdentity_shouldReturnFalseWhenNotFound() {
        // Спроба видалення неіснуючого об'єкта
        boolean deleted = repository.deleteByIdentity("P888");

        assertFalse(deleted);
        assertEquals(2, repository.size()); // Розмір не змінився
    }

    @Test
    void findAll_shouldReturnAllItems() {
        // Отримання всіх елементів
        List<Product> allItems = repository.findAll();

        assertEquals(2, allItems.size());
        assertTrue(allItems.stream().anyMatch(p -> p.sku().equals("P001")));
        assertTrue(allItems.stream().anyMatch(p -> p.sku().equals("P002")));
    }
}