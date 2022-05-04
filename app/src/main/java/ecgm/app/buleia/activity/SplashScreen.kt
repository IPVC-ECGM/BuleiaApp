package ecgm.app.buleia.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import ecgm.app.buleia.Constantes
import ecgm.app.buleia.R


class SplashScreen : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        //Esconder Barra do menu
        supportActionBar?.hide()

        //Pegar nas dimensões do ecrâ
        //Criar metricas do ecrâ
        //buscar medidas do ecrâ utilizado

        val displayMetrics: DisplayMetrics = getResources().getDisplayMetrics()
        //Gravar os valores nas constantes da classe constantes
        Constantes.Screen_Width = displayMetrics.widthPixels
        Constantes.Screen_Width = displayMetrics.heightPixels



        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            val intent = Intent(this@SplashScreen, LoginActivity::class.java)
            startActivity(intent)
        }, 0)
    }
}