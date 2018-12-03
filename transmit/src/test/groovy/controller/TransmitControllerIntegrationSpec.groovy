package controller

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

@MicronautTest
class TransmitControllerIntegrationSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)


    @Shared
    @AutoCleanup
    HttpClient client = HttpClient.create(embeddedServer.URL)


    void "an HTTP call should be made to /transmit"() {
        given: "a map"

        def map = [
            country  : "de",
            zip      : "10623",
            firstName: "first",
            lastName : "last",
            phone    : "1234567",
            jobTitle : "tester",
            cohort   : "test"
        ]


        when:
        HttpRequest request = HttpRequest.create(HttpMethod.POST, '/transmit')
                                         .accept(MediaType.APPLICATION_JSON_TYPE)
                                         .body(map)

        HttpResponse rsp = client.toBlocking().exchange(request)

        then:
        rsp.status.code == 201
        rsp.body.isPresent()
    }
}
