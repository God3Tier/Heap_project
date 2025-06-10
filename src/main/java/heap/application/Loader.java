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

import heap.application.stalls.Meal.MealTypeRepo;
import heap.application.stalls.Meal.MealType;
import heap.application.stalls.Meal.Meals;
import heap.application.stalls.StallRepo;
import heap.application.stalls.Stalls;
import heap.application.stalls.Stall;

@Component
public class Loader implements CommandLineRunner{
    
    private final static Logger log = LoggerFactory.getLogger(Loader.class);
    private final ObjectMapper objectMapper;
    
    private final MealTypeRepo mealTypeRepo;
    private final StallRepo stallRepo;
    
    public Loader(ObjectMapper objectMapper, MealTypeRepo mealTypeRepo, StallRepo stallRepo) {
        this.objectMapper = objectMapper;
        this.mealTypeRepo = mealTypeRepo;
        this.stallRepo = stallRepo;
    }
    
    public void loadStalls() {
        try (InputStream inputStream = getClass().getResourceAsStream("/fakedata/stalls.json")) {
            Stalls allStalls = objectMapper.readValue(inputStream, Stalls.class);
            log.info("Reading {} runs from JSON data for in mem usage", allStalls.stalls().size());
            log.info("{}", allStalls.stalls());
            for (Stall stall : allStalls.stalls()) {
                List<MealType> mealTypes = stall.getMealIds().stream()
                                                             .map(a -> mealTypeRepo.findById(a)
                                                                 .orElseThrow(()-> new RuntimeException("meal type not found")))
                                                             .collect(Collectors.toCollection(ArrayList::new));
                stall.setMealTypes(mealTypes);
            }
            stallRepo.saveAll(allStalls.stalls());
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file ", e);
        }
    }
    public void loadMeal() {
        try (InputStream inputStream = getClass().getResourceAsStream("/fakedata/meal_loader.json")) {
            Meals allMeals = objectMapper.readValue(inputStream, Meals.class);
            log.info("{}", allMeals);
            log.info("Reading {} runs from JSON data for in mem usage", allMeals.meals().size());
            mealTypeRepo.saveAll(allMeals.meals());
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file ", e);
        }
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (mealTypeRepo.count() == 0) {
            loadMeal();
        }
        
        log.info("meals successfully loaded");
        if (stallRepo.count() == 0) {
            loadStalls();
        }
        log.info("stalls successfully loaded");
    }
}