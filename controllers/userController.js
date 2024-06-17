const { validationResult } = require('express-validator');
const User = require('../models/userModel');
const { createUserValidator } = require('../validators/userValidator'); 

const userController = {};

userController.createUser = async (req, res) => {
    try {
        // Ejecutar los validadores
        await Promise.all(createUserValidator.map(validation => validation.run(req)));

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }

        const { firstName, lastName, email, password } = req.body;

        // Generar el username a partir del email
        const username = email.split('@')[0];

        const newUser = new User({
            firstName,
            lastName,
            email,
            password,
            username
        });

        try {
            const savedUser = await newUser.save();
            return res.status(201).json({ ok: true, user: savedUser });
        } catch (error) {
            console.error('Error saving user:', error);
            return res.status(500).json({ ok: false, error: 'Error creating user' });
        }
    } catch (error) {
        console.error(error);
        return res.status(500).json({ ok: false, error: 'Error creating user' });
    }
};

userController.getUser = async (req, res) => {
    try {
        const { id } = req.params;
        const user = await User.findById(id).select('-hashedPassword -salt');
        if (!user) {
            return res.status(404).json({ ok: false, error: 'User not found' });
        }
        return res.status(200).json({ ok: true, user });
    } catch (error) {
        console.error(error);
        return res.status(500).json({ ok: false, error: 'Error fetching user' });
    }
};

module.exports = userController;
