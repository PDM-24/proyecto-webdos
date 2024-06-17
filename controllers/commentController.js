const { ObjectId } = require('mongoose').Types;
const Comment = require('../models/commentModel');
const debug = require('debug')('app:comment-controller');

const controller = {};

controller.findAll = async (req, res) => {
    try {
        const comments = await Comment.find({ hidden: false })
            .populate("user", "username email")
            .populate("likes", "username email")
            .populate("replies.user", "username email");

        res.status(200).json({ comments });
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: 'Internal server error' });
    }
};

controller.findOneById = async (req, res) => {
    try {
        const id = req.params.id;
        const comment = await Comment.findOne({ _id: id, hidden: false })
            .populate("user", "username email")
            .populate("likes", "username email")
            .populate("replies.user", "username email");

        if (!comment) {
            return res.status(404).json({ ok: false, error: 'Comment not found' });
        }

        res.status(200).json({ ok: true, comment });
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: 'Internal server error' });
    }
};

controller.findByUser = async (req, res) => {
    try {
        const identifier = req.params.identifier;
        const comments = await Comment.find({ user: identifier, hidden: false })
            .populate("user", "username email")
            .populate("likes", "username email")
            .populate("replies.user", "username email");

        return res.status(200).json({ comments });
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: 'Internal server error' });
    }
};

controller.findOwn = async (req, res) => {
    try {
        const userId = req.user._id;
        const comments = await Comment.find({ user: userId })
            .populate("user", "username email")
            .populate("likes", "username email")
            .populate("replies.user", "username email");

        return res.status(200).json({ comments });
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: 'Internal server error' });
    }
};

controller.save = async (req, res) => {
    try {
        const { content } = req.body;
        const { identifier } = req.params;
        const { user } = req;

        let comment;

        if (identifier) {
            comment = await Comment.findById(identifier);

            if (!comment || !comment.user.equals(user._id)) {
                return res.status(403).json({ error: 'This is not your comment' });
            }
        } else {
            comment = new Comment();
            comment.user = user._id;
        }

        comment.content = content;

        const savedComment = await comment.save();
        if (!savedComment) {
            return res.status(409).json({ error: 'Error creating/commenting' });
        }

        return res.status(201).json(savedComment);
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: error.message || 'Internal server error' });
    }
};

controller.likeComment = async (req, res) => {
    try {
        const { identifier } = req.params;
        const user = req.user;

        const comment = await Comment.findOne({ _id: identifier, hidden: false })
            .populate("user", "username email")
            .populate("replies.user", "username email");

        if (!comment) {
            return res.status(404).json({ error: "Comment not found" });
        }

        let likes = comment.likes || [];
        const alreadyLiked = likes.findIndex(like => like.equals(user._id)) >= 0;

        if (alreadyLiked) {
            likes = likes.filter(like => !like.equals(user._id));
        } else {
            likes = [user._id, ...likes];
        }

        comment.likes = likes;

        const updatedComment = await comment.save();

        return res.status(200).json(updatedComment);
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: 'Internal server error.' });
    }
};

controller.deleteComment = async (req, res) => {
    try {
        const { id } = req.params;
        const user = req.user;

        const deletedComment = await Comment.findOneAndDelete({ _id: id });

        if (!deletedComment) {
            return res.status(404).json({ error: 'Comment not found.' });
        }

        return res.status(200).json({ message: 'Comment deleted successfully.' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: 'Internal server error.' });
    }
};

controller.saveReply = async (req, res) => {
    try {
        const { identifier } = req.params;
        const { content, replyId } = req.body;
        const user = req.user;

        const comment = await Comment.findById(identifier)
            .populate("user", "username email")
            .populate("likes", "username email");

        if (!comment) {
            return res.status(404).json({ error: "Comment not found" });
        }

        if (replyId) {
            const reply = comment.replies.id(replyId);

            if (!reply) {
                return res.status(404).json({ error: "Reply not found" });
            }

            reply.content = content;
        } else {
            comment.replies.push({
                user: user._id,
                content: content,
            });
        }

        const savedComment = await comment.save();

        return res.status(200).json({ comment: savedComment });
    } catch (error) {
        console.error(error);
        res.status(500).json({ ok: false, error: 'Internal server error.' });
    }
};

module.exports = controller;
