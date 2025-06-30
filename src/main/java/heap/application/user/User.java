package heap.application.user;

import java.util.List;
import java.util.Objects;

import heap.application.review.Review;
import heap.application.stalls.Stall;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "app_user")
public class User implements Comparable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    @OneToMany
    @JoinColumn(name="user_id")
    private List<Review> reviews;
    @ManyToMany
    @JoinTable(
        name = "favourites",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "stall_id")
    )
    private List<Stall> favourites;
    

    
    @Override
    public int hashCode() {
        return Objects.hash(name, userId, reviews);
    }
    
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", reviews=" + reviews + ", favourites=" + favourites
                + "]";
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