import com.arjang.UserPasswordEncoderListener
import com.arjang.MySecurityEventListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    mySecurityEventListener(MySecurityEventListener)
}
