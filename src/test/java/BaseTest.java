import ru.praktikum.model.CreateUserDto;
import ru.praktikum.model.LoginUserDto;
import ru.praktikum.model.UserMapper;
import ru.praktikum.pojo.UserClient;

public class BaseTest {

    protected static final String REQUIRED_FIELDS = "Email, password and name are required fields";
    protected static final String ALREADY_EXISTS = "User already exists";
    protected static final String INCORRECT_FIELDS = "email or password are incorrect";
    protected final UserClient userClient = new UserClient();
    protected final UserMapper mapper = new UserMapper();
    protected CreateUserDto userDto;
    protected LoginUserDto loginUserDtoDto;
    protected final Data data = new Data();


}
