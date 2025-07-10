package heap.application.review;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import heap.application.stalls.Stall;
import heap.application.user.User;

@Entity
@Data
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    private Double rating;

    @ManyToOne
    @JoinColumn(name = "stall_id")
    @JsonIgnore
    private Stall stall;

    @ManyToOne
    @JoinColumn(name = "app_user")
    @JsonIgnore
    private User user;

    private String reviewDescription;
}