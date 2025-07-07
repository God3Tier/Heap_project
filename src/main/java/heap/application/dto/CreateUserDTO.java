package heap.application.dto;

import java.util.List;

public record CreateUserDTO( 
    String username,
    String password,
    List<String> roles
)
{}
