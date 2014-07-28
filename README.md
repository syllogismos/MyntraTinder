android app that implements tinder ui, based on https://github.com/exctasy2/tinder-card-stack

todo: save the downloaded product/card info into local sqlite db instead of a file, so it will be easier to query if the user already liked a particular product
todo: add a boolean property to product that tells weather the user like a product or not, and you can use this to query the local sqlite db
todo: implement the whole ui as a expendable list view inside a drawer layout and the tinder ui in fragments.