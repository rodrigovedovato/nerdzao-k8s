package io.github.rodrigovedovato.sample.common.akkahttp

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler

import akka.http.scaladsl.server.RouteResult.Complete

import akka.http.scaladsl.model.{HttpResponse, StatusCodes, Uri}

object Directives {
  def handleEmptyResponses = mapRouteResult {
    case Complete(response) if response.entity.isKnownEmpty() => Complete(HttpResponse(StatusCodes.NotFound))
    case unhandled => unhandled
  }

  def exceptionHandler(onError: (String, Throwable) => Unit) = ExceptionHandler {
    case e: Exception =>
	    extractUri { uri =>
				onError(s"Erro ao executar o endpoint $uri. Mensagem: ${e.getMessage()}", e)
				complete(StatusCodes.InternalServerError, "Ops! Ocorreu um erro ao processar o request.")
	    }
  }
}