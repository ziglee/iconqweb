package iconqweb

import grails.converters.JSON

class InstituicaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [instituicaoInstanceList: Instituicao.list(params), instituicaoInstanceTotal: Instituicao.count()]
    }

    def create = {
        def instituicaoInstance = new Instituicao()
        instituicaoInstance.properties = params
        return [instituicaoInstance: instituicaoInstance]
    }

    def save = {
        def instituicaoInstance = new Instituicao(params)
        if (instituicaoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), instituicaoInstance.sigla])}"
            redirect(action: "show", id: instituicaoInstance.id)
        } else {
            render(view: "create", model: [instituicaoInstance: instituicaoInstance])
        }
    }

    def show = {
        def instituicaoInstance = Instituicao.get(params.id)
        if (!instituicaoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])}"
            redirect(action: "list")
        } else {
            [instituicaoInstance: instituicaoInstance]
        }
    }

    def edit = {
        def instituicaoInstance = Instituicao.get(params.id)
        if (!instituicaoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])}"
            redirect(action: "list")
        } else {
            return [instituicaoInstance: instituicaoInstance]
        }
    }

    def update = {
        def instituicaoInstance = Instituicao.get(params.id)
        if (instituicaoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (instituicaoInstance.version > version) {
                    instituicaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'instituicao.label', default: 'Instituicao')] as Object[], "Another user has updated this Instituicao while you were editing")
                    render(view: "edit", model: [instituicaoInstance: instituicaoInstance])
                    return
                }
            }
            instituicaoInstance.properties = params
            if (!instituicaoInstance.hasErrors() && instituicaoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), instituicaoInstance.sigla])}"
                redirect(action: "show", id: instituicaoInstance.id)
            } else {
                render(view: "edit", model: [instituicaoInstance: instituicaoInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def instituicaoInstance = Instituicao.get(params.id)
        if (instituicaoInstance) {
            try {
                instituicaoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])}"
                redirect(action: "list")
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])}"
            redirect(action: "list")
        }
    }

    def listJson = {
        def instituicoes = Instituicao.listOrderBySigla()
        render instituicoes.collect{ row ->
            [id: row.id, sigla: row.sigla, nome: row.nome]
        } as JSON
    }

    def showJson = {
        def obj = Instituicao.get(params.id)
        def result = [id: obj.id, sigla: obj.sigla, nome: obj.nome, telefone: obj.telefone, site: obj.site]
        render result as JSON
    }
}
