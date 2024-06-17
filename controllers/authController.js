const User = require("../models/userModel");
const { createToken, verifyToken } = require("../utils/jwtTools");

const controller = {};

controller.logIn = async (req, res) => {
    try {
        const { email, password } = req.body;

        const user = await User.findOne({ email });

        if (!user) {
            return res.status(404).json({ error: "User not found" });
        }

        if (!user.comparePassword(password)) {
            return res.status(401).json({ error: "Incorrect password" });
        }

        // Create a token
        const token = await createToken(user._id);

        // Store Token
        let _tokens = [...user.tokens];
        const _verifyPromises = _tokens.map(async (_t) => {
            const status = await verifyToken(_t);
            return status ? _t : null;
        });

        _tokens = (await Promise.all(_verifyPromises))
            .filter(_t => _t)
            .slice(0, 4);

        _tokens = [token, ..._tokens];
        user.tokens = _tokens;

        await user.save();

        return res.status(200).json({ token });
    } catch (error) {
        console.error(error);
        res.status(500).json({
            ok: false,
            error: error.message || 'Internal Server Error',
        });
    }
}

controller.whoAmI = async (req, res) => {
    try {
        const { _id, firstName, lastName, email } = req.user; 
        return res.status(200).json({ _id, firstName, lastName, email });
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: 'Internal Server Error' });
    }
}

module.exports = controller;
