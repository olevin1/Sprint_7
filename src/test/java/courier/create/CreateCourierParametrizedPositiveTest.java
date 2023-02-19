package courier.create;

import domain.courier.CourierCreateDto;
import domain.courier.CourierLoginDto;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import specification.CourierSpecification;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class CreateCourierParametrizedPositiveTest {
    private final static String RANDOM_STRING_LOGIN = RandomStringUtils.randomAlphanumeric(5, 15);
    private final static String RANDOM_STRING_PASSWORD = RandomStringUtils.randomAlphanumeric(5, 15);
    private final static String RANDOM_STRING_FIRST_NAME = RandomStringUtils.randomAlphanumeric(5, 15);
    private final String firstName;
    private final CourierSpecification specification = new CourierSpecification();

    public CreateCourierParametrizedPositiveTest(final String firstName) {
        this.firstName = firstName;
    }

    @Parameterized.Parameters(name = "firstName = {0}")
    public static Object[][] testData() {
        return new Object[][]{
                {null},
                {""},
                {RANDOM_STRING_FIRST_NAME}
        };
    }

    @Test
    @DisplayName("Создание курьера - запрос с допустимыми значениями")
    @Description("Проверка создания курьера с допустимыми значениями всех полей")
    public void createCourierSuccess() {
        specification.createCourier(new CourierCreateDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD, firstName))
                .then().assertThat().body("ok", is(true))
                .and()
                .statusCode(SC_CREATED);
    }

    @After
    public void cleanData() {
        specification.deleteCourier(new CourierLoginDto(RANDOM_STRING_LOGIN, RANDOM_STRING_PASSWORD));
    }
}