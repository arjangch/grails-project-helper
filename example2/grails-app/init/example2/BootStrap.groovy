package example2

import jakarta.servlet.ServletContext
import com.arjang.Employee
import com.arjang.Students

class BootStrap {

    ServletContext servletContext

    def init = {
        new Employee(name: "joe", lastname: "london", empoyeeId: 12345, employeeNotes: "good guy").save()
        new Employee(name: "Jim", lastname: "Paris", empoyeeId: 444, employeeNotes: "he is tall").save()
        new Students(name: "jill", lastname: "green", studentId: 1212).save()
        new Students(name: "Pam", lastname: "king", studentId: 3322).save()
    }

    def destroy = {
    }

}
