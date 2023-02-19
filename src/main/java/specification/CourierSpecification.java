package specification;

import domain.courier.CourierCreateDto;
import domain.courier.CourierLoginDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierSpecification extends BasicSpecification {
    private final static String CREATE_COURIER_PATH = "/api/v1/courier";
    private final static String LOGIN_COURIER_PATH = "/api/v1/courier/login";
    private final static String DELETE_COURIER_PATH = "/api/v1/courier/{id}";

    @Step("Создать курьера")
    public Response createCourier(CourierCreateDto courier) {
        return given()
                .spec(baseSpec())
                .and()
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH);
    }

    @Step("Авторизоваться в системе")
    public Response loginCourier(CourierLoginDto courier) {
        return given()
                .spec(baseSpec())
                .and()
                .body(courier)
                .when()
                .post(LOGIN_COURIER_PATH);
    }

    @Step("Удалить курьера")
    public void deleteCourier(CourierLoginDto courier) {
        Integer id =
                given()
                        .spec(baseSpec())
                        .and()
                        .body(courier)
                        .when()
                        .post(LOGIN_COURIER_PATH)
                        .then().extract().body().path("id");
        if (id != null) {
            given()
                    .spec(baseSpec())
                    .and()
                    .delete(DELETE_COURIER_PATH, id.toString());
        }
    }
}