package heap.application.dto;

import java.util.List;

import heap.application.user.Roles;

public record UserResponse 
(
    String id,
    String username,
    List<Roles> roles,
    Boolean active
)
{}
