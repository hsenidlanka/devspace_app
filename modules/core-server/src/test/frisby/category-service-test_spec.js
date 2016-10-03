/**
 * Created by Kasun Dinesh on 10/3/16.
 */
var frisby = require('frisby');

// Test get all categories-correct.
frisby.create('Category service test 01-correct url')
    .get('http://localhost:2222/pizza-shefu/api/v1.0/categories/getall',
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
        "message": "categories retrieved",
        "data": [
            {
                "name": "Pizza",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown"
            },
            {
                "name": "Beverage",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown"
            },
            {
                "name": "Topping",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown"
            },
            {
                "name": "Desserts",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown"
            },
            {
                "name": "Salad",
                "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/categories/getall",
                "rel": "self"
            },
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Pizza",
                "rel": "subcategories of Pizza"
            },
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Beverage",
                "rel": "subcategories of Beverage"
            },
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Topping",
                "rel": "subcategories of Topping"
            },
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Desserts",
                "rel": "subcategories of Desserts"
            },
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Salad",
                "rel": "subcategories of Salad"
            }
        ]
    })
    .toss();

// Test get all categories-wrong method.
frisby.create('Category service test 01-wrong method')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/categories/getall',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .expectStatus(405)
    .inspectJSON()
    .expectJSONTypes({
        status: String,
        code: Number,
        errorMessage: String,
        description: String
    })
    .expectJSON({
        "status": "error",
        "code": 405,
        "errorMessage": "HTTP_METHOD_NOT_ALLOWED",
        "description": "wrong http method used. use the proper http method as described in the documentation."
    })
    .toss();