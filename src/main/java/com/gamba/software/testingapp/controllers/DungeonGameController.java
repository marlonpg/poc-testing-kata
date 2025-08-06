package com.gamba.software.testingapp.controllers;

import com.gamba.software.testingapp.services.DungeonGameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DungeonGameController {

    private final DungeonGameService dungeonGameService;

    public DungeonGameController(DungeonGameService dungeonGameService) {
        this.dungeonGameService = dungeonGameService;
    }

    @PostMapping("/calculate")
    public Integer calculateMinimumHp(@RequestBody Map<String, Object> payload) {
        // Expecting JSON: { "dungeon": [[...]], "player": "name", "outcome": "..." }
        int[][] dungeon = ((java.util.List<java.util.List<Integer>>) payload.get("dungeon"))
                .stream().map(l -> l.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
        // Optionally extract player and outcome if you want to use them
        // String player = (String) payload.getOrDefault("player", "player1");
        // String outcome = (String) payload.getOrDefault("outcome", "COMPLETED");
        return dungeonGameService.calculateMinimumHP(dungeon);
    }
}
