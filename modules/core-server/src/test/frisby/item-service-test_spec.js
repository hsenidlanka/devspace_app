var frisby = require('frisby');
var base_url = "http://localhost:2222/pizza-shefu/api/v1.0";

// Test get items of a category-valid data.
frisby.create('Get items of a category test 01-valid data')
    .get(base_url + '/items/category/pizza',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .inspectJSON()
    .expectJSONTypes({
        status: String,
        code: Number,
        message: String,
        data: {},
        links: {}
    })
    .expectJSON({
        "status": "success",
        "code": 200,
        "message": "items for category pizza retrieved",
        "data": [
            {
                "name": "Thanduri Pizza",
                "description": "Some description of Thanduri Pizza",
                "type": "4"
            },
            {
                "name": "Garlic Bread",
                "description": "Garlic Bread-some description is here.",
                "type": "1"
            }
        ],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/category/pizza"
            }
        ]
    })
    .toss();

// Test get items of a category-non existing category.
frisby.create('Get items of a category test 02-non existing data')
    .get(base_url + '/items/category/pizz',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .inspectJSON()
    .expectJSONTypes({
        status: String,
        code: Number,
        message: String,
        data: {},
        links: {}
    })
    .expectJSON({
        "status": "success",
        "code": 200,
        "message": "no items to retrieve",
        "data": [],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/category/pizz",
                "rel": "self"
            }
        ]
    })
    .toss();

// Test get items of a subcategory-valid data.
frisby.create('Get items of a subcategory test 01-valid data')
    .get(base_url + '/items/subcategory/ice%20creams',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .inspectJSON()
    .expectJSONTypes({
        status: String,
        code: Number,
        message: String,
        data: {},
        links: {}
    })
    .expectJSON({
        "status": "success",
        "code": 200,
        "message": "items for subcategory ice creams retrieved",
        "data": [
            {
                "price": [
                    {
                        "size": "large",
                        "price": 1280
                    },
                    {
                        "size": "medium",
                        "price": 890
                    },
                    {
                        "size": "regular",
                        "price": 560
                    }
                ],
                "name": "Thanduri Pizza",
                "description": "Some description of Thanduri Pizza",
                "type": "non-carbonated"
            },
            {
                "price": [
                    {
                        "size": "medium",
                        "price": 390
                    },
                    {
                        "size": "regular",
                        "price": 240
                    }
                ],
                "name": "Garlic Bread",
                "description": "Garlic Bread-some description is here.",
                "type": "veg"
            }
        ],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/ice%20creams"
            }
        ]
    })
    .toss();

// Test get items of a subcategory-non existing data.
frisby.create('Get items of a subcategory test 02-non existing data')
    .get(base_url + '/items/subcategory/Ice%20cream',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .inspectJSON()
    .expectJSONTypes({
        status: String,
        code: Number,
        message: String,
        data: {},
        links: {}
    })
    .expectJSON({
        "status": "success",
        "code": 200,
        "message": "no items to retrieve",
        "data": [],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/Ice%20cream"
            }
        ]
    })
    .toss();