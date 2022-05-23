package ecgm.app.buleia.model

data class Buleia(
    val id: Int,
    val rideDay: String,
    val rideTime: String,
    val driveFrom: String,
    val driveTo: String,
    val pick1: String,
    val pick2: String,
    val pick3:String,
    val user:String
)