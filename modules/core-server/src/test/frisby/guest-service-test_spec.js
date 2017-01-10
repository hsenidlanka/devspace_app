var frisby = require('frisby');
var base_url = "http://localhost:2222/pizza-shefu/api/v1.0";

// Test add guest user-empty mobile no.
frisby.create('Register guest test 01-empty mobile no')
    .post(base_url + '/guests/add/', {
        "mobile": ""
    }, {json: true})
    .expectStatus(400)
    .inspectJSON()
    .expectJSONTypes({
        status: String,
        code: String,
        errorMessage: String,
        description: String
    })
    .expectJSON({
        "status": "error",
        "code": "400",
        "errorMessage": "HTTP_BAD_REQUEST",
        "description": "incorrect request. make sure the data sent are correct."
    })
    .toss();

// Test add guest user-valid data.
frisby.create('Register guest test 02-valid data')
    .post(base_url + '/guests/add/', {
        "mobile": "0000000000"
    }, {json: true})
    .expectStatus(201)
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
        "code": 201,
        "message": "guest user added to the database",
        "data": [
            {
                "mobileNo": "0000000000"
            }
        ],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/guests/add/"
            }
        ]
    })
    .toss();