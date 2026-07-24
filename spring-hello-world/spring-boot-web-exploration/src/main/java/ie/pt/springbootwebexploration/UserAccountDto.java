package ie.pt.springbootwebexploration;

public record UserAccountDto(
        Long id,
        String name,
        String email,
        String password,
        String confirmPassword) {

    // record creates a pojo
    // all args constructor
    // gets for each property
    // no sets
    // .equals()
    // .toString()
}
