# FoodieApp
App that shows a list of recipes and allows user to create custom meals. Includes simple broadcast receiver that opens a random meal.

The foodie app displays editable and removable Mealitems in a RecyclerView with the option to click on an item and open a new activity that displays details for the Meal.
Or user can long click on an item to remove it from the list.

The three files in I am home broadcast are a separate app that sends a broadcast to the FoodieApp. Upon receiving the broadcast, a random meal is chosen and its detail
activtiy is opened.
