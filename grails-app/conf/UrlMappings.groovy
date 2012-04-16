class UrlMappings {

	static mappings = {
        "/api/v1/instituicao/list" (controller: "instituicao", action: "listJson")
        "/api/v1/instituicao/show/$slug" (controller: "instituicao", action: "showJson")

        "/api/v1/concurso/list" (controller: "concurso", action: "listJson")
        "/api/v1/concurso/show/$id" (controller: "concurso", action: "showJson")

        "/api/v1/estado/list" (controller: "estado", action: "listJson")

        "/api/v1/documento/download/$id" (controller: "documento", action: "download")

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
