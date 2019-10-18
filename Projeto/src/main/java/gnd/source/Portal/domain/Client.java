package gnd.source.Portal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor // Possibilita que o construtor receba todas as variaveis como parametros
@Builder
@Data // Realiza as acoes get e set, como, por exemplo, o setStatus(false); do delete() do ClientService
@Entity // Especifica que estamos falando de uma tabela
@NoArgsConstructor // Possibilita que o construtor receba nenhuma variavel como parametro
@Table(name = "clientes")
public class Client extends Object implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // @id e @generatedvalue  sao para identificar que sao chaves primarias é dexar criaçao automatica da chave
    @Column (name = "id", unique = true, nullable = false)
    private Integer id = null;

    @Column (name = "cpf", unique = true, nullable = false)
    private Integer cpf = null;

    @Column (name = "name", length = 150)
    private String name = null;

    @Column (name = "gender", length = 300)
    private String gender = null;

    @Column (name = "status") // deixei isso para caso seja necessario  desativar usuarios
    private Boolean status = true;
}