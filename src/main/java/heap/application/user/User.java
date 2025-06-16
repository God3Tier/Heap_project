package heap.application.user;

import java.util.List;
import java.util.Objects;

import heap.application.review.Review;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
@Table(name = "user")
public class User implements Comparable<User> {
    @Id
    private Integer userId;
    private String name;
    @OneToMany
    @JoinTable (
        name = "user_reviews",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Review> reviews;
    
    @Override
    public int hashCode() {
        return Objects.hash(name, userId, reviews);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof User u) {
            return this.userId == u.userId;
        }
        return false;
    }
    
    @Override
    public int compareTo(User u) {
        return this.userId - u.userId;
    }
}