package ecgm.app.buleia.`interface`

import ecgm.app.buleia.model.Country

interface FirebaseLoadContry {
    fun onFirebaseLoadSuccess(countryList: List <Country>)
    fun onFirebaseLoadFailed(message: String)
}