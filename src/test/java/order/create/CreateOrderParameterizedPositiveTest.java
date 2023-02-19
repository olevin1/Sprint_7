package order.create;

import domain.order.OrderDto;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import specification.OrderSpecification;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedPositiveTest {
    private final OrderSpecification specification = new OrderSpecification();
    private final String[] color;

    public CreateOrderParameterizedPositiveTest(final String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "color = {0}")
    public static Object[][] testData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Создание заказа - заказ с выбором цвета самоката")
    @Description("Проверка создания заказов с указанием предпочитаемых цветов")
    public void createOrderSuccess() {
        OrderDto order = new OrderDto("Naruto", "Uchiha", "Konoha, 142 apt.",
                "4", "+7 800 355 35 35", 5, "2020-06-06",
                "Saske, come back to Konoha", color
        );
        specification.createOrder(order)
                .then().statusCode(SC_CREATED)
                .and()
                .assertThat().body("track", notNullValue());
    }
}