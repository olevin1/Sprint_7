package domain.courier;

public class CourierCreateDto {
    private String login;
    private String password;
    private String firstName;

    public CourierCreateDto() {
    }

    public CourierCreateDto(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCreateDto(final String login, final String password, final String firstName) {
        this(login, password);
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}