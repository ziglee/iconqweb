package iconqweb

class Concurso {

    String nome
    Date dataDivulgacao
    Date dataInscricaoInicio
    Date dataInscricaoFim
    Instituicao instituicao
    SituacaoConcurso situacao
    Estado estado
    String nivel
    String area
    String detalhes
    Double remuneracaoDe
    Double remuneracaoAte
    Integer vagas

    static hasMany = [documentos: Documento]

    static constraints = {
    }

    String toString(){
        nome
    }
}
