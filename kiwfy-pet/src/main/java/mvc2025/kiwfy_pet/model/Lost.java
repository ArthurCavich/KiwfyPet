package mvc2025.kiwfy_pet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "lost_pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
    
    @Column(nullable = false)
    private LocalDateTime dataPerda;
    
    @Column(nullable = false)
    private String localPerda;
    
    private String descricao;
    
    private Boolean encontrado = false;
    
    private LocalDateTime dataEncontrado;
    
    @Column(length = 500)
    private String observacoes;
}

