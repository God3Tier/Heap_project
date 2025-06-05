package heap.application.stalls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class StallRepo {
    
    private List<Stall> stalls = new ArrayList<>();
    
    private static final Logger log = LoggerFactory.getLogger(StallRepo.class);
    private final JdbcClient jdbcClient;
    
    public StallRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    public int count() {
        return jdbcClient.sql("Select * from Stall")
                         .query()
                         .listOfRows()
                         .size();
    }
    
    public List<Stall> findAll() {
        return jdbcClient.sql("Select * from Stall")
                         .query(Stall.class)
                         .list();
    }
    
    public List<Stall> findByLocation(String location) {
        return jdbcClient.sql("Select * from Stall where location = :location")
                         .param("location", location)
                         .query(Stall.class)
                         .list();
    }
    
    public List<Stall> findByRating(Integer val) {
        return jdbcClient.sql("Select * from Stall where rating >= :rating")
                         .param("rating", val)
                         .query(Stall.class)
                         .list();
    }
    
    public List<Stall> findByBudget(Integer val) {
        return jdbcClient.sql("Select * from Stall where budget <= :budget")
                         .param("budget", val)
                         .query(Stall.class)
                         .list();
    }
    
    // This is just because I absolutely do not want to use a nested hell sql statement 
    public List<Stall> findByMeal_Id(Integer val) {
        List<Stall> res = new ArrayList<>();
        
        List<Integer> stallIdList = jdbcClient.sql("Select SID from Stall_Meal_Type where Meal_id = :MealId")
                                       .param("MealId", val)
                                       .query(Integer.class)
                                       .list();
                                       
        
        // I hope this is correct
        for (int i : stallIdList) {
            jdbcClient.sql("Select * from Stall where SID = :SID")
                      .param("SID", i)
                      .query(Stall.class)
                      .list()
                      .forEach(a -> res.add(a));
        }

        return res;
    }
    
    
    
}