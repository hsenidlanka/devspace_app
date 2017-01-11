/**
 * Created by Kasun Dinesh on 10/3/16.
 */
var frisby = require('frisby');
var base_url = "http://localhost:2222/pizza-shefu/api/v1.0";

// Test get all categories-correct.
frisby.create('Category service test 01-correct url')
    .get(base_url + '/categories/list',
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
                "description": "Pizza is a flatbread generally topped with tomato sauce and cheese and baked in an oven. It is commonly topped with a selection of meats, vegetables and condiments."
            },
            {
                "name": "Beverage",
                "description": "Freshly cooked pasta with prawns, vegetables & sliced mushrooms baked with a white cream sauce and mozzarella cheese"
            },
            {
                "name": "Desserts",
                "description": "Freshly cooked pasta with prawns, vegetables & sliced mushrooms baked with a white cream sauce and mozzarella cheese"
            },
            {
                "name": "Salad",
                "description": "Freshly cooked pasta with prawns, vegetables & sliced mushrooms baked with a white cream sauce and mozzarella cheese"
            },
            {
                "name": "IceCream",
                "description": "Ice Cream is of different flavours ."
            },
            {
                "name": "Pasta2",
                "description": "There are different types of Pasta2"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/categories/list",
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
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Desserts",
                "rel": "subcategories of Desserts"
            },
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Salad",
                "rel": "subcategories of Salad"
            },
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/IceCream",
                "rel": "subcategories of IceCream"
            },
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/subcategories/category/Pasta2",
                "rel": "subcategories of Pasta2"
            }
        ]
    })
    .toss();

// Test get all categories-wrong method.
frisby.create('Category service test 02-wrong method')
    .post(base_url + '/categories/list',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .expectStatus(405)
    .inspectJSON()
    .expectJSONTypes({
        status: String,
        code: String,
        errorMessage: String,
        description: String
    })
    .expectJSON({
        "status": "error",
        "code": "405",
        "errorMessage": "HTTP_METHOD_NOT_ALLOWED",
        "description": "wrong http method used. use the proper http method as described in the documentation."
    })
    .toss();