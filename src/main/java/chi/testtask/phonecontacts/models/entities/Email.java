package chi.testtask.phonecontacts.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "all_app_emails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contact parentContact;
}
