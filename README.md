# 🏆 CTF Game: XSS Challenge 🏆

Welcome to the XSS Challenge! This is a Capture The Flag (CTF) game designed to test your understanding of Cross-Site Scripting (XSS) vulnerabilities. Your mission, should you choose to accept it, is to leverage an XSS flaw to retrieve a hidden flag.

---

## 🚀 Getting Started (via Docker)

To run this CTF challenge, you'll need:

* **Docker Desktop** (or Docker Engine) installed and running on your system.
* A **web browser** (Chrome, Firefox, Edge, etc.).

**Steps to Run:**

1.  **Clone this repository:**
    ```bash
    git clone https://github.com/Avichai98/CTF_GAME.git
    ```
    (Replace `<your-repo-url>` and `<your-repo-name>` with your actual GitHub repository details).

2.  **Build the Docker image:**
    Navigate to the root directory of the cloned repository (where `Dockerfile` is located) and run:
    ```bash
    docker build -t xss-challenge .
    ```
    This command builds the Docker image named `xss-challenge`. It will also embed the hidden flag and secret key within the container's environment.

3.  **Run the Docker container:**
    ```bash
    docker run -p 8080:8080 xss-challenge
    ```
    This command starts the container and maps port 8080 of your local machine to port 8080 inside the container.

4.  **Access the application:**
    Open your web browser and go to:
    ```
    http://localhost:8080
    ```

---

## 🎯 The Challenge: Find the Flag!

This web application appears to be a simple message board. You can submit messages, and they will be displayed on the page. However, there's a critical vulnerability hidden within!

**Your Goal:**

Find a way to execute arbitrary JavaScript code within the context of this web page to uncover the secret flag.

**Hints for the Challenge:**

* **Input Handling:** Pay close attention to how your input is processed and rendered on the page. This is where the XSS vulnerability lies.
* **Browser's Best Friend:** Your web browser's developer tools (usually accessible by pressing `F12` or `Ctrl+Shift+I`) are indispensable. Use them to inspect the HTML, monitor network activity, and check the console output.
* **Flag Location:** The flag is **not** located in the client-side code (HTML, JavaScript). It resides securely on the server.
* **Activation:** To retrieve the flag, you'll need to figure out how to make the browser perform a specific action that triggers the server to reveal it. This action typically involves **sending a specific request to a server endpoint, possibly with a required key or identifier.** Look for clues hidden within the application's frontend code or accessible resources.

---

## 🔑 Flag Format

The flag is in the standard CTF format: `AfekaCTF{YOUR_FLAG_VALUE}`.

---

Good luck, and happy hacking!

---