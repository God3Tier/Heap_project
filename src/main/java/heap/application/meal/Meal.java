package heap.application.meal;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import heap.application.stalls.Stall;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/*
 * Meal is a class that holds the different types of meals that can be filtered by stall
 * 
 * @autthor Joseph
 */
@Entity
@Table(name = "mealname")
@Access(AccessType.FIELD)
@Getter
@Setter
public class Meal implements Comparable<Meal> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Integer mealId;
    @JsonProperty("meal_name")
    private String mealName;
    @ManyToMany(mappedBy = "meals")
    @JsonIgnore
    private List<Stall> stall;
    
    public Meal() {}
    
    public Meal(Integer mealId, String mealName, List<Stall> stall) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.stall = stall;
    }

	@Override 
	public int hashCode() {
	    return Objects.hash(mealId, mealName, stall);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (o instanceof Meal m) {
			return this.mealId == m.mealId && this.mealName == m.mealName && this.stall.equals(m.stall);
		}
	
	    return false;
	}
	
	@Override
	public int compareTo(Meal m2) {
	    return this.mealId - m2.mealId;
	}
}