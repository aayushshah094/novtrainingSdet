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
/*Select * From sakila.film
Where title Like'%Alien Center%'; #to get the film id

Set @p_film_count = 0;
call sakila.film_in_stock(15, 2, @p_film_count);
Select *;
*/
set @filmID = (select film_id from film as f where f.title = 'Alien Center');
call film_in_stock(@filmId, 2, @out_value);

#8. Insert a new store. Ensure that you use TRANSACTION. (This one is possible but it's tough! Pay attention to constraints and the order that you are inserting data.) 
/* start transaction;
Insert into sakila.store (store_id, manager_staff_id, address_id, last_update)
values (3,5,5, current_timestamp());
Insert into sakila.staff(staff_id, first_name, last_name, address_id, store_id, active, username, last_update) 
values ( 5, "aayush", "shah", 2, 2, 1, "aayush111", current_timestamp());
commit;
*/
start transaction;
insert into staff(first_name, last_name, address_id, email, store_id, active, username) values("shah", "aayush", "1", "aayush@gmail.com", "3", "1", "ashah");
set @staffId = last_insert_id();
insert into address(address, district, city_id, phone, location) values ("100 street", "district", 10, 1234567890, ST_GeomFromText('Point(1 1)'));
set @addressId = last_insert_id();
insert into store(manager_staff_id, address_id) values(@staffId, @addressId);
set @storeId = last_insert_id();
update staff set store_id = @storeId where staff_id = @staffId;
rollback;

/* #9. Update the timestamp to the current date/time for the new store you entered in the previous question. 
Update store Set last_update = current_timestamp()
where store_id = 3;

#10. Delete the new store. 
Delete from sakila.store
Where store_id = 3;
*/

#11. Using one SQL statement, get how many films are there in each rating category.
Select rating, count(rating) From sakila.film Group by rating;

#12. Get (in order) the first and last names of the 3 customers who have spent the most, along with how much they have paid overall.
/* select first_name, last_name , amount
From customer, payment
where customer.customer_id = payment.customer_id
order by amount desc
limit 3;
*/
select c.customer_id,first_name, last_name, sum(amount) as 'Total Spent' from customer as c join payment as p on p.customer_id = c.customer_id group by c.customer_id order by 4 desc limit 1;

#13. Get all movies rented by the customer who spent the most. (Hint: This will either require nested queries, or more than two joins. one approach is much shorter than the other.)
/*Select customer_id, first_name, Last_name
from customer 
where customer_id in (select customer_id from payment where amount = 11.99);
*/
set @customer_id = (select karl.customer_id from (select c.customer_id, sum(amount) from customer as c join payment as p on p.customer_id = c.customer_id group by c.customer_id order by 2 desc limit 1) as karl);
select distinct(title) from film as f join inventory as i on f.film_id = i.film_id join rental as r on i.inventory_id = r.inventory_id join customer as c on r.customer_id = @customer_id order by 1 asc;

#14. Get the first and last names of all customers who spent more than $150, along with how much they spent.
/*Select first_name, Last_name, customer.customer_id, payment.amount
from payment, customer
where customer.customer_id = payment.customer_id and amount >= 150;
*/
select first_name, last_name, sum(amount) as 'Total Spent' from customer as c join payment as p on p.customer_id = c.customer_id group by c.customer_id having sum(amount) > 150 order by 2 asc;
