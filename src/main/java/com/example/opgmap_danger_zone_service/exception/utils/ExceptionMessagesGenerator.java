package com.example.opgmap_danger_zone_service.exception.utils;

import java.util.UUID;

public class ExceptionMessagesGenerator {

    private ExceptionMessagesGenerator() {}

    public static String generateNotFoundMessage(String entity, UUID id) {
        return entity + " with id " + id + " not found";
    }
}
