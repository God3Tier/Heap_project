package heap.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDTO {
    private Integer reviewId;
    private Integer rating;
    private Integer stallId;
    private Integer userId;
    private String reviewDescription;
}
