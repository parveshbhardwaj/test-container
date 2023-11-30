package com.example.testcontainers.demo.player;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
// do not replace the testcontainer data source
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @Test
    public void testPlayerSaveAndFindById() {

        // Create a new player
        Player player = new Player();
        player.setName("Rohit");
        player.setPlayerId("45");
        player.setCatches("10");
        player.setRuns("550");
        player.setWickets("1");

        // save player
        playerRepository.save(player);

        // find player
        Optional<Player> result = playerRepository.findById(player.getId());
        assertTrue(result.isPresent());

        Player playerFromGet = result.get();

        assertEquals("Rohit", playerFromGet.getName());
        assertEquals("45", playerFromGet.getPlayerId());
        assertEquals("10", playerFromGet.getCatches());
        assertEquals("550", playerFromGet.getRuns());
        assertEquals("1", playerFromGet.getWickets());

    }

    @Test
    public void testPlayerCRUD() {

        Player player = new Player();
        player.setName("Virat");
        player.setPlayerId("18");
        player.setCatches("8");
        player.setRuns("711");
        player.setWickets("1");

        // save player
        playerRepository.save(player);

        // find player by id
        Optional<Player> result = playerRepository.findByPlayerId(player.getPlayerId());
        assertTrue(result.isPresent());

        Player playerFromGet = result.get();

        Long id = playerFromGet.getId();

        assertEquals("Virat", playerFromGet.getName());
        assertEquals("18", playerFromGet.getPlayerId());
        assertEquals("8", playerFromGet.getCatches());
        assertEquals("711", playerFromGet.getRuns());
        assertEquals("1", playerFromGet.getWickets());

        // update player
        player.setRuns("800");
        playerRepository.save(player);

        // find player by id
        Optional<Player> result2 = playerRepository.findById(id);
        assertTrue(result2.isPresent());

        Player playerFromGet2 = result2.get();

        assertEquals("Virat", playerFromGet2.getName());
        assertEquals("18", playerFromGet2.getPlayerId());
        assertEquals("8", playerFromGet2.getCatches());
        assertEquals("800", playerFromGet2.getRuns());
        assertEquals("1", playerFromGet2.getWickets());

        // delete a player
        playerRepository.delete(player);

        // should be empty
        assertTrue(playerRepository.findById(id).isEmpty());

    }


}