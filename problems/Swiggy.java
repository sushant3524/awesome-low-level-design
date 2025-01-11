public class Swiggy {
}
/**
 * Swiggy
 * list of customers
 * list of restaurants
 *
     * Customer
     *      id
     *      name
     *      email
     *      password
     *      historyOrder
     *      address
     *
     *  FoodItem
     *      name
     *      id
     *      price
     *      inStock
     *
     *
     *  Menu
     *      menuId
     *      restaurantId
     *      list pf foodItems
     *
     *  Restaurant
     *      id
     *      menu
     *
     *  Orders
     *      userId
 *          orderId
     *      list of items
     *      restaurant
     *      status - in process
     *
     * DeliveryAgent
     *      id
     *      map order vs address
     *
     *  OrderManagementService
     *      order
     *      agent vs order
     *      placeOrder(list of items, restaurantId, Customer customer)
     *
         *  assignToAgent(order)
         *      agent
     *
     *
     */
