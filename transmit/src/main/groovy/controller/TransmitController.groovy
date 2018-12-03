package controller


import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import service.AllocationService
import service.TransmitService

import javax.inject.Inject
@Controller('/transmit')
class TransmitController {
    @Inject
    TransmitService transmitService

    @Inject
    AllocationService allocationService

    @Post('/')
    @Status(HttpStatus.CREATED)
    String send(@Body Map input) {
        'i just want to return this string'
    }
}
