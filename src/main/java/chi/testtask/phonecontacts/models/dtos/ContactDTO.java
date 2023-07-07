package chi.testtask.phonecontacts.models.dtos;

import chi.testtask.phonecontacts.validation.ValidationProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long ownerId;

    @NotNull
    private List<@Email(
            message = "Not valid email address is provided",
            regexp = ValidationProperties.emailRegexp
    ) String> emails;

    @NotNull
    private List<@Pattern(
            message = "Not valid email address is provided",
            regexp = ValidationProperties.phoneNumberRegexp
    ) String> phoneNumbers;
}
