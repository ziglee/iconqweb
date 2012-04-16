package iconqweb

import grails.converters.JSON

class ConcursoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [concursoInstanceList: Concurso.list(params), concursoInstanceTotal: Concurso.count()]
    }

    def create = {
        def concursoInstance = new Concurso()
        concursoInstance.properties = params
        return [concursoInstance: concursoInstance]
    }

    def save = {
        def concursoInstance = new Concurso(params)
        if (concursoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'concurso.label', default: 'Concurso'), concursoInstance.nome])}"
            redirect(action: "show", id: concursoInstance.id)
        } else {
            render(view: "create", model: [concursoInstance: concursoInstance])
        }
    }

    def show = {
        def concursoInstance = Concurso.get(params.id)
        if (!concursoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'concurso.label', default: 'Concurso'), params.id])}"
            redirect(action: "list")
        } else {
            def documentos = Documento.findAllByConcurso(concursoInstance)
            [concursoInstance: concursoInstance, documentos: documentos]
        }
    }

    def edit = {
        def concursoInstance = Concurso.get(params.id)
        if (!concursoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'concurso.label', default: 'Concurso'), params.id])}"
            redirect(action: "list")
        } else {
            return [concursoInstance: concursoInstance]
        }
    }

    def update = {
        def concursoInstance = Concurso.get(params.id)
        if (concursoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (concursoInstance.version > version) {
                    concursoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'concurso.label', default: 'Concurso')] as Object[], "Another user has updated this Concurso while you were editing")
                    render(view: "edit", model: [concursoInstance: concursoInstance])
                    return
                }
            }
            concursoInstance.properties = params
            if (!concursoInstance.hasErrors() && concursoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'concurso.label', default: 'Concurso'), concursoInstance.nome])}"
                redirect(action: "show", id: concursoInstance.id)
            } else {
                render(view: "edit", model: [concursoInstance: concursoInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'concurso.label', default: 'Concurso'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def concursoInstance = Concurso.get(params.id)
        if (concursoInstance) {
            try {
                concursoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'concurso.label', default: 'Concurso'), params.id])}"
                redirect(action: "list")
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'concurso.label', default: 'Concurso'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'concurso.label', default: 'Concurso'), params.id])}"
            redirect(action: "list")
        }
    }

    def listJson = {
        def rows = Concurso.listOrderByNome()
        render rows.collect{ row ->
            [id: row.id, vagas: row.vagas, instituicao: row.instituicao.sigla, estado: row.estado.sigla,
                show: createLink([controller: 'concurso', action: 'showJson', id: row.id])]
        } as JSON
    }

    def format = 'yyyy-MM-dd';

    def showJson = {
        def obj = Concurso.get(params.id)

        def docJson = Documento.findAllByConcurso(obj).collect {[nome: it.nome, url:
                createLink([controller: 'documento', action: 'download', id:it.id])]}

        def result = [id: obj.id, situacao: obj.situacao.name(), dataInscricaoInicio: obj.dataInscricaoInicio.format(format),
                dataInscricaoFim: obj.dataInscricaoFim.format(format), instituicao: obj.instituicao.sigla,
                vagas: obj.vagas, remuneracaoDe: obj.remuneracaoDe, remuneracaoAte: obj.remuneracaoAte,
                area: obj.area, nivel: obj.nivel, estado: obj.estado.sigla, documentos: docJson]
        render result as JSON
    }
}
