package order.get;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import specification.OrderSpecification;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GetListOrdersTest {
    private final OrderSpecification specification = new OrderSpecification();

    @Test
    @DisplayName("Получение списка заказов - успешное получение списка")
    @Description("Получение списка заказов и проверка ответа")
    public void getListOrdersSuccess() {
        specification.getListOrders()
                .then().statusCode(SC_OK)
                .and()
                .assertThat().body("orders", notNullValue());
    }
}