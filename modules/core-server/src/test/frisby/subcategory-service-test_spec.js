/**
 * Created by Kasun Dinesh on 10/12/16.
 */
var frisby = require('frisby');
var base_url = "http://localhost:2222/pizza-shefu/api/v1.0";

// Test get all the subcategories-correct.
frisby.create('Get all sub categories test 01-correct url')
    .get(base_url + '/subcategories/list',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .expectStatus(200)
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
        "message": "sub-categories retrieved",
        "data": [
            {
                "name": "Ice creams",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text"
            },
            {
                "name": "Chicken",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text"
            },
            {
                "name": "Italian",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text"
            },
            {
                "name": "Soft Drinks",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text"
            },
            {
                "name": "Alcohol",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text"
            }
        ],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/list"
            },
            {
                "rel": "Ice creams items",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/Ice%20creams"
            },
            {
                "rel": "Chicken items",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/Chicken"
            },
            {
                "rel": "Italian items",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/Italian"
            },
            {
                "rel": "Soft Drinks items",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/Soft%20Drinks"
            },
            {
                "rel": "Alcohol items",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/Alcohol"
            }
        ]
    })
    .toss();

// Test get all the subcategories of a category-correct.
frisby.create('Get all subcategories of a categories test 01-correct url')
    .get(base_url + '/subcategories/category/Pizza',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .expectStatus(200)
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
        "message": "sub-categories for Pizza retrieved",
        "data": [
            {
                "name": "Ice creams"
            }
        ],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Pizza"
            },
            {
                "rel": "items of Ice creams",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/items/subcategory/Ice%20creams"
            }
        ]
    })
    .toss();

// Test get all the subcategories of a category-non existing category.
frisby.create('Get all subcategories of a categories test 02-non existing category')
    .get(base_url + '/subcategories/category/Pizz',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .expectStatus(200)
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
        "message": "no sub-categories to retrieve",
        "data": [],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Pizz"
            }
        ]
    })
    .toss();