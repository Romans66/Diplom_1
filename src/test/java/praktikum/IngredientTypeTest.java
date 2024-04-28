package praktikum;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class IngredientTypeTest {
    
    @Test
    public void IngredientTypeSauceTest() {
        assertThat(IngredientType.SAUCE, equalTo(IngredientType.valueOf("SAUCE")));
    }
    
    @Test
    public void IngredientTypeFillingTest() {
        assertThat(IngredientType.FILLING, equalTo(IngredientType.valueOf("FILLING")));
    }
    
}
