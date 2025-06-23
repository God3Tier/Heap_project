package heap.application.stalls;

import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import heap.application.meal.Meal;
import heap.application.review.Review;
import heap.application.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stall")
@Getter
@Setter 
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
        name = "stall_meal",
        joinColumns = @JoinColumn(name = "stall_id"),
        inverseJoinColumns = @JoinColumn(name = "meal_id")
    )
    private List<Meal> meals;

    @JsonProperty("meal_ids")
    private List<Integer> mealIds;

    @OneToMany
    @JoinColumn(name = "stall_id")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable (
        name = "favourites",
        joinColumns = @JoinColumn(name = "stall_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likes;
    
	@Override
	public String toString() {
        return "Stall [" +
                "stallId=" + stallId +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", mealIds=" + mealIds +
                ", mealsCount=" + (meals != null ? meals.size() : 0) +
                ", reviews" + reviews +
                ",reviewCount " + (reviews != null ? reviews.size() : 0) + 
                ']';
    }
	@Override
	public boolean equals(final Object o) {
	    if (o instanceof final Stall s2) {
			return this.name == s2.name && this.stallId == s2.stallId && this.location == s2.location
			           && this.meals.containsAll(s2.meals) && s2.meals.containsAll(this.meals) && this.price == s2.price && this.rating == s2.rating;
		}
		return false;
	}
	
	
	@Override 
	public int hashCode() {
	    return Objects.hash(name, stallId, location, price, meals);
	}
	
	@Override
	public int compareTo (final Stall s2) {
	    if (this.price != s2.price) {
			return (int)Math.round(this.price - s2.price);
		}
		
		return s2.rating - this.rating;
	}
}
