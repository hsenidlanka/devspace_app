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
                "imagePath": "/a/dummy/image/path/",
                "name": "Thanduri Pizza",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text",
                "type": "non-veg"
            },
            {
                "imagePath": "/a/dummy/image/path/",
                "name": "Cheese Lover",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text",
                "type": "veg"
            },
            {
                "imagePath": "/a/dummy/image/path/",
                "name": "Garlic Bread",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text",
                "type": "carbonate"
            },
            {
                "imagePath": "image/test",
                "name": "Test pizza",
                "description": "test",
                "type": "veg"
            },
            {
                "imagePath": "/image/test02",
                "name": "Test pizza 2",
                "description": "test 2",
                "type": "non-veg"
            },
            {
                "imagePath": "image/ny1",
                "name": "Test NY1",
                "description": "NY1",
                "type": "veg"
            },
            {
                "imagePath": "/image/path4",
                "name": "Test NY2",
                "description": "NY2",
                "type": "non-veg"
            },
            {
                "imagePath": "/image/path2",
                "name": "Test NY3",
                "description": "ny3",
                "type": "veg"
            },
            {
                "imagePath": "/image/path3",
                "name": "Test NY4",
                "description": "ny4",
                "type": "veg"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/category/pizza",
                "rel": "self"
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
    .get(base_url + '/items/subcategory/Ice%20creams',
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
        "message": "items for subcategory Ice creams retrieved",
        "data": [
            {
                "imagePath": "/a/dummy/image/path/",
                "name": "Thanduri Pizza",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text",
                "type": "non-veg"
            },
            {
                "imagePath": "/a/dummy/image/path/",
                "name": "Cheese Lover",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text",
                "type": "veg"
            },
            {
                "imagePath": "/a/dummy/image/path/",
                "name": "Garlic Bread",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text",
                "type": "carbonate"
            },
            {
                "imagePath": "image/test",
                "name": "Test pizza",
                "description": "test",
                "type": "veg"
            },
            {
                "imagePath": "/image/test02",
                "name": "Test pizza 2",
                "description": "test 2",
                "type": "non-veg"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/Ice%20creams",
                "rel": "self"
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