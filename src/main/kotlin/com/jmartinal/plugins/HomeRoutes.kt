package com.jmartinal.plugins

import com.jmartinal.plugins.HomeRouteNames.DESCRIPTION_PATH
import com.jmartinal.plugins.HomeRouteNames.ROOT_PATH
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

object HomeRouteNames {
    const val ROOT_PATH = "home"
    const val DESCRIPTION_PATH = "description"
}

fun Routing.addHomeRoutes() {
    route(ROOT_PATH) {
        get {
            call.respondHtml {
                head { title { +"My notes" } }
                body {
                    h1 { +"Notes" }
                    p { +"This is the home page for My notes" }
                    a(href = "$ROOT_PATH/$DESCRIPTION_PATH") { +"See full description here" }
                }
            }
        }
        get(DESCRIPTION_PATH) {
            call.respondHtml {
                head { title { +"Description" } }
                body {
                    h1 { +"Companion project" }
                    p { +"This app is a companion project made by Jorge Martín Alvarado in order to complete Antonio Levia's Kotlin Expert program." }
                    p { +"By completing this program's 6th module, you will get a working backend server completely written in Kotlin that you can use to manage your own Notes list app." }
                }
            }
        }
    }

}