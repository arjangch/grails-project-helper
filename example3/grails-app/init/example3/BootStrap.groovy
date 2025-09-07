package example3
import com.arjang.Student
import com.arjang.Teacher
import jakarta.servlet.ServletContext

class BootStrap {

    ServletContext servletContext

    def init = {
        new Student(name: "sName1", lastname: "sLastname1", grade: "A1", studentId: "11").save()
        new Student(name: "sName2", lastname: "sLastname2", grade: "A2", studentId: "22").save()
        new Student(name: "sName3", lastname: "sLastname3", grade: "A3", studentId: "33").save()

        new Teacher(name: "tName1", lastname: "tLastName1", teacherId: 11).save()
        new Teacher(name: "tName2", lastname: "tLastName2", teacherId: 22).save()
        new Teacher(name: "tName3", lastname: "tLastName3", teacherId: 33).save()
    }

    def destroy = {
    }

}
