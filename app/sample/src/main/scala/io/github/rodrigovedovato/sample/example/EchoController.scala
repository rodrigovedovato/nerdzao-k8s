package io.github.rodrigovedovato.sample.example

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import io.circe.generic.auto._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport

import io.github.rodrigovedovato.sample.example.model._

class EchoController extends FailFastCirceSupport {
  def echo: Route = pathPrefix("echo") {
    pathEndOrSingleSlash {
      get {
        parameters("c", "reverse".as[Boolean]) { (c, reverse) =>
          if (reverse) {
            complete(Echo(c.reverse))
          } else {
            complete(Echo(c))
          }
        }
      }
    }
  }
}