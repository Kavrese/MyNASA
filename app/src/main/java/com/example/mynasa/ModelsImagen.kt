package com.example.mynasa

class ModelCollection(
    var collection: Collection? = null,
    var links: List<Link>? = null
)

class Collection (
    var items: List<Item>? = null
)

class Item (
    var data: List<Data>? = null,
    var links: List<Link>? = null
)

class Link (
    var render: String? = null,
    var href: String? = null
)

class Data (
    var nasa_id: String? = null,
    var date_created: String? = null,
    var title: String? = null,
    var description: String? = null
)


