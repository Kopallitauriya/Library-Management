import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

trait App {
  def init(): Unit = {}

  def main(args: Array[String]): Unit = {
    init()

    implicit val system: ActorSystem = ActorSystem("LibrarySystem")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    val config = ConfigFactory.load()
    val host = config.getString("http.host")
    val port = config.getInt("http.port")

    val routes = LibraryRouter.routes(new LibraryRepository())

    val bindingFuture = Http().bindAndHandle(routes, host, port)
    println(s"Server online at http://$host:$port/")
  }
}
