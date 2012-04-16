package iconqweb

class Estado {

    String sigla
    String nome

    static constraints = {
        sigla nullable: false, blank: false
        nome nullable: false, blank: false
    }

    String toString(){
        nome
    }
}
