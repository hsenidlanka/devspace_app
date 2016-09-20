var frisby = require('frisby');

// Test add user success.
frisby.create('Add user test 01')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/users/add/frisby/123')
    .expectStatus(201)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        data: {},
        links:{},
        message: String,
        status: String
    })
    .expectJSON({
        code: 201,
        "data": [
            {
                "key": "username",
                "value": "frisby"
            },
            {
                "key": "password",
                "value": "123"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/users/add/frisby/123",
                "rel": "self"
            }
        ],
        message: "user added",
        status: "success"
    })
    .toss();

// Test add user with different http method.
frisby.create('Add user test 02')
    .get('http://localhost:2222/pizza-shefu/api/v1.0/users/add/frisby/123')
    .expectStatus(405)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        "error-message": String,
        status: String
    })
    .expectJSON({
        "code": 405,
        "description": "wrong http method used. use the proper http method as described in the documentation.",
        "error-message": "HTTP_METHOD_NOT_ALLOWED",
        "status": "error"
    })
    .toss();

// Test user login success.
frisby.create('User login test 01')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/users/login/frisby/123')
    .expectStatus(200)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        data: {},
        links: {},
        message: String,
        status: String
    })
    .expectJSON({
        code: 200,
        "data": [
            {
                "key": "username",
                "value": "frisby"
            },
            {
                "key": "password",
                "value": "123"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/users/login/frisby/123",
                "rel": "self"
            }
        ],
        message: "username, password validated.",
        status: "success"
    })
    .toss();

// Test user login wrong password.
frisby.create('User login test 02')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/users/login/frisby/12')
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        "error-message": String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "error-message": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test user login wrong username.
frisby.create('User login test 03')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/users/login/frisb/123')
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        "error-message": String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "error-message": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test user login-mixed case user name.
frisby.create('User login test 04')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/users/login/fRiSby/123')
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        "error-message": String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "error-message": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test user login-With spaces.
frisby.create('User login test 05')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/users/login/ frisby/123')
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        "error-message": String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "error-message": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test user login-With spaces.
frisby.create('User login test 06')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/users/login/frisby /123')
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        "error-message": String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "error-message": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test user login-Without password.
frisby.create('User login test 07')
    .post('http://localhost:2222/pizza-shefu/api/v1.0/users/login/frisby/')
    .expectStatus(404)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        "error-message": String,
        status: String
    })
    .expectJSON({
        "code": 404,
        "description": "make sure the url is correct, check the passed parameters and try again.",
        "error-message": "HTTP_NOT_FOUND",
        "status": "error"
    })
    .toss();

// Test user login-With different http method.
frisby.create('User login test 08')
    .get('http://localhost:2222/pizza-shefu/api/v1.0/users/login/frisby/123')
    .expectStatus(405)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        "error-message": String,
        status: String
    })
    .expectJSON({
        "code": 405,
        "description": "wrong http method used. use the proper http method as described in the documentation.",
        "error-message": "HTTP_METHOD_NOT_ALLOWED",
        "status": "error"
    })
    .toss();