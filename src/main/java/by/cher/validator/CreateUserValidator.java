package by.cher.validator;


import by.cher.dto.CreateUserDto;
import by.cher.entity.Gender;
import by.cher.entity.Role;
import by.cher.utils.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    public ValidationResult isValid(CreateUserDto userDto) {
        var validationResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(userDto.getBirthday())) {
            validationResult.add(Error.of("invalid birthday format", "birthday"));
        }

        if (Gender.find(userDto.getGender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        if (Role.find(userDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }

        return validationResult;

    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
