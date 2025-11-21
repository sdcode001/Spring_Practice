<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #0F1111;
            color: white;
            padding: 15px;
            font-size: 32px;
            font-weight: bold;
            text-align: left;
        } 
        .container {
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
        }
        .form-container {
            flex: 1 1 auto;
            max-width: 400px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .form-container h2 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #0F1111;
            color: white;
            padding: 10px 15px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #232f3e;
        }
    </style>
</head>
<body>
    <div class="header">Spring Store</div>
    <div class="container">
        <div class="form-container">
            <h2>Create New Book</h2>
            <form action="book" method="post">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="authors">Authors</label>
                    <input type="text" id="authors" name="authors" required>
                </div>
                <div class="form-group">
                    <label for="amazonRating">Amazon Rating</label>
                    <input type="text" id="amazonRating" name="amazonRating">
                </div>
                <div class="form-group">
                    <label for="imageUrl">Image URL</label>
                    <input type="text" id="imageUrl" name="imageUrl" required>
                </div>
                <input type="submit" value="Create Book">
            </form>
        </div>
    </div>    
</body>
</html>
