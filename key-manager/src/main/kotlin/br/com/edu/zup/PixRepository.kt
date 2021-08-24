package br.com.edu.zup

import br.com.edu.zup.pix.Pix
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface PixRepository : JpaRepository<Pix, Long> {
}