package heap.application.dto;

public record UpdateReviewDTO(
    Integer reviewId,
    Integer userId,
    String username,
    Double rating, 
    String reviewDescription
) 
{}
