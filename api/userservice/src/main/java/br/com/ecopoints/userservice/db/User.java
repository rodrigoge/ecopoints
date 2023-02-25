package br.com.ecopoints.userservice.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Name mustn't be empty")
    @Size(min = 2, max = 250, message = "Name must be between {min} and {max} characters")
    private String name;

    @Column(unique = true)
    @Email(message = "Format invalid for e-mail")
    @NotBlank(message = "E-mail mustn't be empty")
    @Size(min = 2, max = 250, message = "E-mail must be between {min} and {max} characters")
    private String email;

    @Column
    @NotBlank(message = "Password mustn't be empty")
    @Size(min = 3, max = 10, message = "Password must be between {min} and {max} characters")
    private String password;
}
