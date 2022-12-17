package br.com.user.exceptions.errors;

public record Violation(
        String fieldName,
        String message
) {
}
