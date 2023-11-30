package com.example.testcontainers.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @PostMapping
    public Player create(@RequestBody Player player) {
        System.out.println("create player");
        return playerRepository.save(player);
    }

    @GetMapping("/{id}")
    public Player getById(@PathVariable Long id) {
        return playerRepository.findById(id).orElse(null);
    }
}

//curl -X POST http://localhost:8080/players -H "Content-Type: application/json" -d '{"playerId":"18","name":"virat","runs":"711","wickets":"1","catches":"8"}'