package heap.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import heap.application.dto.FilterDTO;
import heap.application.service.StallService;
import heap.application.stalls.Stall;

@RestController
@RequestMapping("/")
public class StallMealController {
    private final StallService stallService;
    private final Logger log = LoggerFactory.getLogger(StallMealController.class);
    
    public StallMealController(StallService stallService) {
        this.stallService = stallService;
    }
    
    /*
     * Getters
     */
    @GetMapping("/stalls")
    public List<Stall> selectAllStalls() {
        return stallService.getAllStalls();
    }
    
    @GetMapping("/filter")
    public List<Stall> getValidRestaurants(FilterDTO filterDTO) {
        return stallService.getFilteredResult(filterDTO);
    }

    /*
     * Deleters
     */
    @DeleteMapping("/delete/stalls/{id}") 
    public ResponseEntity<?> deleteStall(Integer id) {
        stallService.deleteStall(id);
        return new ResponseEntity<>("Successfuly deleted stall", HttpStatus.OK);
    } 

    @DeleteMapping("/delete/meal/{id}")
    public ResponseEntity<?> deleteMeal(Integer id) {
        stallService.deleteMeal(id);
        return new ResponseEntity<>("Successfuly deleted stall", HttpStatus.OK);
    }
}


