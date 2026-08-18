package api.aprovacao.despesas.Infrastructure.Persistence;

import api.aprovacao.despesas.Domain.StatusDespesa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DespesaJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String motivoRejeicao;

   @Enumerated(EnumType.STRING)
    private StatusDespesa statusDespesa;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioJpaEntity solicitante;
}
