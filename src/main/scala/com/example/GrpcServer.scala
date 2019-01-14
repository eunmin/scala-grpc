package com.example

import com.typesafe.config.Config
import io.grpc.{Server, ServerBuilder, ServerServiceDefinition}
import java.util.logging.Logger

class GrpcServer(config: Config, service: ServerServiceDefinition, logger: Logger) { self =>
  private var server: Server = null

  def start(): Unit = {
    val port = config.getInt("server.port")

    server = ServerBuilder.forPort(port).addService(service).build.start
    logger.info("Server started, listening on " + port)
    sys.addShutdownHook {
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      self.stop()
      System.err.println("*** server shut down")
    }
  }

  def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }
}
