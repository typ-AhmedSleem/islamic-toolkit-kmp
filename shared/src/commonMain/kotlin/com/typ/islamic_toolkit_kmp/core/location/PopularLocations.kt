package com.typ.islamic_toolkit_kmp.core.location

/**
 * Popular Locations Object.
 *
 * The `PopularLocations` object represents a collection of popular or frequently accessed locations in your application.
 * It provides a centralized location to manage and access a list of pre-defined popular locations.
 * This object operates as a singleton, ensuring that there is only one instance throughout the application.
 *
 * Developers can use this object to retrieve the list of popular locations and
 * use them for various location-related functionalities.
 */
object PopularLocations {

    /**
     * Egypt Object.
     *
     * The `Egypt` object represents a specific location or entity, in this case, the country of Egypt.
     *
     */
    object Egypt {

        /**
         * Cairo Location.
         *
         * The constant `CAIRO` represents the location of Cairo, Egypt.
         * It is an instance of the `Location` class with specific properties for Cairo's location information.
         *
         * @property code The country code of Cairo (EG for Egypt).
         * @property latitude The latitude coordinate of Cairo.
         * @property longitude The longitude coordinate of Cairo.
         * @property timezone The timezone offset of Cairo.
         */
        val CAIRO = Location(
            code = "EG",
            latitude = 30.12367823,
            longitude = 31.25339501,
            timezone = 3.0
        )

        val SOHAG = Location(
            code = "EG",
            latitude = 26.549999,
            longitude = 31.700001,
            timezone = 2.0
        )

        val MERSA_MATRUH = Location(
            code = "EG",
            latitude = 31.354343,
            longitude = 27.2373161,
            timezone = 2.0
        )

        val GIZA = Location(
            code = "EG",
            latitude = 30.013056,
            longitude = 31.208853,
            timezone = 2.0
        )

        val ALEXANDRIA = Location(
            code = "EG",
            latitude = 31.205753,
            longitude = 29.924526,
            timezone = 2.0
        )

        val LUXOR = Location(
            code = "EG",
            latitude = 25.687243,
            longitude = 32.639637,
            timezone = 2.0
        )
    }

    object SaudiArabia {
        val MAKKAH = Location(
            code = "SA",
            latitude = 21.422487,
            longitude = 39.826206,
            timezone = 3.0
        )

        val MEDINA = Location(
            code = "SA",
            latitude = 24.524654,
            longitude = 39.569118,
            timezone = 3.0
        )
    }

    // United Arab Emirates
    object UnitedArabEmirates {
        val DUBAI = Location(
            code = "AE",
            latitude = 25.276987,
            longitude = 55.296249,
            timezone = 4.0
        )
    }

    // Syria
    object Syria {
        val DAMASCUS = Location(
            code = "SY",
            latitude = 33.5138,
            longitude = 36.2765,
            timezone = 3.0
        )
    }

    // Palestine
    object Palestine {
        val JERUSALEM = Location(
            code = "PS",
            latitude = 31.7683,
            longitude = 35.2137,
            timezone = 3.0
        )
    }

    // Iraq
    object Iraq {
        val BAGHDAD = Location(
            code = "IQ",
            latitude = 33.3152,
            longitude = 44.3661,
            timezone = 3.0
        )
    }

    // Morocco
    object Morocco {
        val CASABLANCA = Location(
            code = "MA",
            latitude = 33.5731,
            longitude = -7.5898,
            timezone = 1.0
        )
    }

    // Algeria
    object Algeria {
        val ALGIERS = Location(
            code = "DZ",
            latitude = 36.7538,
            longitude = 3.0588,
            timezone = 1.0
        )
    }

    // Kuwait
    object Kuwait {
        val KUWAIT_CITY = Location(
            code = "KW",
            latitude = 29.3759,
            longitude = 47.9774,
            timezone = 3.0
        )
    }

    // Jordan
    object Jordan {
        val AMMAN = Location(
            code = "JO",
            latitude = 31.9454,
            longitude = 35.9284,
            timezone = 3.0
        )
    }

    // Libya
    object Libya {
        val TRIPOLI = Location(
            code = "LY",
            latitude = 32.8872,
            longitude = 13.1913,
            timezone = 2.0
        )
    }

    // Lebanon
    object Lebanon {
        val BEIRUT = Location(
            code = "LB",
            latitude = 33.8889,
            longitude = 35.4944,
            timezone = 3.0
        )
    }

    // Yemen
    object Yemen {
        val SANA_A = Location(
            code = "YE",
            latitude = 15.3694,
            longitude = 44.1910,
            timezone = 3.0
        )
    }

    // Qatar
    object Qatar {
        val DOHA = Location(
            code = "QA",
            latitude = 25.276987,
            longitude = 51.5183,
            timezone = 3.0
        )
    }

    // Sudan
    object Sudan {
        val KHARTOUM = Location(
            code = "SD",
            latitude = 15.5007,
            longitude = 32.5599,
            timezone = 2.0
        )
    }

}
