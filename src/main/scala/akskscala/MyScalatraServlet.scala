package akskscala

import org.scalatra._
import scalate.ScalateSupport

class MyScalatraServlet extends ScalatraServlet with ScalateSupport {

  before() {
    contentType = "text/html"
  }

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/ssp") {
    ssp("example.ssp", ("message" -> "a ssp example"))
  }

  get("/order") { 
    "あとからマッチする"
  }

  get("/or*") { 
    "さきにマッチする"
  }

  get("/dup") { "絶対に呼ばれない" }

  get("/dup") { "絶対に呼ばれない" }

  get("/dup") { "重複した route がある場合、一番最後に定義されたものだけが有効" } // http://localhost:8080/dup

  notFound {
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound() 
  }

}
