const express = require("express");
const router = express.Router();

const { createCommentValidator, idInParamsValidator, saveCommentValidator } = require("../validators/commentValidator");
const validateFields = require("../validators/indexMiddleware");

const commentController = require("../controllers/commentController");

const { authentication, authorization } = require("../middlewares/authMiddleware")

// Ruta para obtener todos los comentarios
router.get('/comments',
    commentController.findAll);
router.get('/own',
    authentication,
    commentController.findOwn);
//  Ruta para obterner los comentarios por usuario 
router.get('/userComments/:identifier',
    idInParamsValidator,
    validateFields,
    commentController.findByUser);
// Ruta para obtener comentarios de un usuario por su ID
router.get('/commentsById/:id',
    validateFields,
    idInParamsValidator,
    commentController.findOneById);
// Ruta para crear un comentario
router.post(["/", "/:identifier"],
    authentication,
    createCommentValidator,
    validateFields,
    commentController.save);

router.patch("/Like/:identifier", 
authentication, 
idInParamsValidator, 
validateFields, 
commentController.meGustaAComment); 

router.patch("/comment/:identifier", 
authentication,  
idInParamsValidator, 
saveCommentValidator, 
validateFields, 
commentController.saveComment)
// Ruta para eliminar un comentario
router.delete('/deleteComment/:id',
    authentication,
    validateFields,
    idInParamsValidator,
    commentController.deleteComment);

module.exports = router; 