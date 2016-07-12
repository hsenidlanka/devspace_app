var frisby = require('frisby');

frisby.create('Add user test')
    .post('http://localhost:2222/devspace/api/v1.0/users/add/test/123/')
    .expectStatus(201)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        data: {},
        message: String,
        status: String
    })
    .expectJSON({
        code: 201,
        data: {
            password: '123',
            username: 'test'
        },
        message: "user added",
        status: "success"
    })
    .toss();

frisby.create('Delete user test')
    .delete('http://localhost:2222/devspace/api/v1.0/users/delete/test')
    .expectStatus(200)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        data: {},
        message: String,
        status: String
    })
    .expectJSON({
        code: 200,
        data: {
            "username": "test"
        },
        message: "user deleted",
        status: "success"
    })
    .toss();