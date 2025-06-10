package heap.application.stalls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import heap.application.stalls.Meal.MealType;

@Entity
@Table(name = "stall")
public class Stall implements Comparable<Stall> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stall_id")
    private Integer stallId;
    @NonNull
    @Column(name = "location")
    private String location;
    @Positive
    @Column(name = "rating")
    private Integer rating;
    @Positive
    @Column(name = "price")
    private Double price;
    @NotNull
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(
        name = "stall_mealtype",
        joinColumns = @JoinColumn(name = "stall_id"),
        inverseJoinColumns = @JoinColumn(name = "mealtype_id")
    )
    private List<MealType> mealTypes;
    @JsonProperty("meal_ids")
    @Transient
    private List<Integer> mealIds;
    
	public Integer getStallId() {
		return stallId;
	}
	public void setStallId(final Integer stallId) {
		this.stallId = stallId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(final String location) {
		this.location = location;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(final Integer rating) {
		this.rating = rating;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(final Double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	
	public List<MealType> getMealTypes() {
        return mealTypes;
	}

    public void setMealTypes(final List<MealType> mealTypes) {
        this.mealTypes = mealTypes;
    }

	
	public List<Integer> getMealIds() {
		return mealIds;
	}
	public void setMealIds(final List<Integer> mealIds) {
		this.mealIds = mealIds;
	}
	
	@Override
	public String toString() {
        return "Stall{" +
                "stallId=" + stallId +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", mealIds=" + mealIds +
                ", mealsCount=" + (mealTypes != null ? mealTypes.size() : 0) +
                '}';
    }
	@Override
	public boolean equals(final Object o) {
	    if (o instanceof final Stall s2) {
			return this.name == s2.name && this.stallId == s2.stallId && this.location == s2.location
			           && this.mealTypes.containsAll(s2.mealTypes) && s2.mealTypes.containsAll(this.mealTypes) && this.price == s2.price && this.rating == s2.rating;
		}
		return false;
	}
	
	
	@Override 
	public int hashCode() {
	    return Objects.hash(name, stallId, location, price, mealTypes);
	}
	
	@Override
	public int compareTo (final Stall s2) {
	    if (this.price != s2.price) {
			return (int)Math.round(this.price - s2.price);
		}
		
		return s2.rating - this.rating;
	}

	public void setMeals(final List<MealType> mealTypes) {
		this.mealTypes = mealTypes;
	}
}
