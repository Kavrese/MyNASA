package com.example.mynasa

class AsteroidsDate(
    val near_earth_objects: List<Asteroid>
)

class Asteroid(
    val name: String? = null,
    val id: Int? = null,
    val estimated_diameter: Diameter? = null,
    val close_approach_data: List<FlyDate>? = null
)

class Diameter(
    val kilometers: Dist? = null,
    val meter: Dist? = null
)

class Dist(
    val estimated_diameter_min: String? = null,
    val estimated_diameter_max: String? = null
)

class FlyDate(
    val close_approach_date: String? = null,
    val miss_distance: MissDist? = null
)

class MissDist(
    val kilometers: String? = null
)