package io.github.rodrigovedovato.sample.common

import com.typesafe.config.ConfigFactory

object ServiceConfiguration {
  private lazy val conf = ConfigFactory.load()

  def servicePort: Int = conf.getInt("service.port")
}