package chi.testtask.phonecontacts.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Contact> contacts = new HashSet<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.setOwner(this);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        contact.setOwner(null);
    }
}
