document.addEventListener("DOMContentLoaded", function() {
    var questions = document.querySelectorAll(".faq .question");

    questions.forEach(function(question) {
        question.addEventListener("click", function() {
            this.classList.toggle("active");
            var answer = this.querySelector(".answer");
            answer.style.display = answer.style.display === "block" ? "none" : "block";
        });
    });
});

function searchFunction() {
    var input = document.getElementById("search").value.toLowerCase();
  
    var questions = document.getElementsByClassName("question");

    for (var i = 0; i < questions.length; i++) {
      var question = questions[i];
      var text = question.innerText.toLowerCase();
  
      if (text.includes(input)) {
        question.style.display = "block"; 
      } else {
        question.style.display = "none";
      }
    }
  }