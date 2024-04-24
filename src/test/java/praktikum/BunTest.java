package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;
    private Bun bun;
    
    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }
    
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Воппер", 100f},
                {"CheeseBurger", 10000000f},
                {"", 1f},
                {" ", -100f},
                {"@$@F!", 0},
        };
    }
    
    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }
    
    @Test
    public void getNameTest() {
        assertThat(bun.getName(), equalTo(name));
    }
    
    @Test
    public void getPriceTest() {
        assertThat(bun.getPrice(), equalTo(price));
    }
}