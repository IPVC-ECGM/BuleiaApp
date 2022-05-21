package ecgm.app.buleia.Constants

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import ecgm.app.buleia.R

class Constants {
    companion object{
        const val BASE_URL = "https://fcm.googleapis.com"
        const val SERVER_KEY = "AAAAqXYqpMA:APA91bEVaP3o4VgwQZzDd9Rv-Tjsa3F05RLVQHhohtDU21fXMrzU5NNyOR4izskiprAjoW6oq9tf4vAPMgiE_aSf67VPQeQxfhyJWXM4mskIohNPE7pSkpYkL9jMjvDekZ3-jQW5enJG" // get firebase server key from firebase project setting
        const val CONTENT_TYPE = "application/json"
    }
}