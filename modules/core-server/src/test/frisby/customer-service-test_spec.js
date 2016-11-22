var frisby = require('frisby');
var base_url = "http://localhost:2222/pizza-shefu/api/v1.0";

// Test register customer-valid data.
frisby.create('Register customer test 01-valid data')
    .post(base_url + '/customers/register/', {
        "title": "Mr", "firstName": "frisby", "lastName": "test", "username": "frisby123",
        "password": "password", "email": "frisby@kdm.com", "addressL1": "123",
        "addressL2": "abc", "mobile": "123456789"
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
        "message": "user added to the database",
        "data": [
            {
                "firstName": "frisby",
                "lastName": "test",
                "Email": "frisby@kdm.com",
                "AddressLine02": "abc",
                "mobile": "123456789",
                "AddressLine01": "123",
                "title": "Mr",
                "username": "frisby123"
            }
        ],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/register/"
            },
            {
                "rel": "profile",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/frisby123"
            }
        ]
    })
    .toss();

// Test register customer-different http method.
frisby.create('Register customer test 02-different http method')
    .get(base_url + '/customers/register/', {
        "title": "Mr", "firstName": "frisby", "lastName": "test", "username": "frisby123",
        "password": "password", "email": "frisby@kdm.com", "addressL1": "123",
        "addressL2": "abc", "mobile": "123456789"
    }, {json: true})
    .expectStatus(405)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 405,
        "description": "wrong http method used. use the proper http method as described in the documentation.",
        errorMessage: "HTTP_METHOD_NOT_ALLOWED",
        "status": "error"
    })
    .toss();

// Test customer login-correct data.
frisby.create('Customer login test 01-correct data')
    .post(base_url + '/customers/login/', {
        "username": "frisby123",
        "password": "password"
    }, {json: true})
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
        "message": "username, password validated.",
        "data": [
            {
                "username": "frisby123"
            }
        ],
        "links": [
            {
                "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/login/"
            },
            {
                "rel": "profile",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/frisby123"
            }
        ]
    })
    .toss();

// Test customer login-wrong username.
frisby.create('Customer login test 02-wrong username')
    .post(base_url + '/customers/login/', {
        "username": "frisby12",
        "password": "password"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "errorMessage": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test customer login-wrong password.
frisby.create('Customer login test 03-wrong password')
    .post(base_url + '/customers/login/', {
        "username": "frisby123",
        "password": "passwor"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "errorMessage": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test customer login-with spaces.
frisby.create('Customer login test 04-with spaces')
    .post(base_url + '/customers/login/', {
        "username": " frisby123",
        "password": "password"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "errorMessage": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test customer login-with spaces.
frisby.create('Customer login test 05-with spaces')
    .post(base_url + '/customers/login/', {
        "username": "frisby123 ",
        "password": "password"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "errorMessage": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test customer login-mixed case username.
frisby.create('Customer login test 06-mixed case username')
    .post(base_url + '/customers/login/', {
        "username": "friSby123",
        "password": "password"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "errorMessage": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test customer login-mixed case password.
frisby.create('Customer login test 07-mixed case password')
    .post(base_url + '/customers/login/', {
        "username": "friSby123",
        "password": "pasSword"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "errorMessage": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test customer login-empty password.
frisby.create('Customer login test 08-empty password')
    .post(base_url + '/customers/login/', {
        "username": "frisby123"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "errorMessage": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test customer login-empty username.
frisby.create('Customer login test 09-empty password')
    .post(base_url + '/customers/login/', {
        "password": "password"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 401,
        "description": "credentials provided are not authorized.",
        "errorMessage": "HTTP_UNAUTHORIZED",
        "status": "unauthorized"
    })
    .toss();

// Test customer login-With different http method.
frisby.create('Customer login test 10-wrong http method')
    .get(base_url + '/customers/login/', {
        "username": "frisby123",
        "password": "password"
    }, {json: true})
    .expectStatus(405)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 405,
        "description": "wrong http method used. use the proper http method as described in the documentation.",
        "errorMessage": "HTTP_METHOD_NOT_ALLOWED",
        "status": "error"
    })
    .toss();

// Test getting the profile of a customer-valid data.
frisby.create('Getting customer profile test 01-existing username')
    .get(base_url + '/customers/frisby123',
    {
        headers: {'Content-Type': 'application/json'}
    })
    .expectStatus(200)
    .inspectJSON()
    .expectHeader('content-Type', 'application/json')
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
        "message": "customer data retrieved",
        "data": [
            {
                "firstName": "frisby",
                "lastName": "test",
                "mobile": "123456789",
                "addressLine02": "abc",
                "addressLine01": "123",
                "title": "Mr",
                "email": "frisby@kdm.com",
                "username": "frisby123"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/frisby123",
                "rel": "self"
            }
        ]
    })
    .toss();

// Test getting the profile of a customer-valid data.
frisby.create('Getting customer profile test 02-non-existing username')
    .get(base_url + '/customers/frisby12',
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
        "message": "no customer data to retrieve",
        "data": [],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/frisby12",
                "rel": "self"
            }
        ]
    })
    .toss();

// Test getting the profile of a customer-valid data.
frisby.create('Getting customer profile test 03-username case')
    .get(base_url + '/customers/Frisby123',
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
        "message": "no customer data to retrieve",
        "data": [],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/Frisby123",
                "rel": "self"
            }
        ]
    })
    .toss();

// Test customer login-blocked username.
frisby.create('Customer login test-blocked user')
    .post(base_url + '/customers/login/', {
        "username": "testre",
        "password": "password"
    }, {json: true})
    .expectStatus(403)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "code": 403,
        "description": "user has no permissions or a blocked user",
        "errorMessage": "HTTP_FORBIDDEN",
        "status": "forbidden"
    })
    .toss();

// Test customer change password 01-success.
frisby.create('Customer change password 01-success')
    .put(base_url + '/customers/password', {
        "username": "frisby123",
        "password": "password",
        "newPassword": "password"
    }, {json: true})
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
        "message": "customer password updated successfully",
        "data": [
            {
                "username": "frisby123"
            }
        ],
        "links": [
            {
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/password",
                "rel": "self"
            }
        ]
    })
    .toss();

// Test customer-change password with a correct username but wrong old password.
frisby.create('Customer change password 02-correct username but wrong old username')
    .put(base_url + '/customers/password', {
        "username": "frisby123",
        "password": "password2",
        "newPassword": "password"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "status": "unauthorized",
        "code": 401,
        "errorMessage": "HTTP_UNAUTHORIZED",
        "description": "credentials provided are not authorized."
    })
    .toss();

// Test customer-change password with a correct old password but wrong username.
frisby.create('Customer change password 03-correct old password but wrong username')
    .put(base_url + '/customers/password', {
        "username": "frisby12",
        "password": "password",
        "newPassword": "password"
    }, {json: true})
    .expectStatus(401)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        description: String,
        errorMessage: String,
        status: String
    })
    .expectJSON({
        "status": "unauthorized",
        "code": 401,
        "errorMessage": "HTTP_UNAUTHORIZED",
        "description": "credentials provided are not authorized."
    })
    .toss();