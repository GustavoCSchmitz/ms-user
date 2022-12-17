package br.com.user.exceptions.errors;

public record ErrorDetails(
        int statusCode,
        String timestamp,
        String message
) {
}
