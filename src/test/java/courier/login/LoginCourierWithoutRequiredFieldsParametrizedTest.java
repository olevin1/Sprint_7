package courier.login;

import domain.courier.CourierCreateDto;
import domain.courier.CourierLoginDto;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import specification.CourierSpecification;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class LoginCourierWithoutRequiredFieldsParametrizedTest {
    private final static String MESSAGE_NOT_ENOUGH_DATA = "Недостаточно данных для входа";
    private final static String RANDOM_STRING_LOGIN = RandomStringUtils.randomAlphanumeric(5, 15);
    private final static String RANDOM_STRING_PASSWORD = RandomStringUtils.randomAlphanumeric(5, 15);
    private final String login;
    private final String password;
    private final CourierSpecification specification = new CourierSpecification();

    public LoginCourierWithoutRequiredFieldsParametrizedTest(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters(name = "login = {0} | password = {1}")
    public static Object[][] textData() {
        return new Object[][]{
                {"", RANDOM_STRING_PASSWORD},
                {RANDOM_STRING_LOGIN, ""}
        };
    }

    @Before
    public void setUp() {
        specification.createCourier(new CourierCreateDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD));
    }

    @Test
    @DisplayName("Логин курьера в системе - авторизация без логина или пароля")
    @Description("Проверка невозможности авторизации без указания обязательных параметров")
    public void loginCourierWithoutRequiredFields() {
        specification.loginCourier(new CourierLoginDto(login, password))
                .then().assertThat().body("message", equalTo(MESSAGE_NOT_ENOUGH_DATA))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @After
    public void cleanData() {
        specification.deleteCourier(new CourierLoginDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD));
    }
}