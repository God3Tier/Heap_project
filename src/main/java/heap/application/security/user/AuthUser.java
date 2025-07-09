package heap.application.security.user;

import heap.application.user.Roles;

public record AuthUser (
    String userId,
    Roles roles
)
{}
