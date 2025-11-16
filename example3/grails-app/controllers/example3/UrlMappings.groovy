package example3

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/testSpringTags"(view:"/testSpringTags")
        "/alive"(view: "/alive")
        "/loginError"(view: "/loginError")
        "500"(view:'/error')
        "403"(view:'/forbidden')
        "404"(view:'/notFound')
        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")
    }
}
