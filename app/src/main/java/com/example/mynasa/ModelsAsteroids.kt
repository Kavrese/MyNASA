package com.example.mynasa

import android.util.ArrayMap
import com.google.gson.JsonElement
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class AsteroidsDate(
    val links: Any? = null,
    val element_count: Int? = null,
    val near_earth_objects: Map<String, List<Asteroid>>? = null
)

class Asteroid(
    val name: String? = null,
    val id: Int? = null,
    val nasa_jpl_url: String? = null,
    val estimated_diameter: Diameter? = null,
    val close_approach_data: List<FlyDate>? = null
)

class Diameter(
    val meters: Dist? = null
)

class Dist(
    val estimated_diameter_max: String? = null
)

class FlyDate(
    val close_approach_date: String? = null,
    val miss_distance: MissDist? = null
)

class MissDist(
    val kilometers: String? = null
)