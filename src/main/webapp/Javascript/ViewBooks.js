function getMediaType(format) {
    switch (format) {
        case "xml":
            return "application/xml";
        case "json":
            return "application/json";
        case "plain":
            return "text/plain";
        default:
            return "*/*"; 
    }
}

function getBooks() {
    var format = document.querySelector('input[name="format"]:checked').value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var contentType = this.getResponseHeader("Content-Type");
            var books;

            if (contentType.includes("application/json")) {
                books = JSON.parse(this.responseText).bookList;
                console.log(books);
            } else if (contentType.includes("application/xml")) {
                // Parse XML response
                var parser = new DOMParser();
                var xmlDoc = parser.parseFromString(this.responseText, "application/xml");
                console.log(xmlDoc);
                var bookNodes = xmlDoc.getElementsByTagName("Book");
                books = [];
				console.log(bookNodes);
                for (var i = 0; i < bookNodes.length; i++) {
                    var bookNode = bookNodes[i];
                    var book = {
                        id: bookNode.getElementsByTagName("id")[0].textContent,
                        title: bookNode.getElementsByTagName("title")[0].textContent,
                        author: bookNode.getElementsByTagName("author")[0].textContent,
                        date: bookNode.getElementsByTagName("date")[0].textContent,
                        genres: bookNode.getElementsByTagName("genres")[0].textContent,
                        characters: bookNode.getElementsByTagName("characters")[0].textContent,
                        synopsis: bookNode.getElementsByTagName("synopsis")[0].textContent
                    };
                    books.push(book);
                }
            } else if (contentType.includes("text/plain")) {
                // Parse plain text response
                var lines = this.responseText.split("\n");
                books = [];
				console.log(lines);
                for (var j = 0; j < lines.length; j++) {
                    var bookData = lines[j].split(",");
                    //var booksData = bookData[0].split(",");
						console.log(bookData);
                        var book = {
                            id: bookData[0],
                            title: bookData[1],
                            author: bookData[2],
                            date: bookData[3],
                            genres: bookData[4],
                            characters: bookData[5],
                            synopsis: bookData[6]
                        };
                        books.push(book);
                    
                }
            }

            var table = document.getElementById("books");
            table.innerHTML = "";
			// Create the table header row
			var headerRow = table.insertRow(0);
			headerRow.innerHTML = "<th><b>Id</b></th><th><b>Title</b></th><th><b>Author</b></th><th><b>Date</b></th><th><b>Genres</b></th><th><b>Characters</b></th><th><b>Synopsis</b></th><th><b>Update</b></th><th><b>Delete</b></th>";
			
			
                for (var k = 0; k < books.length; k++) {
                var book = books[k];
                var row = table.insertRow(k + 1);

                var idCell = row.insertCell(0);
                var titleCell = row.insertCell(1);
                var authorCell = row.insertCell(2);
                var dateCell = row.insertCell(3);
                var genresCell = row.insertCell(4);
                var charactersCell = row.insertCell(5);
                var synopsisCell = row.insertCell(6);
                var updateCell = row.insertCell(7);
  				var deleteCell = row.insertCell(8);

                idCell.innerHTML = book.id;
                titleCell.innerHTML = book.title;
                authorCell.innerHTML = book.author;
                dateCell.innerHTML = book.date;
                genresCell.innerHTML = book.genres;
                charactersCell.innerHTML = book.characters;
                synopsisCell.innerHTML = book.synopsis;
                updateCell.innerHTML = "<a href='Form2.html?id=" + book.id + "'>Update</a>";
                deleteCell.innerHTML = "<a href='javascript:DeleteBook(" + book.id + ")'>Delete</a>";
                
  			}
        }
    };

    xhttp.open("GET", "http://localhost:8080/FinalProject-RestfulApi/books", true);
    xhttp.setRequestHeader("Accept", getMediaType(format));
    xhttp.send();
}



function openAddBookForm() {
   window.open("Form1.html", "_blank");
   window.open("Form2.html", "_blank");
}


function closeAddBookForm() {
   window.close("Form1.html", "_blank"); 
   window.close("Form2.html", "_blank"); 
}

function saveBook() {
  var form = document.getElementById("AddbookForm");
  var formData = new FormData(form);

  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4) {
      if (this.status == 200) {
        console.log("Book saved successfully");
      } else {
        console.error("Error saving the book");
      }
    }
  };

  xhttp.open("POST", "http://localhost:8080/FinalProject-RestfulApi/books", true);
  xhttp.setRequestHeader("Content-Type", "application/json");
  xhttp.send(JSON.stringify(Object.fromEntries(formData)));
  }

   
  function UpdateBook() {
  var form = document.getElementById("UpdatebookForm");
  var formData = new FormData(form);

  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4) {
      if (this.status == 200) {
        console.log("Book updated successfully");
       } else {
        console.error("Error updating the book");
      }
    }
  };

  var bookId = // retrieve the book ID from the form or URL parameter
  xhttp.open("PUT", "http://localhost:8080/FinalProject-RestfulApi/books/" + bookId, true);
  xhttp.setRequestHeader("Content-Type", "application/json");
  xhttp.send(JSON.stringify(Object.fromEntries(formData)));
}

	function DeleteBook(bookId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4) {
      if (this.status == 200) {
        console.log("Book deleted successfully");
       
      } else {
        console.error("Error deleting the book");
      }
    }
  };

  xhttp.open("DELETE", "http://localhost:8080/FinalProject-RestfulApi/books/"+bookId, true);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.send();

}
   
   
  

   
	