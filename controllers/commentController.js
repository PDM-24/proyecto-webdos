const User = require('../models/userModel');

const commentController = {};

// Obtener todos los comentarios
commentController.getAllComments = async (req, res) => {
    try {
        const comments = await Comment.find();
        res.status(200).json(comments);
    } catch (error) {
        res.status(500).json({ error: 'Internal server error' });
    }
};

// Crear un nuevo comentario
commentController.createComment = async (req, res) => {
    const { content, user } = req.body;
    try {
        const comment = new Comment({ content, user });
        await comment.save();
        res.status(201).json(comment);
    } catch (error) {
        res.status(500).json({ error: 'Internal server error' });
    }
};

// Obtener un comentario por su ID
commentController.getCommentById = async (req, res) => {
    const { id } = req.params;
    try {
        const comment = await Comment.findById(id);
        if (!comment) {
            return res.status(404).json({ error: 'Comment not found' });
        }
        res.status(200).json(comment);
    } catch (error) {
        res.status(500).json({ error: 'Internal server error' });
    }
};

// Actualizar un comentario por su ID
commentController.updateComment = async (req, res) => {
    const { id } = req.params;
    const { content, hidden } = req.body;
    try {
        const comment = await Comment.findByIdAndUpdate(id, { content, hidden }, { new: true });
        if (!comment) {
            return res.status(404).json({ error: 'Comment not found' });
        }
        res.status(200).json(comment);
    } catch (error) {
        res.status(500).json({ error: 'Internal server error' });
    }
};

// Eliminar un comentario por su ID
commentController.deleteComment = async (req, res) => {
    const { id } = req.params;
    try {
        const comment = await Comment.findByIdAndDelete(id);
        if (!comment) {
            return res.status(404).json({ error: 'Comment not found' });
        }
        res.status(200).json({ message: 'Comment deleted successfully' });
    } catch (error) {
        res.status(500).json({ error: 'Internal server error' });
    }
};

module.exports = commentController;