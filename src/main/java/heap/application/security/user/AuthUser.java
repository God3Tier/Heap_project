package heap.application.security.user;

import heap.application.user.Roles;

import java.util.List;

public record AuthUser (
    String userId,
    List<Roles> roles
)
{}
