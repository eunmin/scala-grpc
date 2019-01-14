package com.example

import java.util.logging.Logger

import com.example.protos.hello.GreeterGrpc
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext

object Main extends App {
  val config = ConfigFactory.load
  val logger =  Logger.getLogger(classOf[GrpcServer].getName)
  val context = ExecutionContext.global
  val service = GreeterGrpc.bindService(new GreeterImpl, context)
  val server = new GrpcServer(config, service, logger)

  server.start()
  server.blockUntilShutdown()
}
