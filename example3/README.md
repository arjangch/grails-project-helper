# Example 3
This example uses Spring Security plugins Core, Core UI and REST.

# Create REST API
In this example we want to work with data in Json or Xml format. There are many ways of doing this 
- We can output domain as JSON or XML. This will output all fields
- We can render some fields as JSON or XML. 
- We can POST new data into our app. 


## Bugs 
1. Grails-doc, bug in render example https://github.com/apache/grails-core/issues/15061. See controller https://github.com/arjangch/grails-project-helper/blob/main/example3/grails-app/controllers/com/arjang/StudentController.groovy and method called showRenderJsonBad()
2. grails-spring-security does not generate domain class for Requestmap https://github.com/apache/grails-spring-security/issues/1172


