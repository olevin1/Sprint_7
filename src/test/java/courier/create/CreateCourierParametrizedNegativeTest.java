package courier.create;

import domain.courier.CourierCreateDto;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import specification.CourierSpecification;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class CreateCourierParametrizedNegativeTest {
    private final static String MESSAGE_NOT_ENOUGH_DATA = "Недостаточно данных для создания учетной записи";
    private final static String RANDOM_STRING_LOGIN = RandomStringUtils.randomAlphanumeric(5, 15);
    private final static String RANDOM_STRING_PASSWORD = RandomStringUtils.randomAlphanumeric(5, 15);
    private final static String RANDOM_STRING_FIRST_NAME = RandomStringUtils.randomAlphanumeric(5, 15);
    private final String login;
    private final String password;
    private final CourierSpecification specification = new CourierSpecification();

    public CreateCourierParametrizedNegativeTest(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters(name = "login = {0} | password = {1}")
    public static Object[][] textData() {
        return new Object[][]{
                {null, RANDOM_STRING_PASSWORD},
                {"", RANDOM_STRING_PASSWORD},
                {RANDOM_STRING_LOGIN, null},
                {RANDOM_STRING_LOGIN, ""}
        };
    }

    @Test
    @DisplayName("Создание курьера - запрос с НЕдопустимыми значениями обязательных полей")
    @Description("Проверка создания курьера с недопустимыми значениями обязательных полей")
    public void createCourierWithoutRequiredFields() {
        specification.createCourier(new CourierCreateDto(login, password, RANDOM_STRING_FIRST_NAME))
                .then().statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat().body("message", equalTo(MESSAGE_NOT_ENOUGH_DATA));
    }
}