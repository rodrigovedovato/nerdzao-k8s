package io.github.rodrigovedovato.sample.example

import org.scalatest.flatspec.AnyFlatSpec
import akka.http.scaladsl.testkit.ScalatestRouteTest

import io.circe.generic.auto._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport

import io.github.rodrigovedovato.sample.example.model.Echo

class EchoControllerSpec extends AnyFlatSpec with ScalatestRouteTest with FailFastCirceSupport {
  val echoController = new EchoController()

  it should "echo correctly" in {
    Get("/echo?c=hello&reverse=false") ~> echoController.echo ~> check {
      assertResult(Echo("hello"))(responseAs[Echo])
    }
  }

  it should "echo in reverse" in {
    Get("/echo?c=hello&reverse=true") ~> echoController.echo ~> check {
      assertResult(Echo("olleh"))(responseAs[Echo])
    }
  }  
}