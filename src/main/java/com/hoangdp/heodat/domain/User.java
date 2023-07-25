package com.hoangdp.heodat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoangdp.heodat.config.Constants;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A user.
 */
@Table("jhi_user")
@Data
@Entity
public class User extends AbstractAuditingEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column("password_hash")
    private String password;

    @Size(max = 50)
    @Column("first_name")
    private String firstName;

    @Size(max = 50)
    @Column("last_name")
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @NotNull
    private boolean activated = false;

    @Size(min = 2, max = 10)
    @Column("lang_key")
    private String langKey;

    @Size(max = 256)
    @Column("image_url")
    private String imageUrl;

    @Size(max = 20)
    @Column("activation_key")
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column("reset_key")
    @JsonIgnore
    private String resetKey;

    @Column("reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @Transient
    private Set<Authority> authorities = new HashSet<>();

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated='" + activated + '\'' +
            ", langKey='" + langKey + '\'' +
            ", activationKey='" + activationKey + '\'' +
            "}";
    }
}
