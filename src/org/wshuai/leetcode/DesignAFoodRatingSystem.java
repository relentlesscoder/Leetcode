package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 01/07/2024.
 * #2353 https://leetcode.com/problems/design-a-food-rating-system/
 */
public class DesignAFoodRatingSystem {

    // time O(n * log(n)), space O(n)
    private class FoodRatings {

        private Map<String, Food> foodCuisine;
        private Map<String, TreeSet<Food>> topRating;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            this.foodCuisine = new HashMap<>();
            this.topRating = new HashMap<>();
            for (int i = 0; i < foods.length; i++) {
                Food f = new Food(foods[i], cuisines[i], ratings[i]);
                this.foodCuisine.put(foods[i], f);
                this.topRating.putIfAbsent(cuisines[i], new TreeSet<>());
                this.topRating.get(cuisines[i]).add(f);
            }
        }

        public void changeRating(String food, int newRating) {
            Food of = foodCuisine.get(food), nf = new Food(of.food, of.cuisine, newRating);
            TreeSet<Food> foodsByCuisine = this.topRating.get(of.cuisine);
            foodCuisine.put(food, nf);
            foodsByCuisine.remove(of);
            foodsByCuisine.add(nf);
        }

        public String highestRated(String cuisine) {
            TreeSet<Food> foodsByCuisine = this.topRating.get(cuisine);
            return foodsByCuisine.first().food;
        }

        private class Food implements Comparable<Food> {

            private String food;
            private String cuisine;
            private int rating;

            private Food(String food, String cuisine, int rating) {
                this.food = food;
                this.cuisine = cuisine;
                this.rating = rating;
            }

            @Override
            public int compareTo(Food other) {
                if (this.rating == other.rating) {
                    return this.food.compareTo(other.food);
                }
                return Integer.compare(other.rating, this.rating);
            }
        }
    }

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
}
