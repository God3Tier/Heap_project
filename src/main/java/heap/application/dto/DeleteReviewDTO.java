package heap.application.dto;

public record DeleteReviewDTO (
    Integer reviewId,
    Integer userId,
    String username
) {}
