const express = require('express');
const router = express.Router();

const commentRouter = require('./commentRoute');
const userRouter = require('./userRoute');
const authRouter = require('./authRoute');

router.use('/comments', commentRouter);
router.use('/users', userRouter); 
router.use('/auth', authRouter);

module.exports = router;
