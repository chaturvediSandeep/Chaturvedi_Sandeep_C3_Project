import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static  org.hamcrest.CoreMatchers.is;
import static  org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    RestaurantService service = new RestaurantService();
    Restaurant  restaurant;
    @BeforeEach
    public void InitializeValues(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }



    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        // Modifying Restaurant Timing for this test case
        LocalTime openingTime = LocalTime.now().minusHours(2);
        LocalTime closingTime = LocalTime.now().plusHours(2);
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        boolean restaurantOpenOrNot = restaurant.isRestaurantOpen();
        assertTrue(restaurantOpenOrNot);

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime openingTime = LocalTime.now().plusHours(2);
        LocalTime closingTime = LocalTime.now().plusHours(4);
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        boolean restaurantOpenOrNot = restaurant.isRestaurantOpen();
        assertFalse(restaurantOpenOrNot);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<GetOrderValuePassCase>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void calling_getOrderValueMethod_should_return_order_value(){
        List<String> selectedMenuItem= new ArrayList<String>();
        selectedMenuItem.add("Sweet corn soup");
        selectedMenuItem.add("Vegetable lasagne");
        int returnedOrderValue = restaurant.getTotalOrderValue(selectedMenuItem);
        assertThat(returnedOrderValue,greaterThan(0));
    }
   

}
