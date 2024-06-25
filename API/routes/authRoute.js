const { Router } = require('express');
const router = Router();

const authController = require("../controllers/authController");
const { authentication } = require('../middlewares/authMiddleware');

// Ruta para iniciar sesión
router.post('/logIn', authController.logIn);

// Ruta para obtener la información del usuario autenticado
router.get('/whoAmI', authentication, authController.whoAmI);

module.exports = router;
