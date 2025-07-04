package heap.application.dto;

public record ReviewDTO (
    Integer reviewId,
    Integer rating,
    Integer stallId,
    Integer userId,
    String reviewDescription
)
{}
