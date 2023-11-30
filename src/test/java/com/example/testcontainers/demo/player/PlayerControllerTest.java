package com.example.testcontainers.demo.player;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class PlayerControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * The Test containers JUnit 5 Extension will take care of starting the container before tests and stopping it after tests.
     * If the container is a static field then it will be started once before all the tests and stopped after all the tests.
     * If it is a non-static field then the container will be started before each test and stopped after each test.
     */
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @Test
    public void testPlayerEndpoints() {

        // Create a new player
        Player player = new Player();
        player.setName("Rohit");
        player.setPlayerId("45");
        player.setCatches("10");
        player.setRuns("550");
        player.setWickets("1");

        ResponseEntity<Player> createResponse =
                restTemplate.postForEntity("/players", player, Player.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        Player savedPlayer = createResponse.getBody();

        assert savedPlayer != null;

        // Retrieve
        ResponseEntity<Player> getResponse =
                restTemplate.getForEntity("/players/" + savedPlayer.getId(), Player.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        Player playerFromGet = getResponse.getBody();

        assert playerFromGet != null;

        assertEquals("Rohit", playerFromGet.getName());
        assertEquals("45", playerFromGet.getPlayerId());
        assertEquals("10", playerFromGet.getCatches());
        assertEquals("550", playerFromGet.getRuns());
        assertEquals("1", playerFromGet.getWickets());

        // Retrieve All
        ResponseEntity<Player[]> getAllResponse =
                restTemplate.getForEntity("/players", Player[].class);
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());

        Player[] playerFromGetAll = getAllResponse.getBody();
        assert playerFromGetAll != null;

        assertEquals(1, playerFromGetAll.length);
    }

}