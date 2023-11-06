import ru.praktikum.model.order.IngredientListDto;
import ru.praktikum.model.user.CreateUserDto;
import ru.praktikum.model.user.LoginUserDto;
import ru.praktikum.model.user.UserMapper;
import ru.praktikum.pojo.OrderClient;
import ru.praktikum.pojo.UserClient;

public class BaseTest {

    protected static final String REQUIRED_FIELDS = "Email, password and name are required fields";
    protected static final String ALREADY_EXISTS = "User already exists";
    protected static final String INCORRECT_FIELDS = "email or password are incorrect";
    protected static final String NO_INGREDIENT = "Ingredient ids must be provided";


    protected final UserClient userClient = new UserClient();
    protected final OrderClient orderClient = new OrderClient();
    protected final UserMapper mapper = new UserMapper();
    protected CreateUserDto userDto;
    protected LoginUserDto loginUserDto;
    protected final UserData userData = new UserData();

    protected IngredientListDto ingredientListDto;
    protected String token;

    protected String[] generateIngredients(int n) {
        String[] ingredients = new String[n];
        for (int i = 0; i <= (ingredients.length - 1); i++) {
            ingredients[i] = orderClient.getIngredientId(i);
        }
        return ingredients;
    }

}
