const User = require('../models/userModel');

const userController = {};

userController.createUser = async (req, res) => {
    try {
        const { firstName, lastName, email, password } = req.body;

        // Crear nuevo usuario
        const newUser = new User({ firstName, lastName, email, password });

        // Guardar el usuario en la base de datos
        await newUser.save();

        res.status(201).json({ ok: true, user: newUser });
    } catch (error) {
        console.error('Error creating user:', error);
        res.status(500).json({ ok: false, error: error.message || 'Internal Server Error' });
    }
};

userController.findOneById = async (req, res) => {
    try {
        const user = await User.findById(req.params.id);
        if (!user) {
            return res.status(404).json({ error: "User not found" });
        }
        res.status(200).json({ ok: true, user });
    } catch (error) {
        console.error('Error finding user by ID:', error);
        res.status(500).json({ ok: false, error: error.message || 'Internal Server Error' });
    }
};

module.exports = userController;
