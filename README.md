Products from a shopping website showcased in a Tinder like UI.
==============================================================

* Android app that implements tinder ui, based on https://github.com/exctasy2/tinder-card-stack

For each product group we have start_from, max_products sharepref key values so that we can see
what products we stored in our sqlite database.

Every time our adapter needs to be initialized, it queries for products from the sqlite database,
if there are still some products that are yet to be "swiped" by the user, it gets the products from
the db, else, it inserts new products from the internet by sending a post request 
starting from the start_from number into the database, and then queries the updated table for new 
products again and initializes the adapter.  

The most updated code is in the "Kids" fragment of the drawer Layout.