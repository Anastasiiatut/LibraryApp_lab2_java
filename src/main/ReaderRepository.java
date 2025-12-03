package main;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.Optional;

public class ReaderRepository extends GenericRepository<Reader> {
    private static final Logger LOGGER = Logger.getLogger(ReaderRepository.class.getName());

    public ReaderRepository(IdentityExtractor<Reader> identityExtractor) {
        super(identityExtractor);
    }

    public List<Reader> findByLastName(String lastName) {
        LOGGER.info("Searching with last name: " + lastName);

        return findAll().stream()
                .filter(reader -> reader.lastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public int sumOfAllReaderIds() {
        LOGGER.info("Counting all readers");

        return findAll().stream()
                .map(Reader::readerId) // Проміжна операція: map
                .reduce(0, Integer::sum); // Термінальна операція: reduce
    }
}