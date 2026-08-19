package api.aprovacao.despesas.adapters.outboud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "funcionario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FuncionarioJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeFuncionario;

    @Column(nullable = false)
    private String cargo;

    @OneToMany(mappedBy = "despesa")
    private List<DespesaJpaEntity> despesa;

}
