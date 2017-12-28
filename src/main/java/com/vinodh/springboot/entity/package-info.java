/**
 * 
 * courtesy::
 * https://www.javaworld.com/article/2077819/java-se/understanding-jpa-part-2-relationships-the-jpa-way.html
 * 
 * Problem Description:
 * 
 * A customer can have zero or more orders, and each order can be associated
 * with one or more products. For each order, an order invoice is generated for
 * billing.
 * 
 * To survey its customers to see how satisfied they are with its products, and
 * hence needs to find out how many products each customer has. In order to
 * figure out how to improve the quality of its products, the company also wants
 * to conduct a special survey of those customers who cancelled their
 * subscriptions within the first month.
 * 
 * Useful Mapping Information:
 * 
 * The owning side is responsible for propagating the update of the relationship
 * to the database. Usually this is the side with the foreign key.
 * 
 * E.g: OrderInvoice table is owning side for Orders table(inverse side which
 * maps to the owning side)
 * 
 * To fetch Orders for a particular OrderInvoice em.find(OrderInvoice.class, 1);
 * To fetch an OrderInvoice for a particular Order em.find(Orders.class, 111);
 * 
 * Get order details for a particular customer and viceversa
 * 
 * A customer can have zero or more orders, whereas an order is mapped to one
 * customer. Thus, a Customer enjoys a one-to-many relationship with an Order,
 * whereas an Order has a many-to-one relationship with the Customer.
 * 
 * The entity that specifies the @JoinTable is the owner of the relationship in
 * MANY-to-MANY mapping.
 *
 * @author Vinodh Kumar Thimmisetty
 *
 */
package com.vinodh.springboot.entity;