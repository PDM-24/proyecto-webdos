const { body, param } = require("express-validator");
const validators = {};

validators.createCommentValidator = [
    body("comentario")
        .notEmpty().withMessage("Comentario es requerido")
        .isLength({ max: 280 }).withMessage("La longitud máxima del comentario es de 280 caracteres.")
];

validators.saveCommentValidator = [
    body("contenido")
        .notEmpty().withMessage("Contenido es requerido")
        .isLength({ max: 280 }).withMessage("La longitud máxima del contenido es de 280 caracteres."),
    body("_id")
        .optional()
        .notEmpty().withMessage("_id es requerido")
        .isMongoId().withMessage("Identificador tiene que ser Mongo Id")
];

validators.idInParamsValidator = [
    param("identifier")
        .optional()
        .isMongoId().withMessage("Identificador tiene que ser Mongo Id")
];

module.exports = validators;
