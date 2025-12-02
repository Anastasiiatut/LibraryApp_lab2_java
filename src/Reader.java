public class Reader {
    private String firstName;
    private String lastName;
    private int readerId;
    public Reader(String firstName, String lastName, int readerId) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setReaderId(readerId);
    }

    public static Reader newReaderWithoutId(String firstName, String lastName) {
        return new Reader(firstName, lastName, 0);
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        if (firstName.length()<3) {
            throw new IllegalArgumentException("First name must have at least 3 characters");
        }
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        if (firstName.length()<3) {
            throw new IllegalArgumentException("Last name must have at least 3 characters");
        }
        this.lastName = lastName;
    }
    public int getReaderId() {
        return readerId;
    }
    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    @Override
    public String toString() {
        return "Reader @" + readerId + " " + firstName + " " + lastName;
    }
}
