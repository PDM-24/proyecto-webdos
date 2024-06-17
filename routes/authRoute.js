const { Router } = require('express');
const router = Router();

const authController = require("../controllers/authController");
const { authentication } = require('../middlewares/authMiddleware');

router.post('/logIn', authController.logIn);

router.get('/whoAmI', authentication, authController.whoAmI);

module.exports = router;