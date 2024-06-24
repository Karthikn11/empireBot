package com.draftBot;

import java.util.HashMap;
import java.util.Map;

public class Tracker {
    private final Map<String, Integer> userCommandUsage = new HashMap<>();

    public boolean canUseCommand(String userId) {
        return userCommandUsage.getOrDefault(userId, 0) < 3;
    }

    public void incrementUsage(String userId) {
        userCommandUsage.put(userId, userCommandUsage.getOrDefault(userId, 0) + 1);
    }

    public int getUsageCount(String userId) {
        return userCommandUsage.getOrDefault(userId, 0);
    }
}

