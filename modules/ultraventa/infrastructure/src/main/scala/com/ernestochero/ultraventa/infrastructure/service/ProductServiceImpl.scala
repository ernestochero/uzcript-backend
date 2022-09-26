package com.ernestochero.ultraventa.infrastructure.service

import com.ernestochero.ultraventa.api.service.ProductService
import com.ernestochero.ultraventa.domain.Product
import zio._

object ProductService {
  val live: RLayer[Scope, ProductService] =
    ZLayer.succeed(new ProductServiceImpl)

  def getProducts: RIO[ProductService, List[Product]] =
    ZIO.serviceWithZIO[ProductService](_.getProducts)
}
class ProductServiceImpl extends ProductService {
  override def getProducts: UIO[List[Product]] = ZIO.succeed(List(Product(1, "test")))
}
