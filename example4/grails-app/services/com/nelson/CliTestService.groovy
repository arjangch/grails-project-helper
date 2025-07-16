package com.nelson

import grails.gorm.services.Service

@Service(CliTest)
interface CliTestService {

    CliTest get(Serializable id)

    List<CliTest> list(Map args)

    Long count()

    void delete(Serializable id)

    CliTest save(CliTest cliTest)

}