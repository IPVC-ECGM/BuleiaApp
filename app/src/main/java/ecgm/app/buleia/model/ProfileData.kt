package ecgm.app.buleia.model

class ProfileData {
    var comt : String? = null
    var fromID : String? = null
    var toID : String? = null
    var fromIMG : String? = null
    constructor() {}

    constructor(
        comt: String?,
        fromID: String?,
        toID: String?,
        fromIMG: String?

    ){
        this.comt = comt
        this.fromID = fromID
        this.toID = toID
        this.fromIMG = fromIMG

    }
}