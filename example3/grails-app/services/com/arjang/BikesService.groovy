package com.arjang

import grails.gorm.services.Service

@Service(Bikes)
interface BikesService {

    Bikes get(Serializable id)

    List<Bikes> list(Map args)

    Long count()

    void delete(Serializable id)

    Bikes save(Bikes bikes)

}