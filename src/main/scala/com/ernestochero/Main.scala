package com.ernestochero

import caliban.ZHttpAdapter
import com.ernestochero.graphql.api.ProductApi
import com.ernestochero.ultraventa.api.service.ProductService
import com.ernestochero.ultraventa.infrastructure.service.ProductService
import zhttp.http._
import zhttp.service.Server
import zio.{Console, RLayer, Scope, ZIO, ZIOAppDefault}
import zio.stream.ZStream

object Main extends ZIOAppDefault {
  private val graphiql = Http.fromStream(ZStream.fromResource("graphiql.html"))
  val productLayer: RLayer[Scope, ProductService] = ProductService.live
  val app =
    for {
      interpreter <- ProductApi.api.interpreter
      _           <- Server.start(
                       8080,
                       Http.collectHttp[Request] {
                         case _ -> !! / "api" / "graphql" => ZHttpAdapter.makeHttpService(interpreter)
                         case _ -> !! / "graphiql"        => graphiql
                       })
      _           <- Console.printLine("Hello World")
    } yield ()

  override def run: ZIO[Environment with Scope, Throwable, Unit] =
    app.provideSomeLayer(productLayer)
}
