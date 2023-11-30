package com.example.testcontainers.demo.player;

import com.example.testcontainers.demo.player.Player;
import com.example.testcontainers.demo.player.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class PlayerRepositoryServiceConnectionTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Container
    // When using Test containers, connection details can be automatically created for a service running in a container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );


    @Test
    public void testEmptyList() {
        List<Player> result = playerRepository.findAll();
        assertEquals(0, result.size());
    }
}
