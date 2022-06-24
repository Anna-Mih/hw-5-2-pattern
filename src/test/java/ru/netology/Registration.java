package ru.netology;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Registration {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void setUpAll(UserInfo user) {
        Gson gson = new Gson();
        String authUser = gson.toJson(user);
        given()
                .spec(requestSpec)
                .body(authUser)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
