const { SignJWT, jwtVerify } = require("jose");

// Secret key should ideally be stored in environment variables
const secret = new TextEncoder().encode(process.env.TOKEN_SECRET || "Super Secret Value");
const expTime = process.env.TOKEN_EXP || "15d"; // Token expiration time, default 15 days

const tools = {};

tools.createToken = async (id) => {
  try {
    return await new SignJWT()
      .setProtectedHeader({ alg: "HS256" }) // Signing algorithm
      .setSubject(id) // Subject of the token (usually user ID)
      .setExpirationTime(expTime) // Token expiration time
      .setIssuedAt() // Time at which the token was issued
      .sign(secret); // Sign the token with the secret key
  } catch (error) {
    console.error("Error creating token:", error);
    throw error; // Propagate error to the caller
  }
};

tools.verifyToken = async (token) => {
  try {
    const { payload } = await jwtVerify(token, secret); // Verify the token
    return payload; // Return the payload if verification is successful
  } catch (error) {
    console.error("Error verifying token:", error);
    return null; // Return null or false on verification failure
  }
};

module.exports = tools;
