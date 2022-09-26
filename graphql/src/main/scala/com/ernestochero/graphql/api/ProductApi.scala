package com.ernestochero.graphql.api

import caliban.{GraphQL, RootResolver}
import caliban.GraphQL.graphQL
import caliban.schema.{GenericSchema, Schema}
import caliban.schema.Annotations.GQLDescription
import caliban.wrappers.ApolloTracing.apolloTracing
import caliban.wrappers.Wrappers._
import com.ernestochero.ultraventa.api.service.ProductService
import com.ernestochero.ultraventa.domain.Product
import com.ernestochero.ultraventa.infrastructure.service.ProductService
import zio._

import scala.language.postfixOps

object ProductApi extends GenericSchema[ProductService] {
  case class Queries(
    @GQLDescription("Return all products")
    products: () => RIO[ProductService, List[Product]]
  )
  implicit val productSchema: Schema[Any, Product] = Schema.gen
  val api: GraphQL[ProductService] =
    graphQL(
      RootResolver(
        Queries(() => ProductService.getProducts)
      )
    ) @@
      maxFields(200) @@               // query analyzer that limit query fields
      maxDepth(30) @@                 // query analyzer that limit query depth
      timeout(3 seconds) @@           // wrapper that fails slow queries
      printSlowQueries(500 millis) @@ // wrapper that logs slow queries
      printErrors @@                  // wrapper that logs errors
      apolloTracing                   // wrapper for https://github.com/apollographql/apollo-tracing
}
