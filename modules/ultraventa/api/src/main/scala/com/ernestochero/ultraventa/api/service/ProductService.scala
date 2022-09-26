package com.ernestochero.ultraventa.api.service

import com.ernestochero.ultraventa.domain.Product
import zio._

trait ProductService {
  def getProducts: UIO[List[Product]]
}
