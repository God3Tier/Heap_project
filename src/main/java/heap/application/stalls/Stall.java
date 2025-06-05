package heap.application.stalls;


public record Stall(
    Integer stallID,
    // If we need to be more restrictive, put this in enum
    String Location,
    MealType mealType,
    Integer rating,
    Double Budget
)
{}