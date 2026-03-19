package com.micro_service.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryApplicationTests {

	@LocalServerPort
	private Integer port;

	@ServiceConnection
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

	@BeforeEach
	void setUp() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port = port;
	}

	@Test
	void contextLoads() {

		RestAssured.given()
            .queryParam("skuCode", "MACBOOK-AIR-M2-13")
            .queryParam("quantity", 2)
            .when()
            .post("/api/inventory/stock")
            .then()
            .statusCode(200)
            .body(org.hamcrest.Matchers.equalTo("Product Available"));

	}

}
