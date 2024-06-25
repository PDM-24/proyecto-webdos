const { body } = require('express-validator');

const validators = {};

validators.createUserValidator = [
    body('firstName')
        .notEmpty().withMessage('First name is required.')
        .isLength({ max: 100 }).withMessage('Maximum length of first name is 100 characters.'),
    body('lastName')
        .notEmpty().withMessage('Last name is required.')
        .isLength({ max: 100 }).withMessage('Maximum length of last name is 100 characters.'),
    body('email')
        .notEmpty().withMessage('Email is required.')
        .isEmail().withMessage('Invalid email format.')
        .normalizeEmail(),
    body('password')
        .notEmpty().withMessage('Password is required.')
        .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)
        .withMessage('Password must contain at least one lowercase letter, one uppercase letter, one number, one special character, and be at least 8 characters long.'),
    body('confirmPassword')
        .notEmpty().withMessage('Confirm Password is required.')
        .custom((value, { req }) => {
            if (value !== req.body.password) {
                throw new Error('Passwords do not match');
            }
            return true;
        }),
];

module.exports = validators;
