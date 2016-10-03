/**
 * Created by Kasun Dinesh on 10/3/16.
 */
var frisby = require('frisby');

// Test get all the packages-correct.
frisby.create('Package service test 01-correct url')
    .get('http://localhost:2222/pizza-shefu/api/v1.0/packages/getall',
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
        "message": "packages retrieved",
        "data": [
            {
                "PackageName": "Christmas",
                "price": "414.00",
                "imagePath": "/a/dummy/image/path/",
                "content": "dummy content"
            },
            {
                "PackageName": "New Year",
                "price": "658.00",
                "imagePath": "/a/dummy/image/path/",
                "content": "dummy content"
            },
            {
                "PackageName": "Halloween",
                "price": "154.22",
                "imagePath": "/a/dummy/image/path/",
                "content": "dummy content"
            },
            {
                "PackageName": "",
                "price": "564.00",
                "imagePath": "/a/dummy/image/path/",
                "content": "dummy content"
            },
            {
                "PackageName": "Feasty",
                "price": "256.25",
                "imagePath": "/a/dummy/image/path/",
                "content": "dummy content"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/packages/getall",
                "rel": "self"
            }
        ]
    })
    .toss();

// Test get all the packages-wrong method.
frisby.create('Package service test 01-wrong method')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/packages/getall',
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

