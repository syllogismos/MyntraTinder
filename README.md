Products from a Fashion website showcased in a Tinder like UI.
==============================================================
[![Build Status](https://secure.travis-ci.org/syllogismos/MyntraTinder.svg)](http://travis-ci.org/syllogismos/MyntraTinder)
* Android app that implements tinder ui, based on https://github.com/exctasy2/tinder-card-stack
* Latest apk build file [here](https://github.com/syllogismos/MyntraTinder/blob/master/app-debug.apk)
* Current build is pretty decent, I'm pretty happy with it logically, UI can be improved.  

## Note:
Currently the the rest endpoints I used to query for the products are not working any more. Myntra must have updated their rest endpoints. So sadly you can't experience this in all of this gloriousness :D

TODO:
Use below updated cool REST api, instead of old
Current REST endpoints: http://developer.myntra.com/search/data/men-casual%20shoes?start=10&rows=69

## UI
![Navigation Drawer](http://i.imgur.com/d7o9Ccz.png "Navigation Drawer")
![Swipe Right](http://i.imgur.com/FbgLOf4.png "Like")
![Swipe Left](http://i.imgur.com/sTMeDDr.png "Dislike")  

## Relavent Activities, Fragments, Views, Models, Resources, Layouts and Adapters

### Activities:
* **MyntraTinderActivity**: This is the main activity that is based on the default DrawerLayoutActivity provided
to us in Android Studio.  
It extends **NavigationDrawerCallbacks** from **NavigationDrawerFragmentSingleElv** to handle click events
in the drawer fragment.  

    * *Layout*: *activity_myntra_tinder.xml*

The rest of the activities in the project are just for testing and trying various things.

### Fragments:
* **NavigationDrawerFragmentSingleElv**: Fragment that implements the navigation drawer, that has a single
*ExpandbleListView* where you can find all the product groups, you click on the relavent product group
to get products presented to you in the form a Tinder Stack. It also has a list view for things like
"Home", "Settings" etc.,
    * *Layouts*: *fragment_navigation_drawer_myntra_tinder_activity_single_elv.xml*
    * *Adapters*: **MyntraCategoryExpandableListAdapter**, to populate various product categories to our *ExpandableListView*

* **TinderUIFragment**: Fragment that has the ProductStackView with in which we showcase a given list of products
in a tinder stack.  
    * *Layouts*: *fragment_tinder_ui.xml*
    * *Adapters*:
    
* **HomeFragment**: Place holder fragment that you can further develop upon to show what ever you want, currently, 
this fragment shows up when you click ListView items like "Home", "Settings".
    * *Layouts*: *fragment_home.xml*
    * *Adapters*:
  
* **LikedProductsFragment**: Fragment that contains a single ListView that is used to display all the liked products from
a single product group. This is implemented inside the **MyntraTinderActivity**
    * *Layouts*: *activity_product_list_view.xml*
    * *Adapters*: **ProductListAdapterWithACursor**, a cursor adapter that takes a cursor obtained from a database
    operation and fills our listView
    
### Views:
* **SingleProductView**: View that implements what each Card in the TinderStack looks like, this is what you swipe right 
or left to like or dislike a particular product.  
    * *Layouts*: *product_card.xml*
    * *Adapters*:

* **ProductStackView**: View that contains the whole stack, 
    * *Layouts*: 
    * *Adapters*: **ProductCardAdapter**, adapter that loads, given list of products in to our tinder stack, you can find
    helper functions inside this adapter and see how we initialize the adapter in various cases.

### Models:
* **Product**: A model that defines a single Product and its parameters
* **MyntraCategory**: A model that defines product categories, sub categories etc., and also an helper function to load 
these into our ExpandableListView.

### Utils:
* **DatabaseHelper**: Helper functions that handle all database operations and our db schema is also defined in this.
* **Downloader**: Helper functions to download json, to our phones filesystem, given url, postData and filename
* **ProductsJSONPullParser**: Helper functions to parse a given json file and return a list of Products.

## Additional Notes:

#### Note1:
When you select a product category/group in the expandable list view, it queries the database for 20 products from that
category, if the db doesn't even have a single product from that category, it gets new products from the server side, 96 
at a time and fill the db with new products. And returns 20 new products to the ProductStackAdapter

#### Note2:
Our product categories have 3 levels of hierarchy, but ExpandableListView has only 2 levels, so the top most level, 
say "Men" is also technically a "Group" with no children, it only behaves as a header to the next two levels in the hierarchy.
Below is the actual hierarchy of product groups.  

* Men
    * Footwear
        1. Casual Shoes
        2. Formal Shoes
    * Accessories
        1. Watches
        2. Sunglasses
* Women
    * Footwear
        1. Heels
        2. Wedges
    * Accessories
        1. Watches
        2. Sunglasses

But for our convenience, to put it inside an ExpandableListView, we changed it to below  

* Men
* Footwear
    1. Casual Shoes
    2. Formal Shoes
* Accessories
    1. Watches
    2. Sunglasses
* Women
* Footwear
    1. Heels
    2. Wedges
* Accessories
    1. Watches
    2. Sunglasses
