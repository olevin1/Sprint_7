package specification;

import domain.order.OrderDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderSpecification extends BasicSpecification {
    private final static String CREATE_ORDER_PATH = "/api/v1/orders";
    private final static String GET_ORDER_PATH = "/api/v1/orders";

    @Step("Создать заказ")
    public Response createOrder(OrderDto order) {
        return given()
                .spec(baseSpec())
                .and()
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH);
    }

    @Step("Получить список заказов")
    public Response getListOrders() {
        return given()
                .spec(baseSpec())
                .and()
                .get(GET_ORDER_PATH);
    }
}