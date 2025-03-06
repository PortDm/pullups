package demon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "touch")
public class Touch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
