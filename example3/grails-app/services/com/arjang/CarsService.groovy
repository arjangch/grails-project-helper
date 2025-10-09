package com.arjang

import grails.gorm.services.Service

@Service(Cars)
interface CarsService {

    Cars get(Serializable id)

    List<Cars> list(Map args)

    Long count()

    void delete(Serializable id)

    Cars save(Cars cars)

}