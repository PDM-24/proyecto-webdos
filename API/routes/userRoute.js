const { Router } = require('express');
const router = Router();

const userController = require("../controllers/userController");
const runValidation = require("../validators/indexMiddleware");
const { createUserValidator } = require('../validators/userValidator');

// Ruta para crear un nuevo usuario
router.post('/newUser', createUserValidator, runValidation, userController.createUser);

// Ruta para obtener un usuario por ID
router.get('/getUser/:id', userController.getUser); // <-- Corregido el nombre del controlador

module.exports = router;
