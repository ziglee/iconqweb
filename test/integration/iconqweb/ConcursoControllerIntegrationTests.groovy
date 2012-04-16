package iconqweb

import grails.test.GrailsUnitTestCase
import org.junit.BeforeClass
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import groovy.sql.Sql
import iconqweb.test.TestUtils
import org.junit.Test
import grails.converters.JSON

class ConcursoControllerIntegrationTests extends GrailsUnitTestCase {

    def static DATE_FORMAT = 'yyyy-MM-dd'

    @BeforeClass
    public static void massaDados(){
        def config = ConfigurationHolder.config
        def sql = Sql.newInstance(config.dataSource.url, config.dataSource.username,
                config.dataSource.password, config.dataSource.driverClassName)
        TestUtils.apagarBanco(sql)

        def go = new Estado(nome: 'Goiás', sigla: 'GO').save(flush: true, failOnError: true)
        def rj = new Estado(nome: 'Rio de Janeiro', sigla: 'RJ').save(flush: true, failOnError: true)

        def ufg = new Instituicao(nome: 'UFG', sigla: 'UFG', site: 'http://www.ufg.br', telefone: '(62) 9999-1111').save(flush: true, failOnError: true)
        def detran = new Instituicao(nome: 'DETRAN-GO', sigla: 'DETRAN-GO', site: 'http://www.detran.go.gov', telefone: '(62) 8888-2222').save(flush: true, failOnError: true)

        def hoje = new Date()

        new Concurso(nome: 'O Concurso', instituicao: ufg, area: 'Direito', estado: go, nivel: 'Superior',
                situacao: SituacaoConcurso.ABERTO, dataDivulgacao: hoje,
                dataInscricaoInicio: Date.parse(DATE_FORMAT, '2012-01-01'), dataInscricaoFim: Date.parse(DATE_FORMAT, '2012-02-01'),
                remuneracaoDe: 1000, remuneracaoAte: 2000, vagas: 10).save(flush: true, failOnError: true)

        new Concurso(nome: 'O Concurso 2', instituicao: detran, area: 'Direito', estado: go, nivel: 'Médio',
                situacao: SituacaoConcurso.ABERTO, dataDivulgacao: hoje,
                dataInscricaoInicio: Date.parse(DATE_FORMAT, '2012-02-01'), dataInscricaoFim: Date.parse(DATE_FORMAT, '2012-03-01'),
                remuneracaoDe: 2000, remuneracaoAte: 2500, vagas: 10).save(flush: true, failOnError: true)

        new Concurso(nome: 'O Concurso 3', instituicao: detran, area: 'Saúde', estado: rj, nivel: 'Superior e Médio',
                situacao: SituacaoConcurso.ABERTO, dataDivulgacao: hoje,
                dataInscricaoInicio: Date.parse(DATE_FORMAT, '2012-03-01'), dataInscricaoFim: Date.parse(DATE_FORMAT, '2012-04-01'),
                remuneracaoDe: 2500, remuneracaoAte: 3000, vagas: 10).save(flush: true, failOnError: true)

        new Concurso(nome: 'O Concurso 4', instituicao: ufg, area: 'Saúde', estado: rj, nivel: 'Superior e Médio',
                situacao: SituacaoConcurso.ABERTO, dataDivulgacao: hoje,
                dataInscricaoInicio: Date.parse(DATE_FORMAT, '2012-04-01'), dataInscricaoFim: Date.parse(DATE_FORMAT, '2012-05-01'),
                remuneracaoDe: 2500, remuneracaoAte: 3000, vagas: 10).save(flush: true, failOnError: true)
    }

    @Test void show_json() {
        def hoje = new Date()
        def go = Estado.findBySigla('GO')
        def ufg = Instituicao.findBySigla('UFG')
        def conc = new Concurso(nome: 'O Concurso', instituicao: ufg, area: 'Direito', estado: go, nivel: 'Superior',
            situacao: SituacaoConcurso.ABERTO, dataDivulgacao: hoje, dataInscricaoInicio: hoje, dataInscricaoFim: hoje,
            remuneracaoDe: 1000, remuneracaoAte: 2000, vagas: 10).save(flush: true, failOnError: true)

        def controller = new ConcursoController()
        controller.params.id = conc.id
        controller.showJson()

        def json = JSON.parse(controller.response.contentAsString)

        assertEquals 0, json.erro

        def concJson = json.payload
        assertEquals 'UFG', concJson.instituicao
        assertEquals 'Direito', concJson.area
        assertEquals 'GO', concJson.estado
        assertEquals 'Superior', concJson.nivel
        assertEquals SituacaoConcurso.ABERTO.name(), concJson.situacao
        assertEquals 10, concJson.vagas
        assertEquals 1000, concJson.remuneracaoDe
        assertEquals 2000, concJson.remuneracaoAte
    }

    @Test void lista_tudo_json() {
        def controller = new ConcursoController()
        controller.listJson()

        def json = JSON.parse(controller.response.contentAsString)
        println json

        assertEquals 0, json.erro
        assertEquals 4, json.payload.total

        def lista = json.payload.lista

        assertEquals 'UFG', lista.get(0).instituicao
        assertEquals 'RJ', lista.get(0).estado

        assertEquals 'DETRAN-GO', lista.get(1).instituicao
        assertEquals 'RJ', lista.get(1).estado
    }

    @Test void lista_estado_rio_janeiro_json() {
        def controller = new ConcursoController()
        controller.params.estado = 'RJ'
        controller.listJson()

        def json = JSON.parse(controller.response.contentAsString)
        println json

        assertEquals 0, json.erro
        assertEquals 2, json.payload.total

        def lista = json.payload.lista

        assertEquals 'UFG', lista.get(0).instituicao
        assertEquals 'RJ', lista.get(0).estado

        assertEquals 'DETRAN-GO', lista.get(1).instituicao
        assertEquals 'RJ', lista.get(1).estado
    }

    @Test void lista_estado_goias_json() {
        def controller = new ConcursoController()
        controller.params.estado = 'GO'
        controller.listJson()

        def json = JSON.parse(controller.response.contentAsString)
        println json

        assertEquals 0, json.erro
        assertEquals 2, json.payload.total

        def lista = json.payload.lista

        assertEquals 'DETRAN-GO', lista.get(0).instituicao
        assertEquals 'GO', lista.get(0).estado

        assertEquals 'UFG', lista.get(1).instituicao
        assertEquals 'GO', lista.get(1).estado
    }

    @Test void lista_nivel_superior_json() {
        def controller = new ConcursoController()
        controller.params.nivel = 'Superior'
        controller.listJson()

        def json = JSON.parse(controller.response.contentAsString)
        println json

        assertEquals 0, json.erro
        assertEquals 3, json.payload.total

        def lista = json.payload.lista

        assertEquals 'UFG', lista.get(0).instituicao
        assertEquals 'RJ', lista.get(0).estado

        assertEquals 'DETRAN-GO', lista.get(1).instituicao
        assertEquals 'RJ', lista.get(1).estado

        assertEquals 'UFG', lista.get(2).instituicao
        assertEquals 'GO', lista.get(2).estado
    }

    @Test void lista_nivel_medio_json() {
        def controller = new ConcursoController()
        controller.params.nivel = 'Médio'
        controller.listJson()

        def json = JSON.parse(controller.response.contentAsString)
        println json

        assertEquals 0, json.erro
        assertEquals 3, json.payload.total

        def lista = json.payload.lista

        assertEquals 'UFG', lista.get(0).instituicao
        assertEquals 'RJ', lista.get(0).estado

        assertEquals 'DETRAN-GO', lista.get(1).instituicao
        assertEquals 'RJ', lista.get(1).estado

        assertEquals 'DETRAN-GO', lista.get(2).instituicao
        assertEquals 'GO', lista.get(2).estado
    }
}
