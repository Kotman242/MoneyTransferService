package ru.netology.moneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MoneyTransferServiceContainersTest {
    final GenericContainer<?> app = new GenericContainer<>("app:latest")
            .withExposedPorts(5500);

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        app.start();
    }

    @Test
    void testContainer200() {
        ResponseEntity<?> entity = restTemplate.postForEntity("http://localhost:5500/transfer", new RequestObjact("5555555555555555"), String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testContainer400() {
        ResponseEntity<?> entity = restTemplate.postForEntity("http://localhost:5500/transfer", new RequestObjact("5554555555555355"), String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
