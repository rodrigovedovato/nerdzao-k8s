package io.github.rodrigovedovato.sample.common

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

trait AsyncInstrumentation {
  def timedAsyncAction[A](action: => Future[A])(measureCallback: Long => Unit)(implicit ec: ExecutionContext) : Future[A] = {
    val startMillis = System.nanoTime()
    action.andThen {
      case Success(_) => measureCallback(System.nanoTime() - startMillis)
    }
  }
}