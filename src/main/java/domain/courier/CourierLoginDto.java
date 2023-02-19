package domain.courier;

public class CourierLoginDto {
    private String login;
    private String password;

    public CourierLoginDto() {
    }

    public CourierLoginDto(final String login, final String password) {
        this.login = login;
        this.password = password;
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
}