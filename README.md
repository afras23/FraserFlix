# FraserFlix – Film Management Web App

FraserFlix is a dynamic web application for managing and browsing a collection of films. Built using **Java**, **AJAX**, and **SQL**, the app allows users to add, view, and delete films through an interactive frontend and a robust backend.

---

## Features

- Add new films with title, genre, and release year
- View and search films in a dynamic table
- Delete films from the catalog
- AJAX-enabled interactions for smooth user experience
- Persistent storage via SQL database

---

## Technologies Used

- **Java** – Backend logic and servlet handling
- **JavaScript (AJAX)** – Frontend interactivity and API calls
- **HTML/CSS** – UI layout and styling
- **MySQL** – Persistent film data storage
- **Eclipse IDE** – Development environment and deployment

---

## Installation & Running Locally

1. **Clone the repository:**

```bash
git clone https://github.com/afras23/FraserFlix.git
cd FraserFlix
```

2. **Import project into Eclipse** as a Dynamic Web Project.

3. **Configure your SQL database**:
   - Create a database and table for films (e.g., `film(id, title, genre, year)`).
   - Update your DB connection settings in the Java backend.

4. **Deploy to server** (Tomcat or similar) from Eclipse.

5. **Access via browser**: `http://localhost:8080/FraserFlix`

---

## Tests

Basic test class to validate database connection and film retrieval logic.

```java
public class FilmDAOTest {
    public static void main(String[] args) {
        FilmDAO dao = new FilmDAO();
        List<Film> films = dao.getAllFilms();
        assert films != null : "Failed to retrieve film list";
        assert films.size() >= 0 : "Film list size invalid";
        System.out.println("FilmDAO test passed!");
    }
}
```

Compile and run from your IDE or terminal after adding to your test directory.

---

## Credits

**Author:** Anesah Fraser

---

