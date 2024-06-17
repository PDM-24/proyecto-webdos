const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const crypto = require('crypto');

// Define el esquema del usuario
const userSchema = new Schema({
    firstName: {
        type: String,
        trim: true,
        required: true,
    },
    lastName: {
        type: String,
        trim: true,
        required: true,
    },
    email: {
        type: String,
        unique: true,
        lowercase: true,
        trim: true,
        required: true,
    },
    hashedPassword: {
        type: String,
        trim: true,
        required: true,
    },
    salt: {
        type: String,
    },
    tokens: {
        type: [String],
        default: [],
    },
});

// Virtual para el campo de contraseña
userSchema.virtual('password')
    .set(function (password) {
        this._password = password;
        this.salt = this.makeSalt();
        this.hashedPassword = this.encryptPassword(password);
    })
    .get(function () {
        return this._password;
    });

// Pre-save hook para cifrar la contraseña
userSchema.pre('save', function (next) {
    if (this.isModified('password')) {
        this.salt = this.makeSalt();
        this.hashedPassword = this.encryptPassword(this.password);
    }
    next();
});

// Métodos para cifrar y comparar contraseñas
userSchema.methods = {
    encryptPassword: function (password) {
        if (!password || !this.salt) return '';
        try {
            return crypto.pbkdf2Sync(password, this.salt, 1000, 64, 'sha512').toString('hex');
        } catch (error) {
            console.error('Error encrypting password:', error);
            return '';
        }
    },
    makeSalt: function () {
        return crypto.randomBytes(16).toString('hex');
    },
    comparePassword: function (password) {
        return this.hashedPassword === this.encryptPassword(password);
    },
};

// Exportar el modelo
module.exports = mongoose.model('User', userSchema);
