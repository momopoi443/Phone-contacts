package chi.testtask.phonecontacts.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contacts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(
            mappedBy = "parentContact",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Email> emails = new HashSet<>();

    public void addEmail(Email email) {
        emails.add(email);
        email.setParentContact(this);
    }

    public void removeEmail(Email email) {
        emails.remove(email);
        email.setParentContact(null);
    }

    @OneToMany(
            mappedBy = "parentContact",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();

    public void addPhoneNumber(PhoneNumber email) {
        phoneNumbers.add(email);
        email.setParentContact(this);
    }

    public void removePhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.remove(phoneNumber);
        phoneNumber.setParentContact(null);
    }
}
