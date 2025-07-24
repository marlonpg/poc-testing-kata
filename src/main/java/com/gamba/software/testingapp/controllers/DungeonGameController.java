package com.gamba.software.testingapp.controllers;

import com.gamba.software.testingapp.services.DungeonGameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DungeonGameController {

    private DungeonGameService dungeonGameService;

    public DungeonGameController(DungeonGameService dungeonGameService) {
        this.dungeonGameService = dungeonGameService;
    }

    @PostMapping("/calculate")
    public Integer calculateMinimumHp(@RequestBody int[][] dungeon) {
        return dungeonGameService.calculateMinimumHP(dungeon);
    }
}
