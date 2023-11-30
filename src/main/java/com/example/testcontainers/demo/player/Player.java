package com.example.testcontainers.demo.player;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "players")
public class Player {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="playerid", nullable = false, unique = true)
        private String playerId;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String runs;

        @Column(nullable = false)
        private String wickets;

        @Column(nullable = false)
        private String catches;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getRuns() {
                return runs;
        }

        public void setRuns(String runs) {
                this.runs = runs;
        }

        public String getWickets() {
                return wickets;
        }

        public void setWickets(String wickets) {
                this.wickets = wickets;
        }

        public String getCatches() {
                return catches;
        }

        public void setCatches(String catches) {
                this.catches = catches;
        }

        public String getPlayerId() {
                return playerId;
        }

        public void setPlayerId(String playerId) {
                this.playerId = playerId;
        }

}
