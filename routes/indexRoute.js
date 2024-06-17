const express = require('express');
const router = express.Router();

const commentRouter = require('./commentRoute');
const userRouter = require('./userRoute');
const authRouter = require('./authRoute');

router.use('/comment', commentRouter);
router.use('/user', userRouter);
router.use('/auth', authRouter);

module.exports = router;
