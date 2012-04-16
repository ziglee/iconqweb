package iconqweb

public enum SituacaoConcurso {

    ABERTO("Aberto",0),
    FECHADO("Fechado",1)

    final String nome
    final int id

    SituacaoConcurso (String nome, int id){
        this.nome = nome
        this.id = id
    }

    String toString(){
        nome
    }
}