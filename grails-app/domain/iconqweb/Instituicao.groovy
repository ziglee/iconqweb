package iconqweb

class Instituicao {

    String sigla
    String nome
    String telefone
    String site

    static constraints = {
        sigla nullable: false, blank: false
        nome nullable: false, blank: false
        telefone nullable: false, blank: false
        site url: true, nullable: false, blank: false
    }

    String toString(){
        nome
    }

    static mapping = {
        sort "sigla"
    }
}
