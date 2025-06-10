package heap.application.Meal;

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

@Entity
@Table(name = "mealtype")
@Access(AccessType.FIELD)
public class MealType implements Comparable<MealType> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Integer mealId;
    @JsonProperty("meal_name")
    private String mealName;
    @ManyToMany(mappedBy = "mealTypes")
    @JsonIgnore
    private List<Stall> stall;
    
    public MealType() {}
    
    public MealType(Integer mealId, String mealName, List<Stall> stall) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.stall = stall;
    }
    
	public Integer getMealId() {
		return mealId;
	}
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
    
    
	public List<Stall> getStall() {
		return stall;
	}

	public void setStall(List<Stall> stall) {
		this.stall = stall;
	}

	@Override 
	public int hashCode() {
	    return Objects.hash(mealId, mealName, stall);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (o instanceof MealType m) {
			return this.mealId == m.mealId && this.mealName == m.mealName && this.stall.equals(m.stall);
		}
	
	    return false;
	}
	
	@Override
	public int compareTo(MealType m2) {
	    return this.mealId - m2.mealId;
	}
}