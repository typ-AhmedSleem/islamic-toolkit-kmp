package com.typ.islamic_toolkit_kmp.core.location

/**
 * Location Class.
 *
 * The `Location` class is an open class that serves as a base class for representing a location.
 * It provides a foundation for defining specific types of locations with additional properties and functionality.
 *
 * The class can be subclassed to create more specialized location types, such as GPS coordinates, addresses,
 * or custom location representations. Developers can extend this class to add specific behavior and data to suit
 * their application's needs.
 *
 * NOTE that this class can be extended to match your need.
 *
 * @constructor Creates an instance of the `Location` class.
 */
open class Location(
    /**
     * Code of country this location belongs to
     */
    open val code: String,
    /**
     * Latitude of this location
     */
    open val latitude: Double,
    /**
     * Longitude of this location
     */
    open val longitude: Double,
    /**
     * Timezone of this location
     */
    open val timezone: Double
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Location) return false

        if (code != other.code) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        return timezone == other.timezone
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        result = 31 * result + timezone.hashCode()
        return result
    }

    override fun toString(): String {
        return buildString {
            append("Location(code='")
            append(code)
            append("', latitude=")
            append(latitude)
            append(", longitude=")
            append(longitude)
            append(", timezone=")
            append(timezone)
            append(")")
        }
    }

}
