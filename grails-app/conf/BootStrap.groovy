import java.text.Normalizer
import grails.util.GrailsUtil
import iconqweb.Estado
import iconqweb.Instituicao

class BootStrap {

    def init = { servletContext ->

        String.metaClass.slug { ->
            def s = delegate.toLowerCase()
            s = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").trim()
            s = s.replaceAll(/[^a-z0-9\s-]/, "").replaceAll(/\s+/, " ").trim()
            if (s.length() > 45) {
                s = s.substring(0, 45).trim()
            }
            s.replaceAll(/\s/, "-")
        }

        switch(GrailsUtil.environment){
            case "development":
                initData();
                break;
            case "test":
                break
            case "production":
                break
        }
    }

    def destroy = {
    }

    def initData() {
        if (Estado.list().size() == 0) {
            new Estado(sigla: 'GO', nome: 'Goiás').save(flush: true, failOnError: true)
            new Estado(sigla: 'RJ', nome: 'Rio de Janeiro').save(flush: true, failOnError: true)

            new Instituicao(sigla: 'DETRAN-GO', nome: 'Departamento de Trânsito de Goiás', telefone: '(62) 3333-2222', site: 'http://www.detran.go.gov').save(flush: true, failOnError: true)
            new Instituicao(sigla: 'UFG', nome: 'Universidade Federal de Goiás', telefone: '(62) 6261-9172', site: 'http://www.ufg.br').save(flush: true, failOnError: true)
        }
    }
}
