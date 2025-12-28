package com.IAbouzaid.Task.Management.model;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
