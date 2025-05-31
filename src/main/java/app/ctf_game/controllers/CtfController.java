package app.ctf_game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value; // Added for @Value annotation

import java.util.ArrayList;
import java.util.List;

@Controller // Marks this class as a Spring MVC controller
public class CtfController {

    // A list to store messages submitted by users. This is where the XSS vulnerability exists.
    private final List<String> messages = new ArrayList<>();

    // The actual flag for the CTF challenge.
    // This value is injected from an environment variable (e.g., from Dockerfile)
    // to keep it out of the source code.
    @Value("${CTF_FLAG}")
    private String ACTUAL_FLAG;

    // The secret string that the attacker needs to discover and send
    // to retrieve the flag from the server.
    // This value is also injected from an environment variable.
    @Value("${FLAG_SECRET}")
    private String FLAG_SECRET;

    /**
     * Handles requests to the root URL ("/").
     * Redirects users to the /submit page, which is the main entry point for the challenge.
     * @return A redirect string to the /submit endpoint.
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/submit";
    }

    /**
     * Displays the message submission form.
     * This endpoint renders the 'submit.html' Thymeleaf template.
     * @param model The Spring UI Model to pass data (messages) to the view.
     * @return The name of the Thymeleaf template to render (submit.html).
     */
    @GetMapping("/submit")
    public String showSubmitForm(Model model) {
        // Add the list of submitted messages to the model, so they can be displayed on the page.
        model.addAttribute("messages", messages);
        return "submit";
    }

    /**
     * Handles the submission of a new message via a POST request.
     * The submitted message is added to the 'messages' list.
     * IMPORTANT: This endpoint is vulnerable to XSS because the 'message' is not sanitized
     * before being added to the list and subsequently rendered in the HTML using th:utext.
     * @param message The message submitted by the user.
     * @return A redirect string back to the /submit page to show the updated list of messages.
     */
    @PostMapping("/submit")
    public String handleSubmit(@RequestParam String message) {
        messages.add(message); // The XSS vulnerability point: storing user input directly.
        return "redirect:/submit";
    }

    /**
     * This is the server-side endpoint to retrieve the flag.
     * It's intended to be called by the client-side XSS payload.
     * The flag is only returned if the correct 'secret' parameter is provided in the request.
     * @param secret An optional request parameter that must match FLAG_SECRET to get the flag.
     * @return The actual flag if the secret is correct, otherwise an error message.
     */
    @GetMapping("/getFlag")
    @ResponseBody // Indicates that the return value should be bound directly to the web response body.
    public String getFlag(@RequestParam(required = false) String secret) {
        // Check if the provided 'secret' matches the predefined FLAG_SECRET.
        if (FLAG_SECRET.equals(secret)) {
            return ACTUAL_FLAG; // Return the flag if the secret is correct.
        } else {
            // Return an error message if the secret is incorrect or missing.
            return "Invalid secret or no secret provided. Keep digging!";
        }
    }
}