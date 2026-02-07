package com.arjang

import grails.gorm.services.Service

@Service(Suvs)
interface SuvsService {

    Suvs get(Serializable id)

    List<Suvs> list(Map args)

    Long count()

    void delete(Serializable id)

    Suvs save(Suvs suvs)

}