package ecgm.app.buleia.model
import android.widget.EditText
import java.sql.Time

class DriverModel(
    var driverWhere: String ?= null,
    var timeDriver: String ?= null,
    var pickUpLocation: String ?= null,
    var numberSeats: String ?= null,
    var driverName: String ?= null,
    var numberBuleia: String ?= null,
    var reviewStar: String ?= null,
    var desProfile: String ?=null,
    var matriculaCarro: String ?= null,
    var statusId: String ?= null,
    var driverStatus: String ?= null,
    var expandableLayout2: Boolean = false
)