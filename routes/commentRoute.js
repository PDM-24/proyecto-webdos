const express = require("express");
const router = express.Router();

const {
    createCommentValidator,
    saveCommentValidator,
    idInParamsValidator,
    saveReplyValidator
} = require("../validators/commentValidator");
const validateFields = require("../validators/indexMiddleware");

const commentController = require("../controllers/commentController");

const { authentication } = require("../middlewares/authMiddleware");

// Obtener todos los comentarios
router.get('/comments',
    commentController.findAll);

// Obtener comentarios por usuario
router.get('/userComments/:identifier',
    idInParamsValidator,
    validateFields,
    commentController.findByUser);

// Obtener un comentario por su ID
router.get('/commentsById/:id',
    idInParamsValidator,
    validateFields,
    commentController.findOneById);

// Crear o actualizar un comentario
router.post(["/", "/:identifier"],
    authentication,
    createCommentValidator,
    validateFields,
    commentController.save);

// Eliminar un comentario
router.delete('/deleteComment/:id',
    authentication,
    idInParamsValidator,
    validateFields,
    commentController.deleteComment);

// Guardar una respuesta a un comentario
router.post('/comments/:identifier/reply',
    authentication,
    saveReplyValidator,
    validateFields,
    commentController.saveReply);

// Ruta para dar like a un comentario
router.patch('/comments/Like/:identifier',
    authentication,
    idInParamsValidator,
    validateFields,
    commentController.likeComment);

module.exports = router;
