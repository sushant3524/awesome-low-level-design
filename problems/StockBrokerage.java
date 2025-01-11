public class StockBrokerage {
}
/**
 * list of tradingAccount
 *
 * map of accounts
 * map of stocks
 * orderManagementService
 *     map of orders
 *     orderQueue
     * placeorder(order)
     *      orderQueue.offer(order)
     *      processOrder()
 *
     * processOrders() {
     *     while(queue is not empty)
     *      order.execute()
     * }
 *
 * TradingAccount
 *      User
 *      balance
 *      portfolio
 *      id
 *
 * Order
 *      id
 *      account
 *      stock
 *      quantity
 *      price
 *      status
 *
 * BuyOrder
 *      execute
 *
 * SellOrder
 *      execute
 *
 * Portfolio
 *      account
 *      map holdings
 *      orderHistory
 *
 * Stock
 *      id
 *      name
 *      price
 */