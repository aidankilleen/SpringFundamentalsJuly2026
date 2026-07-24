package ie.pt.springbootwebexploration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserAccountDto(
        Long id,

        @NotBlank(message="Name is required")
        @Size(
                min=2,
                max=20,
                message="Name must be between 2 and 20 characters"
        )
        String name,

        @NotBlank(message="Email is required")
        @Email(message="Enter a valid email")
        String email,

        @NotBlank(message="Password is required")
        String password,

        @NotBlank(message="Confirm Password is required")
        String confirmPassword) {

    // record creates a pojo
    // all args constructor
    // gets for each property
    // no sets
    // .equals()
    // .toString()
}
