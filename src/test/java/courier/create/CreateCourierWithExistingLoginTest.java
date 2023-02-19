package courier.create;

import domain.courier.CourierCreateDto;
import domain.courier.CourierLoginDto;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import specification.CourierSpecification;

import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierWithExistingLoginTest {
    private final static String MESSAGE_LOGIN_EXISTS = "Этот логин уже используется";
    //Тест падает, т.к. фактический текст сообщения не соответствует указанному в документации и в константе MESSAGE_LOGIN_EXISTS, это баг.
    private final static String RANDOM_STRING_LOGIN = RandomStringUtils.randomAlphanumeric(5, 15);
    private final static String RANDOM_STRING_PASSWORD = RandomStringUtils.randomAlphanumeric(5, 15);
    private final CourierSpecification specification = new CourierSpecification();

    @Before
    public void setUp() {
        specification.createCourier(new CourierCreateDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD));
    }

    @Test
    @DisplayName("Создание курьера - запрос с повторяющимся логином")
    @Description("Проверка невозможности создания курьера с уже существующим в системе логином")
    public void createCourierWithExistingLoginTest() {
        //Создание дубликата курьера, созданного выше
        specification.createCourier(new CourierCreateDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD))
                .then().assertThat().body("message", equalTo(MESSAGE_LOGIN_EXISTS))
                .and()
                .statusCode(SC_CONFLICT);
    }

    @After
    public void cleanData() {
        specification.deleteCourier(new CourierLoginDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD));
    }
}