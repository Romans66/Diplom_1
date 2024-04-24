package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class IngredientTest {
    private static IngredientType type;
    private Ingredient ingredient;
    private final String name;
    private final float price;
    private final IngredientType sauce = IngredientType.SAUCE;
    private final IngredientType filling = IngredientType.FILLING;
    
    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }
    
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {type.SAUCE, "Салат", 100f},
                {type.SAUCE, "", 1f},
                {type.SAUCE, "Salad", 1000000f},
                {type.SAUCE, " ", -100f},
                {type.SAUCE, "!@$@#", 0},
                {type.FILLING, "Салат", 100f},
                {type.FILLING, "", 1f},
                {type.FILLING, "Salad", 1000000f},
                {type.FILLING, " ", -100f},
                {type.FILLING, "!@$@#", 0},
        };
    }
    
    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }
    
    @Test
    public void getPriceTest() {
        assertThat(ingredient.getPrice(), equalTo(price));
    }
    
    @Test
    public void getNameTest() {
        assertThat(ingredient.getName(), equalTo(name));
    }
    
    @Test
    public void getTypeSauceTest() {
        ingredient = new Ingredient(sauce, name, price);
        assertThat(ingredient.getType(), equalTo(sauce));
    }
    
    @Test
    public void getTypeFillingTest() {
        ingredient = new Ingredient(filling, name, price);
        assertThat(ingredient.getType(), equalTo(filling));
    }
}