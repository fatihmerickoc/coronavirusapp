package com.example.coronavirus

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

     var Lappi =0
     var HUS = 0
     var KantaHäme=0
    var Pirkanmaa = 0
    var EteläKarjala = 0
    var PohjoisPohjanmaa = 0
    var PohjoisSavo =  0
    var VarsinaisSuomi = 0
    var KeskiSuomi = 0
    var PohjoisKarjala = 0
    var EteläPohjanmaa =  0
    var Satakunta = 0
    var Ahvenanmaa = 0
    var EteläSavo = 0
    var Kainuu  = 0
    var KeskiPohjanmaa = 0
    var Vaasa = 0
    var Kymenlaakso = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        getData()



    }

    private fun getData() {
        val url  = "https://w3qa5ydb4l.execute-api.eu-west-1.amazonaws.com/prod/finnishCoronaData"
        val myRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener<JSONObject> {response ->


            var confirmed  =  response?.getJSONArray("confirmed")
            var death  =  response?.getJSONArray("deaths")
            var rescued = response?.getJSONArray("recovered")

         //   var healthCareDistrict =confirmed?.getJSONObject(pos)?.getString("healthCareDistrict")




            for(i in 0..confirmed!!.length()-1){

                    when(confirmed!!.getJSONObject(i).getString("healthCareDistrict")){

                        "Kymenlaakso"->Kymenlaakso++
                        "Lappi"->Lappi++
                        "HUS"->HUS++
                        "Kanta-Häme"->KantaHäme++
                        "Pirkanmaa"->Pirkanmaa++
                        "Etelä-Karjala"->EteläKarjala++
                        "Pohjois-Pohjanmaa"->PohjoisPohjanmaa++
                        "Pohjois-Savo"->PohjoisSavo++
                        "Varsinais-Suomi"->VarsinaisSuomi++
                        "Keski-Suomi"->KeskiSuomi++
                        "Pohjois-Karjala"->PohjoisKarjala++
                        "Etelä-Pohjanmaa"->EteläPohjanmaa++
                        "Satakunta"->Satakunta++
                        "Ahvenanmaa"->Ahvenanmaa++
                        "Etelä-Savo"->EteläSavo++
                        "Kainuu"->Kainuu++
                        "Keski-Pohjanmaa"->KeskiPohjanmaa++
                        "Vaasa"->Vaasa++

                    }
            }











            when(confirmed?.getJSONObject(confirmed?.length()-1) ?.getString("healthCareDistrict")){
                "Lappi"-> manageblinkeffect(lappitext)


                "Kymenlaakso"->manageblinkeffect(kymenlaakso)
                "HUS"->
                    manageblinkeffect(Hustext)

                "Kanta-Häme"->manageblinkeffect(kantaharmetext)

                "Pirkanmaa"->manageblinkeffect(pirkanmaatext)

                "Etelä-Karjala"->manageblinkeffect(pohjoiskarjala)

                "Pohjois-Pohjanmaa"->manageblinkeffect(pohjoispohjanmaa)

                "Pohjois-Savo"->manageblinkeffect(pohjoissavo)

                "Varsinais-Suomi"->manageblinkeffect(varsinaissuomitext)

                "Keski-Suomi"->manageblinkeffect(keskisuomitext)

                "Pohjois-Karjala"->manageblinkeffect(pohjoiskarjala)

                "Etelä-Pohjanmaa"->manageblinkeffect(etelapohjanmaa)

                "Satakunta"->manageblinkeffect(satakuntatext)

                "Ahvenanmaa"->manageblinkeffect(ahvenanmaatext)

                "Etelä-Savo"->manageblinkeffect(etelasavotext)

                "Kainuu"->manageblinkeffect(kantaharmetext)

                "Keski-Pohjanmaa"->manageblinkeffect(etelapohjanmaa)
                "Vaasa"->manageblinkeffect(vaasatext)

            }


           // var date =confirmed?.getJSONObject(confirmed.length()-1)?.getString("date")?.subSequence(0,10)

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val d = Date()
            val dayOfTheWeek: String = sdf.format(d)


            tvOlenler.text = ""+death?.length()
            tvYarali.text =""+confirmed?.length()
            tvKurtarilanlar.text = "" +rescued?.length()

            tvdate.text = "Päivitetty " +dayOfTheWeek



            Hustext.text = HUS.toString()
            kantaharmetext.text = KantaHäme.toString()
            varsinaissuomitext.text = VarsinaisSuomi.toString()
            satakuntatext.text = Satakunta.toString()
            pirkanmaatext.text = Pirkanmaa.toString()
            etelapohjanmaa.text = (EteläPohjanmaa).toString()
            keskisuomitext.text  = KeskiSuomi.toString()
            pohjoiskarjala.text =(PohjoisKarjala + EteläKarjala).toString()
            pohjoissavo.text= PohjoisSavo.toString()
            etelasavotext.text = EteläSavo.toString()
            pohjoispohjanmaa.text = PohjoisPohjanmaa.toString()
            lappitext.text = Lappi.toString()
            ahvenanmaatext.text = Ahvenanmaa.toString()
            kymenlaakso.text = Kymenlaakso.toString()
            vaasatext.text = Vaasa.toString()





        }


            ,Response.ErrorListener {


            })


        MySingleton.getInstance(this)?.addToRequestQueue(myRequest)


    }

    private fun manageblinkeffect(textView: TextView){
        var anim  = ObjectAnimator.ofInt(textView,"backgroundColor",Color.RED)
        anim.setDuration(500)
        anim.setEvaluator(ArgbEvaluator())
        anim.repeatMode = ValueAnimator.REVERSE
        anim.repeatCount = Animation.INFINITE
        anim.start()

    }


}
