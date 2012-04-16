package iconqweb

class DocumentoController {

    def grailsApplication

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [documentoInstanceList: Documento.list(params), documentoInstanceTotal: Documento.count()]
    }

    def create = {
        def documentoInstance = new Documento()
        documentoInstance.properties = params
        return [documentoInstance: documentoInstance]
    }

    def save = {
        def documentoInstance = new Documento(params)
        if (documentoInstance.save(flush: true)) {

            def folder = new File(grailsApplication.config.grails.pastaDocumentos)
            if (!folder.exists())
                folder.mkdirs()

            if (params.file) {
                def folderId = new File(folder, Long.toString(documentoInstance.id))

                def file = request.getFile('file')
                if(!file.empty) {
                    if (!folderId.exists())
                        folderId.mkdirs()

                    documentoInstance.nomeArquivo = file.getOriginalFilename()
                    documentoInstance.contentType = file.getContentType()
                    documentoInstance.path = UUID.randomUUID().toString()
                    file.transferTo(new File(folderId, documentoInstance.path))
                }
            }

            flash.message = "${message(code: 'default.created.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])}"
            redirect(action: "show", id: documentoInstance.id)
        } else {
            render(view: "create", model: [documentoInstance: documentoInstance])
        }
    }

    def show = {
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
            redirect(action: "list")
        } else {
            [documentoInstance: documentoInstance]
        }
    }

    def edit = {
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
            redirect(action: "list")
        } else {
            return [documentoInstance: documentoInstance]
        }
    }

    def update = {
        def documentoInstance = Documento.get(params.id)
        if (documentoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (documentoInstance.version > version) {
                    documentoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'documento.label', default: 'Documento')] as Object[], "Another user has updated this Documento while you were editing")
                    render(view: "edit", model: [documentoInstance: documentoInstance])
                    return
                }
            }

            documentoInstance.properties = params
            if (!documentoInstance.hasErrors() && documentoInstance.save(flush: true)) {
                def folder = new File(grailsApplication.config.grails.pastaDocumentos)
                if (!folder.exists())
                    folder.mkdirs()

                if (params.file) {
                    def folderId = new File(folder, Long.toString(documentoInstance.id))

                    def file = request.getFile('file')
                    if(!file.empty) {
                        if (!folderId.exists())
                            folderId.mkdirs()

                        documentoInstance.nomeArquivo = file.getOriginalFilename()
                        documentoInstance.contentType = file.getContentType()
                        documentoInstance.path = UUID.randomUUID().toString()
                        file.transferTo(new File(folderId, documentoInstance.path))
                    }
                }

                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])}"
                redirect(action: "show", id: documentoInstance.id)
            } else {
                render(view: "edit", model: [documentoInstance: documentoInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def documentoInstance = Documento.get(params.id)
        if (documentoInstance) {
            try {
                documentoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
                redirect(action: "list")
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
            redirect(action: "list")
        }
    }

    def download = {
        def doc = Documento.get(params.id)
        def file = new File("documentos/${doc.id}/${doc.path}")
        if(file.exists()) {
            response.setHeader("Content-disposition", "attachment; filename=${doc.nomeArquivo}")
            response.contentType = doc.contentType
            response.outputStream << file.newInputStream()
            response.outputStream.flush()
        }
    }
}
