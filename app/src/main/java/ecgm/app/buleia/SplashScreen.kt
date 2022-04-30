package ecgm.app.buleia

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        //Esconder Barra do menu
        supportActionBar?.hide()
        @Suppress("DEPRECATION")
        //Pegar nas dimensões do ecrâ
        //Criar metricas do ecrâ
        val dm = DisplayMetrics()
        //buscar medidas do ecrâ utilizado
        windowManager.defaultDisplay.getMetrics(dm)
        //Gravar os valores nas constantes da classe constantes
        Constantes.Screen_Width = dm.widthPixels
        Constantes.Screen_Height = dm.heightPixels

        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, LoginActivity::class.java)
            startActivity(intent)
        },0)
    }
}