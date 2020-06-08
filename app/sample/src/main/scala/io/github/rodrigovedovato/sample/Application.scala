package io.github.rodrigovedovato.sample

import scala.util.{Failure, Success}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{RejectionHandler, Route}

import io.github.rodrigovedovato.sample.common.{Logging, ServiceConfiguration}
import io.github.rodrigovedovato.sample.common.akkahttp.Directives._
import io.github.rodrigovedovato.sample.example.EchoController

object Application extends App with Logging {

  implicit val system: ActorSystem = ActorSystem("sample")
  implicit val executionContext: ExecutionContext= system.dispatcher

  val echoController = new EchoController()

  val healthCheck = pathPrefix("ping") {
    pathEndOrSingleSlash {
      get {
        complete("pong")
      }
    }
  }

  val routes = handleExceptions(exceptionHandler(error)) {
    handleEmptyResponses {
      echoController.echo
    }
  }

  val serverBinding = Http().bindAndHandle(routes ~ healthCheck, "0.0.0.0", ServiceConfiguration.servicePort)

  serverBinding.onComplete {
    case Success(bound) =>
      info(s"Server listening on port ${bound.localAddress.getPort}")
    case Failure(e) =>
      error(s"Server could not start!", e)
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
}