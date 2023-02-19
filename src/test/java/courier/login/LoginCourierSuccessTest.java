package courier.login;

import domain.courier.CourierCreateDto;
import domain.courier.CourierLoginDto;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import specification.CourierSpecification;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierSuccessTest {
    private final static String RANDOM_STRING_LOGIN = RandomStringUtils.randomAlphanumeric(5, 15);
    private final static String RANDOM_STRING_PASSWORD = RandomStringUtils.randomAlphanumeric(5, 15);
    private final CourierSpecification specification = new CourierSpecification();

    @Before
    public void setUp() {
        specification.createCourier(new CourierCreateDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD));
    }

    @Test
    @DisplayName("Логин курьера в системе - успешная авторизация курьера в системе")
    @Description("Проверка авторизации с корректными логином и паролем")
    public void loginCourierSuccess() {
        specification.loginCourier(new CourierLoginDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD))
                .then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(SC_OK);
    }

    @After
    public void cleanData() {
        specification.deleteCourier(new CourierLoginDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD));
    }
}