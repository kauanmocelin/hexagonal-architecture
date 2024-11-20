package dev.kauanmocelin.bank.infrastructure.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SendMoneyTest {

    @LocalServerPort
    private Integer port;

    @Test
    @Sql("CreateTwoAccounts.sql")
    void ShouldSendMoneyFromOneAccountToAnother() {
        given()
            .port(port)
            .pathParam("sourceAccountId", 559985L)
            .pathParam("targetAccountId", 445985L)
            .pathParam("amount", 100.0)
        .when()
            .post("/account/send/{sourceAccountId}/{targetAccountId}/{amount}")
        .then()
            .statusCode(200)
            .body(is("true"));
    }
}
