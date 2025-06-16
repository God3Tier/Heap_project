package heap.application.review;

import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import heap.application.stalls.Stall;
import heap.application.user.User;

@Entity
@Getter
@Setter
@Table(name = "review")
public class Review {
    @Id
    private Integer rev_id;
    @ManyToOne
    @JoinTable(name = "stall_id")
    private Stall stall;
    // @OneToMany(mappedBy="meals")
    // private Integer meal_id;
    
    @ManyToOne
    @JoinTable(name = "user")
    private User user;
    
}