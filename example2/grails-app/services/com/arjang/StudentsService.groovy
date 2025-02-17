package com.arjang

import grails.gorm.services.Service

@Service(Students)
interface StudentsService {

    Students get(Serializable id)

    List<Students> list(Map args)

    Long count()

    void delete(Serializable id)

    Students save(Students students)

}