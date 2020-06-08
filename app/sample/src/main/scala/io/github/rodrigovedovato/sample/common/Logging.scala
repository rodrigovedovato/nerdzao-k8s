package io.github.rodrigovedovato.sample.common

import org.slf4j.{Logger, LoggerFactory}

trait Logging {
  private val logger: Logger = LoggerFactory.getLogger(classOf[Logging])

  def info = logger.info(_: String)
  def error = logger.error(_: String, _: Throwable)
  def warn = logger.warn(_: String)
}