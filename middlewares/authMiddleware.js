const debug = require('debug')('app:auth-middleware');
const { verifyToken } = require('../utils/jwtTools');
const User = require('../models/userModel');

const middlewares = {};
const PREFIX = 'Bearer';

middlewares.authentication = async (req, res, next) => {
    try {
        debug('User authentication');

        const { authorization } = req.headers;

        if (!authorization) {
            return res.status(401).json({ error: 'unauthenticated user' });
        }

        const [prefix, token] = authorization.split(' ');

        if (prefix !== PREFIX) {
            return res.status(401).json({ error: 'unauthenticated user' });
        }

        if (!token) {
            return res.status(401).json({ error: 'unauthenticated user' });
        }

        const payload = await verifyToken(token);
        if (!payload) {
            return res.status(401).json({ error: 'unauthenticated user' });
        }

        const userId = payload.sub;

        const user = await User.findById(userId);

        if (!user) {
            return res.status(401).json({ error: 'unauthenticated user' });
        }

        const isTokenValid = user.tokens.includes(token);
        if (!isTokenValid) {
            return res.status(401).json({ error: 'unauthenticated user' });
        }

        req.user = user;
        req.token = token;

        console.log('Token:', token);
        console.log('Decoded Payload:', payload);

        next();
    } catch (error) {
        console.error(error);
        return res.status(500).json({ ok: false, error: error.message || 'Internal Server Error' });
    }
};

middlewares.authorization = () => {
    return (req, res, next) => {
        try {
            next();
        } catch (error) {
            console.error(error);
            res.status(500).json({ ok: false, error: error.message || 'Internal Server Error' });
        }
    };
};

module.exports = middlewares;