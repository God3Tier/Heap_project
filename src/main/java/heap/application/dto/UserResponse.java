package heap.application.dto;

import heap.application.user.Roles;

public record UserResponse 
(
    String id,
    String username,
    Roles roles,
    Boolean active
)
{}
