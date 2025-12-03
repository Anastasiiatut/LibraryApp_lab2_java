package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericRepository<T> {
    private final List<T> items = new ArrayList<>();
    private final IdentityExtractor<T> identityExtractor;
    private static final Logger LOGGER = Logger.getLogger(GenericRepository.class.getName());

    public GenericRepository(IdentityExtractor<T> identityExtractor) {
        this.identityExtractor = identityExtractor;
        LOGGER.info("Created repository");
    }

    public void add(T item) {
        String identity = identityExtractor.extractIdentity(item);
        if (findByIdentity(identity).isPresent()) {
            throw new IllegalArgumentException("Елемент з ідентифікатором '" + identity +
                    "' вже існує у репозиторії.");
        }
        items.add(item);
        LOGGER.info("Add object to repository");
    }

    public boolean deleteByIdentity(String identity) {
        LOGGER.info("Deleting object from repository");
        return items.removeIf(item -> identityExtractor.extractIdentity(item).equals(identity));

    }

    public Optional<T> findByIdentity(String identity) {
        return items.stream()
                .filter(item -> identityExtractor.extractIdentity(item).equals(identity))
                .findFirst();
    }

    public List<T> findAll() {
        return List.copyOf(items);
    }

    public int size() {
        return items.size();
    }
}