package mvc2025.kiwfy_pet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "localizadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localizador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String email;  //login feito com email
    
    private String telefone;
    
    private String endereco;

    private String senha;
    
    @Column(length = 500)
    private String observacoes;
}

