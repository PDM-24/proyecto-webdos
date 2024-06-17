const { body, param } = require("express-validator");

const validators = {};

validators.createCommentValidator = [
    param("identifier")
        .optional()
        .isMongoId().withMessage("El identificador debe ser un ObjectId válido de MongoDB"),
    body("content")
        .notEmpty().withMessage("El contenido es requerido.")
        .isLength({ max: 280 }).withMessage("La longitud máxima del contenido es de 280 caracteres.")
];

validators.saveCommentValidator = [
    param("identifier")
        .optional()
        .isMongoId().withMessage("El identificador debe ser un ObjectId válido de MongoDB"),
    body("content")
        .notEmpty().withMessage("El contenido es requerido.")
        .isLength({ max: 280 }).withMessage("La longitud máxima del contenido es de 280 caracteres.")
];

validators.idInParamsValidator = [
    param("identifier")
        .optional()
        .isMongoId().withMessage("El identificador debe ser un ObjectId válido de MongoDB")
];

validators.saveReplyValidator = [
    param("identifier")
        .exists().withMessage("El identificador del comentario es requerido.")
        .isMongoId().withMessage("El identificador debe ser un ObjectId válido de MongoDB"),
    body("content")
        .notEmpty().withMessage("El contenido de la respuesta es requerido.")
        .isLength({ max: 280 }).withMessage("La longitud máxima del contenido es de 280 caracteres."),
    body("replyId")
        .optional()
        .isMongoId().withMessage("El replyId debe ser un ObjectId válido de MongoDB")
];

module.exports = validators;
