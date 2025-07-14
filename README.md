# Simple Calculator Android App

This is my **first Android application** built using **Java**. It is a simple calculator app that performs basic binary arithmetic operations.

---

## Features

- Supports five basic operations between two numbers:
  - Addition (`+`)
  - Subtraction (`-`)
  - Multiplication (`*`)
  - Division (`/`)
  - Exponentiation (`^`)
- Input validation with user-friendly messages
- Handles special cases such as:
  - Division by zero
  - Indeterminate forms like 0^0 and 0/0
- Displays result on the screen clearly
- Double-click on the equal button updates the first number input with the last result for convenience
- Long press on the equal button clears inputs and result

---

## File Overview

- **MainActivity.java**  
  Contains the main activity logic for handling user input, performing calculations, and updating the UI.

- **activity_main.xml**  
  Defines the UI layout including EditTexts for input, TextView for displaying results, and Buttons for operations.

---

## Notes

- This project uses `LinearLayout` for UI arrangement.
- Inputs accept decimal numbers only.
- The app is designed for learning and practice purposes.
- You can extend the app by adding more operations, memory functions, or a more sophisticated UI.

---

*Thank you for checking out my first Android app!*
