package ie.pt.springbootwebexploration;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=100)
    private String name;

    @Column(nullable=false, unique=true, length=255)
    private String email;

    @Column(name="password_hash", nullable=false, length=100)
    private String passwordHash;

    @Column(name="created_at", nullable=false)
    private Instant createdAt;

    protected UserAccount() {
    }

    public UserAccount(String name, String email, String passwordHash, Instant createdAd) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAd) {
        this.createdAt = createdAd;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
