const { Router } = require('express');
const router = Router();

const userController = require("../controllers/userController");
const runValidation = require("../validators/indexMiddleware");
const { createUserValidator } = require('../validators/userValidator');

router.post('/newUser', createUserValidator, runValidation, userController.createUser);

router.get('/getUser/:id', userController.findOneById);

module.exports = router;
