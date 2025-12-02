public record Reader(
        String firstName,
        String lastName,
        int readerId
) {
    public Reader {
        if (firstName == null || firstName.length() < 3) {
            throw new IllegalArgumentException("First name must be non-null and have at least 3 characters");
        }
        if (lastName == null || lastName.length() < 3) {
            throw new IllegalArgumentException("Last name must be non-null and have at least 3 characters");
        }
        if (readerId <= 0) {
            throw new IllegalArgumentException("Reader ID must be positive.");
        }
    }
    @Override
    public String toString() {
        return "Reader @" + readerId + " " + firstName + " " + lastName;
    }

    public static Reader newReaderWithoutId(String firstName, String lastName) {
        throw new UnsupportedOperationException("ID must be known when creating a Reader record.");
    }
}