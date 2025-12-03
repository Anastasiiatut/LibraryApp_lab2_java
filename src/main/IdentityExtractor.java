package main;

@FunctionalInterface
public interface IdentityExtractor<T> {
    String extractIdentity(T item);
}
