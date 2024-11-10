package prontvet.table;

public class PacienteEntity {
    private Integer id;
    private TutorEntity tutor;
    private String nome;
    private String raca;
    private Character sexo;
    private Integer idade;
    private Double peso;
    private String descricao;

    public PacienteEntity() {}

    public PacienteEntity(TutorEntity tutor, String nome, String raca, Character sexo,
                          Integer idade, Double peso, String descricao) {
        this.tutor = tutor;
        this.nome = nome;
        this.raca = raca;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public PacienteEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public TutorEntity getTutor() {
        return tutor;
    }

    public PacienteEntity setTutor(TutorEntity tutor) {
        this.tutor = tutor;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public PacienteEntity setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getRaca() {
        return raca;
    }

    public PacienteEntity setRaca(String raca) {
        this.raca = raca;
        return this;
    }

    public Character getSexo() {
        return sexo;
    }

    public PacienteEntity setSexo(Character sexo) {
        this.sexo = sexo;
        return this;
    }

    public Integer getIdade() {
        return idade;
    }

    public PacienteEntity setIdade(Integer idade) {
        this.idade = idade;
        return this;
    }

    public Double getPeso() {
        return peso;
    }

    public PacienteEntity setPeso(Double peso) {
        this.peso = peso;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public PacienteEntity setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public String toString() {
        return nome;
    }
}
