import React, { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [user, setUser] = useState(null);
  const [bio, setBio] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/profile", { withCredentials: true })
      .then((res) => {
        setUser(res.data);
        setBio(res.data.bio || "");
      })
      .catch(() => setUser(null));
  }, []);

  const handleSave = async () => {
    try {
      await axios.post(
        "http://localhost:8080/profile",
        { bio },
        { withCredentials: true }
      );
      setMessage("Profile updated!");
    } catch (err) {
      setMessage("Error updating profile");
    }
  };

  const handleLogout = () => {
    window.location.href = "http://localhost:8080/logout";
  };

  if (!user) {
    return (
      <div style={{ textAlign: "center", marginTop: "100px" }}>
        <h2>Login with:</h2>
        <a href="http://localhost:8080/oauth2/authorization/google">
          <button>Google</button>
        </a>
        <br />
        <br />
        <a href="http://localhost:8080/oauth2/authorization/github">
          <button>GitHub</button>
        </a>
      </div>
    );
  }

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      <h2>Welcome, {user.displayName || user.name}</h2>
      <img src={user.avatarUrl} alt="avatar" width="100" />
      <p>Email: {user.email}</p>

      <textarea
        rows="4"
        cols="50"
        value={bio}
        onChange={(e) => setBio(e.target.value)}
        placeholder="Enter your bio"
      />
      <br />
      <button onClick={handleSave}>Save</button>
      <p>{message}</p>
      <br />
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default App;
