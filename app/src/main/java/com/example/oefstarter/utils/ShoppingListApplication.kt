import android.app.Application
import android.content.Context

class ShoppingListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ShoppingListApplication.appContext = applicationContext
    }

    companion object {

        lateinit  var appContext: Context

    }
}