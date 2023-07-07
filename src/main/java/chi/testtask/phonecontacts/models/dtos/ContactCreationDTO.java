package chi.testtask.phonecontacts.models.dtos;

import chi.testtask.phonecontacts.validation.ValidationProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class ContactCreationDTO {

    @NotBlank
    private String name;

    private List<@Email(
            message = "Not valid email address is provided",
            regexp = ValidationProperties.emailRegexp
    ) String> emails;

    private List<@Pattern(
            message = "Not valid email address is provided",
            regexp = ValidationProperties.phoneNumberRegexp
    ) String> phoneNumbers;
}
