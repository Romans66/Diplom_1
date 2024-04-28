package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class BurgerTest {
    
    // Индексы элементов в LIST
    private final int zeroIndex = 0;
    private final int firstIndex = 1;
    
    // Тестовые данные
    private final IngredientType sauce = IngredientType.SAUCE;
    private final IngredientType filling = IngredientType.FILLING;
    private String bunName;
    private String ingredientName;
    private float price;
    
    // Моки
    private Ingredient ingredientMockSauce = Mockito.mock(Ingredient.class);
    private Ingredient ingredientMockFilling = Mockito.mock(Ingredient.class);
    
    private Bun bunMock = Mockito.mock(Bun.class);
    private Burger burgerSpy = Mockito.spy(Burger.class);
    
    public BurgerTest(String bunName, String ingredientName, float price) {
        this.bunName = bunName;
        this.ingredientName = ingredientName;
        this.price = price;
    }
    
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Just a bun", "Just a ingredient", 100},
                {"Булка", "Ингредиент", 1},
                {"", "", 0},
                {" ", " ", -100},
                {"@%!", "@!$", 10000000},
        };
    }
    
    @Before
    public void setUp() {
        burgerSpy.setBuns(bunMock);
        burgerSpy.addIngredient(ingredientMockSauce);
        burgerSpy.addIngredient(ingredientMockFilling);
    }
    
    @Test
    public void setBunsTest() {
        Mockito.when(bunMock.getName()).thenReturn(bunName);
        Mockito.when(bunMock.getPrice()).thenReturn(price);
        
        assertThat(burgerSpy.bun.getName(), equalTo(bunName));
        assertThat(burgerSpy.bun.getPrice(), equalTo(price));
    }
    
    @Test
    public void addIngredientTest() {
        
        Mockito.when(ingredientMockSauce.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientMockSauce.getPrice()).thenReturn(price);
        Mockito.when(ingredientMockSauce.getType()).thenReturn(sauce);
        
        Mockito.when(ingredientMockFilling.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientMockFilling.getPrice()).thenReturn(price);
        Mockito.when(ingredientMockFilling.getType()).thenReturn(filling);
        
        assertThat(burgerSpy.ingredients.get(zeroIndex).getName(), equalTo(ingredientName));
        assertThat(burgerSpy.ingredients.get(zeroIndex).getPrice(), equalTo(price));
        assertThat(burgerSpy.ingredients.get(zeroIndex).getType(), equalTo(sauce));
        
        assertThat(burgerSpy.ingredients.get(firstIndex).getName(), equalTo(ingredientName));
        assertThat(burgerSpy.ingredients.get(firstIndex).getPrice(), equalTo(price));
        assertThat(burgerSpy.ingredients.get(firstIndex).getType(), equalTo(filling));
    }
    
    @Test
    public void removeIngredientTest() {
        int oneElementSize = 1;
        int twoElementSize = 2;
        
        assertThat(burgerSpy.ingredients.size(), equalTo(twoElementSize));
        burgerSpy.removeIngredient(zeroIndex);
        assertThat(burgerSpy.ingredients.size(), equalTo(oneElementSize));
    }
    
    @Test
    public void moveIngredientTest() {
        Mockito.when(ingredientMockSauce.getType()).thenReturn(sauce);
        Mockito.when(ingredientMockFilling.getType()).thenReturn(filling);
        
        burgerSpy.moveIngredient(zeroIndex, firstIndex);
        
        assertThat(burgerSpy.ingredients.get(0).getType(), equalTo(filling));
        assertThat(burgerSpy.ingredients.get(1).getType(), equalTo(sauce));
    }
    
    @Test
    public void getPriceTest() {
        Mockito.when(ingredientMockSauce.getPrice()).thenReturn(price);
        Mockito.when(ingredientMockFilling.getPrice()).thenReturn(price);
        Mockito.when(bunMock.getPrice()).thenReturn(price);
        
        burgerSpy.setBuns(bunMock);
        
        // Цена за bun и 2 ингредиента
        float expectedPrice = price * 4;
        float actual = burgerSpy.getPrice();
        
        assertThat(actual, equalTo(expectedPrice));
    }
    
    @Test
    public void getReceiptTest() {
        burgerSpy.setBuns(bunMock);
        
        Mockito.when(bunMock.getName()).thenReturn(bunName);
        Mockito.when(ingredientMockSauce.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientMockSauce.getType()).thenReturn(sauce);
        Mockito.when(ingredientMockFilling.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientMockFilling.getType()).thenReturn(filling);
        Mockito.when(burgerSpy.getPrice()).thenReturn(price);
        
        String expectedReceipt =
                new StringBuilder(String.format("(==== %s ====)%n", bunMock.getName()))
                        + String.format("= %s %s =%n", ingredientMockSauce.getType().toString().toLowerCase(),
                        ingredientMockSauce.getName())
                        + String.format("= %s %s =%n", ingredientMockFilling.getType().toString().toLowerCase(),
                        ingredientMockFilling.getName()) + String.format("(==== %s ====)%n", bunMock.getName())
                        + String.format("%nPrice: %f%n", burgerSpy.getPrice());
        
        String actual = burgerSpy.getReceipt();
        assertThat(actual, equalTo(expectedReceipt));
        
        System.out.println(actual);
        
    }
}