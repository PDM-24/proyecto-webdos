const express = require('express');
const router = express.Router();
const commentController = require('../controllers/commentController');
const { authentication } = require('../middlewares/authMiddleware');

// Ruta para obtener todos los comentarios
router.get('/', commentController.getAllComments);

// Ruta para crear un nuevo comentario
router.post('/', authentication, commentController.createComment);

// Ruta para obtener un comentario por su ID
router.get('/:id', commentController.getCommentById);

// Ruta para actualizar un comentario por su ID
router.put('/:id', authentication, commentController.updateComment);

// Ruta para eliminar un comentario por su ID
router.delete('/:id', authentication, commentController.deleteComment);

module.exports = router;

module.exports = router;
