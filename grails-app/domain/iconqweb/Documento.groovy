package iconqweb

class Documento {

    String nome
    String nomeArquivo
    String path
    String contentType
    Concurso concurso

    static constraints = {
        concurso nullable: false
        nome nullable: false, blank: false
        path nullable: true
        nomeArquivo nullable: true
        contentType nullable: true
    }

    String toString(){
        nome
    }
}
