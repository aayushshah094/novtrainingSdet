# 1 Get 10 cities in descending alphabetical order.
Select city
From sakila.city
order by city DESC
Limit 10;

#2. Get all films with "airplane" in the title.
Select * From sakila.film
Where title Like '%airplane%';

#3. Get the highest payment amount.
Select max(amount)
From sakila.payment;

#4. Get the number of records in the customer table for store id #1.
Select * From sakila.customer 
Where store_id = 1;

#5. Get all payment records for customer with email address "NANCY.THOMAS@sakilacustomer.org"
Select * From sakila.customer
wHERE email Like "NANCY.THOMAS@sakilacustomer.org";

#6. Use a View to get the film info for actor Bob Fawcett.

Select * From sakila.actor_info
Where first_name Like 'Bob' And last_name Like 'Fawcett';

#7. Use a Stored Procedure to get the 4 inventory ids for the film "Alien Center" at Store #2. 
Select * From sakila.film
Where title Like'%Alien Center%'; #to get the film id

Set @p_film_count = 0;
call sakila.film_in_stock(15, 2, @p_film_count);
Select *;

#8. Insert a new store. Ensure that you use TRANSACTION. (This one is possible but it's tough! Pay attention to constraints and the order that you are inserting data.) 
start transaction;
Insert into sakila.store (store_id, manager_staff_id, address_id, last_update)
values (3,5,5, current_timestamp());
Insert into sakila.staff(staff_id, first_name, last_name, address_id, store_id, active, username, last_update) 
values ( 5, "aayush", "shah", 2, 2, 1, "aayush111", current_timestamp());
commit;

#9. Update the timestamp to the current date/time for the new store you entered in the previous question. 
Update store Set last_update = current_timestamp()
where store_id = 3;

#10. Delete the new store. 
Delete from sakila.store
Where store_id = 3;

#11. Using one SQL statement, get how many films are there in each rating category.
Select rating, count(rating) From sakila.film Group by rating;

#12. Get (in order) the first and last names of the 3 customers who have spent the most, along with how much they have paid overall.
select first_name, last_name , amount
From customer, payment
where customer.customer_id = payment.customer_id
order by amount desc
limit 3;

#13. Get all movies rented by the customer who spent the most. (Hint: This will either require nested queries, or more than two joins. one approach is much shorter than the other.)
Select customer_id, first_name, Last_name
from customer 
where customer_id in (select customer_id from payment where amount = 11.99);

#14. Get the first and last names of all customers who spent more than $150, along with how much they spent.
Select first_name, Last_name, customer.customer_id, payment.amount
from payment, customer
where customer.customer_id = payment.customer_id and amount >= 150;
