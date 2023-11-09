import com.github.javafaker.Faker;
import ru.praktikum.model.user.CreateUserDto;


public class UserData {

    private final Faker faker = new Faker();

    public CreateUserDto getUserData() {
        return CreateUserDto.builder()
                .password(faker.internet().password())
                .email(faker.internet().emailAddress())
                .name(faker.name().name())
                .build();
    }

    public String getNewUserName() {
        return faker.name().name();
    }

    public String getNewUserMail() {
        return faker.internet().emailAddress();
    }

}
