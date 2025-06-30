package heap.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import heap.application.stalls.StallRepo;
import heap.application.meal.Meal;
import heap.application.meal.MealRepo;
import heap.application.stalls.Stall;

// @Component
public class Loader /* implements CommandLineRunner */ {
    
    private final static Logger log = LoggerFactory.getLogger(Loader.class);
    private final ObjectMapper objectMapper;
    
    private final MealRepo mealRepo;
    private final StallRepo stallRepo;
    
    public Loader(ObjectMapper objectMapper, MealRepo mealRepo, StallRepo stallRepo) {
        this.objectMapper = objectMapper;
        this.mealRepo = mealRepo;
        this.stallRepo = stallRepo;
    }
    
    public void loadStalls() {
        try (InputStream inputStream = getClass().getResourceAsStream("/fakedata/stalls.json")) {
            Stall[] stalls = objectMapper.readValue(inputStream, Stall[].class);
            log.info("Reading {} runs from JSON data for in mem usage", stalls.length);
            for (Stall stall : stalls) {
                List<Meal> mealTypes = stall.getMealIds().stream()
                                                             .map(a -> mealRepo.findById(a)
                                                                 .orElseThrow(()-> new RuntimeException("meal type not found")))
                                                             .collect(Collectors.toCollection(ArrayList::new));
                stall.setMeals(mealTypes);
            }
            stallRepo.saveAll(List.of(stalls));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file ", e);
        } 
    }
    public void loadMeal() {
        try (InputStream inputStream = getClass().getResourceAsStream("/fakedata/meal_loader.json")) {
            Meal[] meals = objectMapper.readValue(inputStream, Meal[].class);
            log.info("Reading {} runs from JSON data for in mem usage", meals.length);
            mealRepo.saveAll(List.of(meals));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file ", e);
        }
    }
    
    // @Override
    // public void run(String... args) throws Exception {
    //     if (mealRepo.count() == 0) {
    //         loadMeal();
    //     }
        
    //     log.info("meals successfully loaded");
    //     if (stallRepo.count() == 0) {
    //         loadStalls();
    //     }
    //     log.info("stalls successfully loaded");
    // }
}