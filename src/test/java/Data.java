import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.praktikum.model.CreateUserDto;


public class Data {

    private final Faker faker = new Faker();

    CreateUserDto getUserData() {
    return CreateUserDto.builder()
            .password(faker.internet().password())
            .email(faker.internet().emailAddress())
            .name(faker.name().name())
            .build();
    }

}
