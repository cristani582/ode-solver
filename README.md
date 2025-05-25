# Java ODE Solver

A simple Java application to solve ordinary differential equations (ODEs) and systems of ODEs using numerical methods like Euler and Runge-Kutta 4th order (RK4).

---
## 🚀 Features
- ✅ Support for solving systems of ordinary differential equations (ODEs) with multiple variables (e.g., 𝑥(𝑡), 𝑦(𝑡), etc.).
- ✅ Flexible structure: you create a class that defines the system of equations to be solved.
- ✅ Ability to set initial conditions for all variables in the system.
- ✅ Numerical methods implemented: Euler and 4th-order Runge-Kutta (RK4).
- ✅ Easy to modify initial conditions and parameters to explore different system behaviors.
- ✅ Simple and educational Java codebase, ideal for learning and prototyping numerical methods.
---
## 📘 Example: Solving a System of ODEs
- This project can also solve **systems of ordinary differential equations (ODEs)** where multiple dependent variables vary with respect to an independent variable (usually time \( t \)).

## For example, consider the system:
    dx/dt = y(t)
    dy/dt = -x(t)

## with initial conditions:
     x(0) = 1
     y(0) = 0

- and a step size value for solving. The program approximates solutions for both 𝑥(𝑡) and 𝑦(𝑡) simultaneously over the defined time range, using both Euler and Runge-Kutta 4th order (RK4) methods.
    
---

## 🧠 Ideal for:

- Students learning numerical methods for coupled ODE systems
- Engineers and developers prototyping mathematical models
- Anyone wanting to understand how to solve multi-variable differential systems in practice

---

## 🛠️ Methods Implemented

- Euler’s Method (for systems)
- Runge-Kutta Method 4th Order (RK4) (for systems)

---
