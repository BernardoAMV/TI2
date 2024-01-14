
function toggleSubItens(button) {
  var subItens = button.nextElementSibling;
  if (subItens.style.display === "block") {
    subItens.style.display = "none";
  } else {
    subItens.style.display = "block";
  }
}

window.onload = function() {
  var subitens = document.getElementsByClassName("esconder");
  for (var i = 0; i < subitens.length; i++) {
    subitens[i].style.display = "none";
  }
}

$(document).ready(function() {
  $("#search-input").on("input", function() {
    var searchTerm = $(this).val().toLowerCase();
    var selectedCategories = [];

    $("input[type=checkbox]:checked").each(function() {
      selectedCategories.push($(this).val());
    });

    $(".card").each(function() {
      var cardText = $(this).text().toLowerCase();
      var cardCategories = $(this).data("species") + " " + $(this).data("gender") + " " + $(this).data("age");

      var matchesSearchTerm = cardText.includes(searchTerm);

      var matchesCategory = selectedCategories.some(function(category) {
        return cardCategories.includes(category);
      });

      if (matchesSearchTerm && matchesCategory) {
        $(this).show();
      } else {
        $(this).hide();
      }

      if (cardText.includes(searchTerm)) {
        $(this).show();
      } else {
        $(this).hide();
      }
    });
  });
});




document.addEventListener('DOMContentLoaded', function() {
  var checkboxCachorro = document.getElementById('checkbox-cachorro');
  var checkboxGato = document.getElementById('checkbox-gato');
  var checkboxFemea = document.getElementById('checkbox-femea');
  var checkboxMacho = document.getElementById('checkbox-macho');
  var checkboxFilhote = document.getElementById('checkbox-filhote');
  var checkboxAdulto = document.getElementById('checkbox-adulto');

  checkboxCachorro.addEventListener('change', function() {
    if (checkboxCachorro.checked) {
      checkboxGato.checked = false;
      checkboxGato.disabled = true;
    } else {
      checkboxGato.disabled = false;
    }
    filterCards();
  });

  checkboxGato.addEventListener('change', function() {
    if (checkboxGato.checked) {
      checkboxCachorro.checked = false;
      checkboxCachorro.disabled = true;
    } else {
      checkboxCachorro.disabled = false;
    }
    filterCards();
  });

  checkboxFemea.addEventListener('change', function() {
    if (checkboxFemea.checked) {
      checkboxMacho.checked = false;
      checkboxMacho.disabled = true;
    } else {
      checkboxMacho.disabled = false;
    }
    filterCards();
  });

  checkboxMacho.addEventListener('change', function() {
    if (checkboxMacho.checked) {
      checkboxFemea.checked = false;
      checkboxFemea.disabled = true;
    } else {
      checkboxFemea.disabled = false;
    }
    filterCards();
  });

  checkboxFilhote.addEventListener('change', function() {
    if (checkboxFilhote.checked) {
      checkboxAdulto.checked = false;
      checkboxAdulto.disabled = true;
    } else {
      checkboxAdulto.disabled = false;
    }
    filterCards();
  });

  checkboxAdulto.addEventListener('change', function() {
    if (checkboxAdulto.checked) {
      checkboxFilhote.checked = false;
      checkboxFilhote.disabled = true;
    } else {
      checkboxFilhote.disabled = false;
    }
    filterCards();
  });

  function filterCards() {
    var cards = document.getElementsByClassName('card');

    for (var i = 0; i < cards.length; i++) {
      var card = cards[i];
      var isCachorro = card.classList.contains('Cachorro');
      var isGato = card.classList.contains('Gato');
      var isFemea = card.classList.contains('Fêmea');
      var isMacho = card.classList.contains('Macho');

      var showCard = true;

      if (checkboxCachorro.checked && !isCachorro) {
        showCard = false;
      }

      if (checkboxGato.checked && !isGato) {
        showCard = false;
      }

      if (checkboxFemea.checked && !isFemea) {
        showCard = false;
      }

      if (checkboxMacho.checked && !isMacho) {
        showCard = false;
      }


      if (checkboxCachorro.checked && checkboxGato.checked && !(isCachorro || isGato)) {
        showCard = false;
      }

      if (checkboxFemea.checked && checkboxMacho.checked && !(isFemea || isMacho)) {
        showCard = false;
      }

      if (checkboxCachorro.checked && checkboxGato.checked) {
        showCard = true;
      }
      if (checkboxCachorro.checked && checkboxGato.checked && checkboxFemea.checked) {
        showCard = true;
      }

      if (showCard) {
        card.classList.remove('esconder');
      } else {
        card.classList.add('esconder');
      }
    }
  }
});

window.onload(listaUsuarios()); 

  
  
  


async function listaUsuarios(){
    
  var animais = await fetch('http://localhost:5554/animal/list').then(data => data.json())

console.log(animais);
  animais = animais.animais
  
  const jsonContainer = document.getElementById('jsonContainer');

  jsonContainer.innerHTML = "";
  animais.forEach(element => {
  
      jsonContainer.innerHTML +=  `<div class="card ${element.especieAnimal} ${element.sexoDoAnimal}" >
      <div class="card-image">
        <img src=${element.fotoAnimal} alt="Imagem do Card">
      </div>
      <div class="card-content">
        <div id="nomec1"><h3>${element.nomeAnimal}</h3></div>
        <div class="flex">
          <ul>
            <div>
              <p>Raça: ${element.racaAnimal} </p>
              <p>Idade: ${element.idadeAnimal} ano(s)</p>
              <p>Sexo: ${element.sexoDoAnimal}</p>
              <p>Especie: ${element.especieAnimal}</p>
              <p>Descrição: ${element.descricaoAnimal}</p>
              <p>Responsavel: ${element.nomeResponsavel}</p>
              <p>Contato: ${element.contatoResponsavel}</p>
            </div>
          </ul>
          
        </div>
      </div>
    </div>`
  });
}

function salvarDados() {
  // Recupera os valores dos campos de texto
  var nomeInstituicao = document.getElementById("nomeInstituicao").value;
  var cnpj = document.getElementById("cnpjInput").value;
  var senha = document.getElementById("senhaInput").value;
  var contato = document.getElementById("contato").value;

  // Verifica se os campos estão preenchidos
  if (nomeInstituicao && cnpj && senha && contato) {
    // Cria um objeto com os dados
    var dados = {
      nomeInstituicao: nomeInstituicao,
      cnpj: cnpj,
      senha: senha,
      contato: contato
    };

    // Converte o objeto em uma string JSON
    const options = {
      method : 'POST',
      body : JSON.stringify(dados)

    };
    var usuarios = fetch('http://localhost:5554/usuario/insert', options)
.then(response => response.json())
.then(data => {
    console.log('Usuário inserido com sucesso:', data);
    
})
.catch(error => {
    console.error('Erro ao inserir usuário:', error);
});
console.log(dados);

    // Exibe uma mensagem de sucesso
    alert("Conta criada com sucesso!");

    // Limpa os campos do formulário
    document.getElementById("nomeInstituicao").value = "";
    document.getElementById("cnpjInput").value = "";
    document.getElementById("senhaInput").value = "";
    document.getElementById("contato").value = "";

    var cadastroDiv = document.querySelector('.cadastro');
    var overlayDiv = document.querySelector('.overlay');

    cadastroDiv.remove();
    overlayDiv.style.display = 'none';


  } else {
    // Exibe uma mensagem de erro se algum campo estiver vazio
    alert("Preencha todos os campos do formulário.");
  }
}

async function validarLogin(){

  var loginCnpj = document.getElementById("loginCnpj").value;
  var loginSenha = document.getElementById("loginSenha").value;

  let usuario = {
    cnpj : loginCnpj,
    senha : loginSenha 
    

  };

  const dados = {
    method : 'POST',
    body : JSON.stringify(usuario)

  };
  
  
  await fetch('http://localhost:5554/usuario/validate', dados)
  .then(data => data.json())
  .then(data => {
    console.log(data);


    if (data.authenticated) {
      if(data.isAdmin){
      alert("Login feito com sucesso, bem-vindo admin!");

      document.getElementById("loginCnpj").value = "";
      document.getElementById("loginSenha").value = "";
      window.location.href = '../Admin/admin.html';
      }
     else {
      alert("Login feito com sucesso, bem-vindo!");
  
      localStorage.setItem("dadosUser", JSON.stringify(data));
      window.location.href = 'logado.html';
     }
    } else {
      alert("Usuário não encontrado");
      window.location.href = 'index.html';
    }
  })
  //localStorage.removeItem('dadosDaInstituicao')
}
async function enviarPergunta() {
  var pergunta = document.getElementById("pergunta").value;

  var data = {
      pergunta: pergunta
  };

  const dados = {
      method: 'POST',
      body: JSON.stringify(data)
  };

  try {
      const response = await fetch('http://localhost:5554/gpt/pedido', dados);

      if (!response.ok) {
          throw new Error(`Erro na solicitação: ${response.status}`);
      }

      const responseData = await response.json();
      console.log(responseData);

      // Manipular a resposta recebida do servidor
      var respostaDoServidor = responseData.choices[0].message.content;
      return respostaDoServidor
      //document.getElementsByClassName("incoming").innerHTML = respostaDoServidor;

  } catch (error) {
      console.error('Erro na solicitação:', error);
  }
}




