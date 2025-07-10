package heap.application.security.user;

import java.util.List;

import heap.application.user.Roles;

public record AuthUser (
    String userId,
    List<Roles> role
)
{}
