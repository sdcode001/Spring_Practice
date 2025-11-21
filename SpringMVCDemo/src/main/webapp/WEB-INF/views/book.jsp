<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${book.title}</title>
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
        .book-image {
            flex: 0 0 auto;
            padding-right: 30px;
        }
        .book-image img {
            max-width: 250px;
            height: auto;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        .book-details {
            flex: 1 1 auto;
            text-align: left;
        }
        .title {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .authors {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .rating {
            font-size: 14px;
            color: #555;
        }
    </style>
</head>
<body>
    <div class="header">Spring Store</div>
    <div class="container">
        <div class="book-image">
            <img src="${book.imageUrl}" alt="Book Image">
        </div>
        <div class="book-details">
            <div class="title">${book.title}</div>
            <div class="authors">
                By 
                <c:forEach var="author" items="${book.authors}" varStatus="status">
                    ${author}<c:if test="${!status.last}">, </c:if>
                </c:forEach>
            </div>
            <div class="rating">Rating: ${book.amazonRating}</div>
        </div>
    </div>   
    
</body>
</html>
